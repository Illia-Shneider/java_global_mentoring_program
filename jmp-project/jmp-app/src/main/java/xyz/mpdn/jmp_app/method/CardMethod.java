package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_service_api.Service;

public class CardMethod implements Method{
    private final static String methodName = "card";
    @Override
    public void handle(Service service, String parameter) {
        service.getAllSubscriptionsByCondition(sub -> sub.getBankcard().startsWith(parameter))
                .forEach(sub -> System.out.format(
                        "%s %s%n",
                        sub.getBankcard(),
                        sub.getStartDate()
                ));
    }

    @Override
    public boolean match(String method) {
        return methodName.equals(method);
    }
}
