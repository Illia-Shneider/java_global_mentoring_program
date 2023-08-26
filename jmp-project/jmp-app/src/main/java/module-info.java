module xyz.mpdn.jmp_app {
    requires info.picocli;
    requires xyz.mpdn.jmp_cloud_service_impl;
    requires xyz.mpdn.jmp_service_api;

    opens xyz.mpdn.jmp_app to info.picocli;

    exports xyz.mpdn.jmp_app;
}