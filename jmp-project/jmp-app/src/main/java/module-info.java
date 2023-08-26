module xyz.mpdn.jmp_app {
    requires info.picocli;
    requires xyz.mpdn.jmp_cloud_service_impl;
    requires xyz.mpdn.jmp_service_api;

    opens xyz.mpdn.jmp_app to info.picocli;

    exports xyz.mpdn.jmp_app;

    provides xyz.mpdn.jmp_app.method.Method with
            xyz.mpdn.jmp_app.method.SubscribeMethod,
            xyz.mpdn.jmp_app.method.SubscriptionMethod,
            xyz.mpdn.jmp_app.method.UserMethod,
            xyz.mpdn.jmp_app.method.PayableUserMethod,
            xyz.mpdn.jmp_app.method.AverageUserAgeMethod,
            xyz.mpdn.jmp_app.method.TestMethod;

    uses xyz.mpdn.jmp_app.method.Method;
    uses xyz.mpdn.jmp_service_api.Service;
}