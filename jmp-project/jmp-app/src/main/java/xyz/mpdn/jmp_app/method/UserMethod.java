package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_service_api.Service;

public class UserMethod implements Method{
    private final static String methodName = "users";
    @Override
    public void handle(Service service, String parameter) {
        service.getAllUsers()
                .forEach(user -> System.out.format(
                        "%s %s, %s%n",
                        user.getName(),
                        user.getSurname(),
                        user.getBirthday()
                ));
    }

    @Override
    public boolean match(String method) {
        return methodName.equals(method);
    }

}
