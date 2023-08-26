package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_app.SubscriptionNotFoundException;
import xyz.mpdn.jmp_service_api.Service;

public class SubscriptionMethod implements Method {
    private final static String methodName = "subscribe";
    @Override
    public void handle(Service service, String parameter) {
        var subscription = service.getSubscriptionByBankCardNumber(parameter)
                .orElseThrow(SubscriptionNotFoundException::new);

        System.out.format(
                "%s %s%n",
                subscription.getBankcard(),
                subscription.getStartDate());
    }

    @Override
    public boolean match(String method) {
        return methodName.equals(method);
    }
}
