
package com.example.employee.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeRowMapper;


@Service 

public class EmployeeH2Service{
    @Autowired 
    private JdbcTemplate db;
   
    public ArrayList<Employee> getEmployees(){
        
        return (ArrayList<Employee>) db.query("select * from EMPLOYEELIST",new EmployeeRowMapper());

    }

    public Employee getEmployeeById(int employeeId){
        try{
            Employee employee = db.queryForObject("select * from EMPLOYEELIST where employeeId=?",new EmployeeRowMapper(),employeeId);
        return employee;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
        
    }
  
    public Employee addEmployee(Employee employee){
        db.update("insert into EMPLOYEELIST(employeeName,email,department) values (?,?,?)",employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
        return db.queryForObject("select * from EMPLOYEELIST where employeeName=? and email=? and department=?",new EmployeeRowMapper(),employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
        
    }
   
    public Employee updateEmployee(int employeeId,Employee employee){
        if(employee.getEmployeeName() != null){
            db.update("update EMPLOYEELIST set employeeName=? where employeeId=?",employee.getEmployeeName(),employeeId);
        }
        if(employee.getEmail() != null){
            db.update("update EMPLOYEELIST set email=? where employeeId=?",employee.getEmail(),employeeId);
        }
        if(employee.getDepartment() != null){
            db.update("update EMPLOYEELIST set department=? where employeeId=?",employee.getDepartment(),employeeId);
        }
        return getEmployeeById(employeeId);
    }

    public void deleteEmployee(int employeeId){
        db.update("delete from EMPLOYEELIST where employeeId=?",employeeId);
    }


}
