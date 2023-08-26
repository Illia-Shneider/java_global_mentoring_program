module xyz.mpdn.jmp_cloud_service_impl {
    requires transitive xyz.mpdn.jmp_service_api;
    requires transitive java.sql;
    requires lombok;
    requires org.apache.commons.dbutils;

    provides org.apache.commons.dbutils.ColumnHandler with
            xyz.mpdn.jmp_cloud_service_impl.DateColumnHandler,
            xyz.mpdn.jmp_cloud_service_impl.LocalDateColumnHandler;
    provides xyz.mpdn.jmp_service_api.Service with
            xyz.mpdn.jmp_cloud_service_impl.CloudServiceImpl;

    exports xyz.mpdn.jmp_cloud_service_impl;
}