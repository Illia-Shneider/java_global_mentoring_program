package xyz.mpdn.jmp_cloud_service_impl;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xyz.mpdn.jmp_dto.BankCard;
import xyz.mpdn.jmp_dto.Subscription;
import xyz.mpdn.jmp_dto.User;
import xyz.mpdn.jmp_service_api.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@Setter
@NoArgsConstructor
public class CloudServiceImpl implements Service {

    private final QueryRunner qr = new QueryRunner();
    private Connection connection;
    private ResultSetHandler<List<Subscription>> subscriptionHandler = new BeanListHandler<>(Subscription.class);
    private ResultSetHandler<List<User>> userHandler = new BeanListHandler<>(User.class);

    @Override
    public void subscribe(BankCard card) {
        try {
            qr.execute(connection,
                    "INSERT INTO subscriptions (bankcard, date) VALUES (?, CURRENT_DATE)",
                    card.getNumber());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        Optional<Subscription> subscription = Optional.empty();
        try {
            subscription = qr.query(connection,
                            "SELECT bankcard, date FROM subscriptions WHERE bankcard = ?",
                            subscriptionHandler,
                            cardNumber)
                    .stream()
                    .findFirst();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        List<Subscription> subscriptions = List.of();
        try {
            subscriptions = qr.query(connection,
                    "SELECT bankcard, date FROM subscriptions",
                    subscriptionHandler);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return subscriptions;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = List.of();
        try {
            users = qr.query(connection,
                    "SELECT name, surname, birthday FROM users",
                    userHandler);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
//            System.out.println(exception.getMessage());
        }

        return users;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return getAllSubscriptions()
                .stream()
                .filter(predicate)
                .toList();
    }
}
