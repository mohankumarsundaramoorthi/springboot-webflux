package com.practice.sprintbootwebflux.dao;

import com.practice.sprintbootwebflux.dto.Employee;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class EmployeeDAO {

    private  void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //This is asynchronous and blocking operation
    public List<Employee> getEmployees()  {
        return IntStream.rangeClosed(1, 10)
                .peek(this::sleepExecution)
                .peek(i -> System.out.println("Count : " + i))
                .mapToObj(i -> new Employee(i, "employee-" + i))
                .collect(Collectors.toList());
    }

    public Flux<Employee> getEmployeesStream()  {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Count in stream flow : " + i))
                .map(i -> new Employee(i, "employee-" + i));
    }


    public Flux<Employee> getEmployeeList()  {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("Count in stream flow : " + i))
                .map(i -> new Employee(i, "employee-" + i));
    }

}
