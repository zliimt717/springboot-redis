package org.example;

import org.example.Dao.IEmployeeDao;
import org.example.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Map;


@SpringBootApplication
@EnableCaching
public class RedisOperationsRunner implements CommandLineRunner {
    @Autowired
    private IEmployeeDao empDao;
    @Override
    public void run(String... args) throws Exception {

        //saving one employee
        empDao.saveEmployee(new Employee(500,"Emp0",2150.0));

        //saving multiple employee
        empDao.saveAllEmployees(
                Map.of(
                        501,new Employee(501,"Emp1",2396.0),
                        502,new Employee(502,"Emp2",2499.5),
                        503,new Employee(503,"Emp4",2324.75)
                )
        );

        //modifying employee with empId 503
        empDao.updateEmployee(new Employee(503,"Emp3",2325.25));
        // deleting employee with empId 500
        empDao.deleteEmployee(500);
        //retrieving all employee
        empDao.getAllEmployees().forEach(
                (k,v)->System.out.println(k+":"+v)
        );
        //retrieving employee with empId 501
        System.out.println("Emp details for 501:"+empDao.getOneEmployee(501));
    }

}
