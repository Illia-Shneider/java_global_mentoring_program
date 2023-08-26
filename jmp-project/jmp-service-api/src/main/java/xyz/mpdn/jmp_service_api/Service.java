package xyz.mpdn.jmp_service_api;

import xyz.mpdn.jmp_dto.BankCard;
import xyz.mpdn.jmp_dto.Subscription;
import xyz.mpdn.jmp_dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }

    void subscribe(BankCard card);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

    default double getAverageUsersAge() {
        return getAllUsers()
                .stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                .average()
                .orElse(Double.NaN);
    }

    List<Subscription> getAllSubscriptions();
}
