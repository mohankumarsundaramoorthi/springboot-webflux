package com.practice.sprintbootwebflux.handler;

import com.practice.sprintbootwebflux.dao.EmployeeDAO;
import com.practice.sprintbootwebflux.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {

    @Autowired
    private EmployeeDAO dao;


    public Mono<ServerResponse> loadEmployees(ServerRequest request){
        Flux<Employee> employeeList = dao.getEmployeeList();
        return ServerResponse.ok().body(employeeList,Employee.class);
    }


    public Mono<ServerResponse> findEmployee(ServerRequest request){
        int employeeId = Integer.parseInt( request.pathVariable("id"));
        Mono<Employee> employeeMono = dao.getEmployeeList().filter(c -> c.getId() == employeeId).next();
        return ServerResponse.ok().body(employeeMono,Employee.class);
    }


    public Mono<ServerResponse> saveEmployee(ServerRequest request){
        Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
        Mono<String> saveResponse = employeeMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse,String.class);
    }

    public Mono<ServerResponse> getEmployees(ServerRequest request) {
        Flux<Employee> employeesStream = dao.getEmployeesStream();
        return ServerResponse.ok().
                contentType(MediaType.TEXT_EVENT_STREAM)
                .body(employeesStream, Employee.class);
    }
}
