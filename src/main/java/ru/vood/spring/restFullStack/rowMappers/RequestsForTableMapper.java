package ru.vood.spring.restFullStack.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class RequestsForTableMapper implements RowMapper<Object> {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) {
        return null;
    }
    /*
    @Override
    public RequestsForTableMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new RequestsForTableMap(rs.getString("request_type_hr")
                , rs.getString("list_access")
                , rs.getString("list_beneficiaries")
                , rs.getString("isSelected")
                , rs.getString("request_id")
                , rs.getString("justification")
                , rs.getString("id")
                , rs.getString("request_type")
                , rs.getString("initiator")
                , rs.getString("initiator_hr")
        );
    }
*/


}
