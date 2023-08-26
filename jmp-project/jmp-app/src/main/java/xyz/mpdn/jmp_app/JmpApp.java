package xyz.mpdn.jmp_app;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import xyz.mpdn.jmp_app.method.Method;
import xyz.mpdn.jmp_cloud_service_impl.CloudServiceImpl;
import xyz.mpdn.jmp_service_api.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.concurrent.Callable;


@Command(name = "JmpApp", mixinStandardHelpOptions = true, version = "jmpApp 1.0",
        description = "Demo app for a Java Mentoring Program course")
public class JmpApp implements Callable<Integer> {
    private static final String H2_INIT_SQL = "jmp-project/jmp-app/src/main/resources/h2init.sql";
    private static final String CONNECTION_URL = "jdbc:h2:mem:jmp;INIT=RUNSCRIPT FROM '" + H2_INIT_SQL + "';";
    private final Connection connection;

    @Option(names = {"-m", "--method"}, description = """ 
            -m subscribe
            -m subscription
            -m users
            -m card
            -m averageAge
            -m payableUser
            """, required = true)
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
            System.exit(new CommandLine(new JmpApp(connection)).execute(args));
        }
    }

    @Override
    public Integer call() {
        var service = ServiceLoader.load(Service.class)
                .stream()
                .map(Provider::get)
                .filter(s -> s instanceof CloudServiceImpl)
                .peek(s -> ((CloudServiceImpl) s).setConnection(connection))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CloudServiceImpl not found in modules graph"));

        ServiceLoader.load(Method.class)
                .stream()
                .map(Provider::get)
                .filter(m -> m.match(method))
                .forEach(m -> m.handle(service, parameter));

        return 0;
    }
}
