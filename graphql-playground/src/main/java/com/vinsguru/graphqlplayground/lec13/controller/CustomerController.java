package com.vinsguru.graphqlplayground.lec13.controller;

import com.vinsguru.graphqlplayground.lec13.dto.CustomerDto;
import com.vinsguru.graphqlplayground.lec13.dto.DeleteResponseDto;
import com.vinsguru.graphqlplayground.lec13.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @QueryMapping
    public Flux<CustomerDto> customers() {
        return this.service.allCustomers();
    }

    @QueryMapping
    public Mono<CustomerDto> customerById(@Argument Integer id) {
        return this.service.customerById(id);
    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument CustomerDto customer) {
        return this.service.createCustomer(customer)
                .delayElement(Duration.ofSeconds(1));
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto dto) {
        return this.service.updateCustomer(id, dto)
                .delayElement(Duration.ofSeconds(1));
    }

    @MutationMapping
    public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id) {
        return this.service.deleteCustomer(id);
    }

}
