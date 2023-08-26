package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_service_api.Service;

public class AverageUserAgeMethod implements Method {
    private final static String methodName = "averageAge";

    @Override
    public void handle(Service service, String parameter) {
        System.out.format(
                "Average age is %.0f %n",
                service.getAverageUsersAge()
        );
    }

    @Override
    public boolean match(String method) {
        return methodName.equals(method);
    }
}
