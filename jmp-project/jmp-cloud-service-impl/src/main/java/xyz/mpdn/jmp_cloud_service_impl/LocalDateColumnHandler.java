package xyz.mpdn.jmp_cloud_service_impl;

import org.apache.commons.dbutils.ColumnHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LocalDateColumnHandler implements ColumnHandler<LocalDate> {
    @Override
    public LocalDate apply(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getDate(columnIndex).toLocalDate();
    }

    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(LocalDate.class);
    }
}
