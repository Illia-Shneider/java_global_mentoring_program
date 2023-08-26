package xyz.mpdn.jmp_app;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import xyz.mpdn.jmp_cloud_service_impl.CloudServiceImpl;
import xyz.mpdn.jmp_dto.BankCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;


@Command(name = "JmpApp", mixinStandardHelpOptions = true, version = "jmpApp 1.0",
        description = "Demo app for a Java Mentoring Program course")
public class JmpApp implements Callable<Integer> {
    private static final String H2_INIT_SQL = "jmp-project/jmp-app/src/main/resources/h2init.sql";
    private static final String CONNECTION_URL = "jdbc:h2:mem:jmp;INIT=RUNSCRIPT FROM '" + H2_INIT_SQL + "';";
    private final Connection connection;

    @Option(names = {"-m", "--method"}, description = "subscribe, subscription, users, card", required = true)
    private String method;
    @Option(names = {"-p", "--parameter"}, description = """
            -m subscribe -p {card number}
            -m subscription -p {card number}
            -m card -p {card number first digits}""")
    private String parameter;

    public JmpApp(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) throws SQLException {
        try (var connection = DriverManager.getConnection(CONNECTION_URL)) {
            var code = new CommandLine(new JmpApp(connection)).execute(args);
            System.exit(code);
        }
    }

    @Override
    public Integer call() {
        var service = new CloudServiceImpl();
        service.setConnection(connection);

        if ("subscribe".equals(method)) {
            var card = new BankCard();
            card.setNumber(parameter);
            service.subscribe(card);
            service.getAllSubscriptions()
                    .forEach(subscription -> System.out.format(
                            "%s, %s %s%n",
                            subscription.getBankcard(),
                            subscription.getStartDate(),
                            parameter.equals(subscription.getBankcard()) ? "*" : ""
                    ));
        }

        if ("subscription".equals(method)) {
            var subscription = service.getSubscriptionByBankCardNumber(parameter)
                    .orElseThrow(SubscriptionNotFoundException::new);

            System.out.format(
                    "%s %s%n",
                    subscription.getBankcard(),
                    subscription.getStartDate());
        }

        if ("card".equals(method)) {
            service.getAllSubscriptionsByCondition(sub -> sub.getBankcard().startsWith(parameter))
                    .forEach(sub -> System.out.format(
                            "%s %s%n",
                            sub.getBankcard(),
                            sub.getStartDate()
                    ));
        }

        if ("users".equals(method)) {
            service.getAllUsers()
                    .forEach(user -> System.out.format(
                            "%s %s, %s%n",
                            user.getName(),
                            user.getSurname(),
                            user.getBirthday()
                    ));
        }


        return 0;
    }
}
