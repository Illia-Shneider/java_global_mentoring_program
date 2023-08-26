package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_service_api.Service;

public class TestMethod implements Method {
    @Override
    public void handle(Service service, String parameter) {
        System.out.println("Works!!!!");
    }

    @Override
    public boolean match(String method) {
        return method.equals("test");
    }
}
