package com.vinsguru.graphqlplayground.lec05;

import com.vinsguru.graphqlplayground.lec05.dto.Address;
import com.vinsguru.graphqlplayground.lec05.dto.Customer;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping
    public Mono<Address> address(DataFetchingFieldSelectionSet selectionSet, Customer customer){
        System.out.println(
                "address : " + selectionSet.getFields()
        );
        return Mono.just(
          Address.create(customer.getName() + " street", customer.getName() + " city" )
        );
    }
}
