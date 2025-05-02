package com.vinsguru.graphqlplayground.lec15.controller;

import com.vinsguru.graphqlplayground.lec15.exceptions.ApplicationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {


    //Custom Error
//    @Override
//    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
//        return Mono.just(
//                List.of(
//                        //Passing DataFetchingEnvironment to get path location etc. in response
//                        GraphqlErrorBuilder.newError(environment)
//                                .message(exception.getMessage())
//                                .errorType(ErrorType.INTERNAL_ERROR )
//                                .build()
//                )
//        );
//    }

    //Pass additional information
//    @Override
//    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
//        return Mono.just(
//                List.of(
//                        //Passing DataFetchingEnvironment to get path location etc. in response
//                        GraphqlErrorBuilder.newError(environment)
//                                .message(exception.getMessage())
//                                .errorType(ErrorType.INTERNAL_ERROR)
//                                //additional information
//                                .extensions(Map.of(
//                                        "customerId", 123,
//                                        "date", LocalDateTime.now()
//                                ))
//                                .build()
//                )
//        );
//    }

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        var ex = toApplicationException(exception); //Mapping to  ApplicationException
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError(environment)
                                .message(ex.getMessage())
                                .errorType(ex.getErrorType())
                                .extensions(ex.getExtensions())
                                .build()
                )
        );
    }

    //Mapping to  ApplicationException
    private ApplicationException toApplicationException(Throwable throwable) {
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException) throwable :
                new ApplicationException(ErrorType.INTERNAL_ERROR, throwable.getMessage(), Collections.emptyMap());
    }

}
