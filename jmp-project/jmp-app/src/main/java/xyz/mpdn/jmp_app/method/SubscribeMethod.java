package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_dto.BankCard;
import xyz.mpdn.jmp_service_api.Service;

public class SubscribeMethod implements Method{
    private final static String methodName = "subscribe";
    @Override
    public void handle(Service service, String parameter) {
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

    @Override
    public boolean match(String method) {
        return methodName.equals(method);
    }

}
