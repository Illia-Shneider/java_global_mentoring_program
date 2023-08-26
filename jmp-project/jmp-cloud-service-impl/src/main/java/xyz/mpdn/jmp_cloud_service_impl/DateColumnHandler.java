package xyz.mpdn.jmp_cloud_service_impl;

import org.apache.commons.dbutils.ColumnHandler;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateColumnHandler implements ColumnHandler<Date> {
    @Override
    public Date apply(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getDate(columnIndex);
    }

    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(Date.class);
    }
}
