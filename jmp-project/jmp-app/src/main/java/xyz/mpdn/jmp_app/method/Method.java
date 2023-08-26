package xyz.mpdn.jmp_app.method;

import xyz.mpdn.jmp_service_api.Service;

public interface Method {
    void handle(Service service, String parameter);
    boolean match(String method);
}
