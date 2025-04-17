package com.vinsguru.graphqlplayground.lec05;

import com.vinsguru.graphqlplayground.lec05.dto.Account;
import com.vinsguru.graphqlplayground.lec05.dto.Customer;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

    @SchemaMapping
    public Mono<Account> account(DataFetchingFieldSelectionSet selectionSet, Customer customer){
        System.out.println(
                "account : " + selectionSet.getFields()
        );
        var type = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVING";
        return Mono.just(
              Account.create(
                      UUID.randomUUID(),
                      ThreadLocalRandom.current().nextInt(1, 1000),
                      type
              )
        );
    }
}
