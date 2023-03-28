package com.example.employee.model;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;


public class EmployeeRowMapper implements RowMapper<Employee>{
    @Override 
    public Employee mapRow(ResultSet rs,int rowNum) throws SQLException{
        return new Employee(rs.getInt("employeeId"),
        rs.getString("employeeName"),
        rs.getString("email"),
        rs.getString("department"));
    }


}
