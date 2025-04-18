package com.vinsguru.graphqlplayground.lec16.serverapp.service;

import com.vinsguru.graphqlplayground.lec16.dto.*;
import com.vinsguru.graphqlplayground.lec16.serverapp.repository.CustomerRepository16;
import com.vinsguru.graphqlplayground.lec16.serverapp.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository16 repository;

    @Autowired
    private CustomerEventService eventService;

    public Flux<CustomerDto> allCustomers(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> customerById(Integer id){
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> createCustomer(CustomerDto dto){
        return Mono.just(dto)
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto)
                .doOnNext(c -> this.eventService.emitEvent(CustomerEvent.create(c.getId(), Action.CREATED)));
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto){
        return this.repository.findById(id)
                .map(c -> EntityDtoUtil.toEntity(id, dto))
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto)
                .doOnNext(c -> this.eventService.emitEvent(CustomerEvent.create(c.getId(), Action.UPDATED)));
    }

    public Mono<DeleteResponseDto> deleteCustomer(Integer id){
        return this.repository.deleteById(id)
                .doOnSuccess(c -> this.eventService.emitEvent(CustomerEvent.create(id, Action.DELETED)))
                .thenReturn(DeleteResponseDto.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponseDto.create(id, Status.FAILURE));
    }

}
