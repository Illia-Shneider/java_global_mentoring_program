package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_service_api.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PayableUserMethod implements Method {
    private final static String methodName = "payableUser";

    @Override
    public void handle(Service service, String parameter) {
        service.getAllUsers()
                .stream()
                .filter(Service::isPayableUser)
                .forEach(user -> System.out.format(
                        "%s %s, %s (%d)%n",
                        user.getName(),
                        user.getSurname(),
                        user.getBirthday(),
                        ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now())
                ));
    }

    @Override
    public boolean match(String method) {
        return methodName.equals(method);
    }
}
