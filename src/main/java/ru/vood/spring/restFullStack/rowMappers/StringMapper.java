package ru.vood.spring.restFullStack.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.vood.spring.restFullStack.rowMappers.mappedObjects.StringMap;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StringMapper implements RowMapper<StringMap> {
    @Override
    public StringMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new StringMap(rs.getString(1));
    }
}
