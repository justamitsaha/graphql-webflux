package com.vinsguru.graphqlplayground.lec08;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FieldGlobPatternController {

    @QueryMapping
    public Object level1(DataFetchingFieldSelectionSet selectionSet){
        System.out.println("level1 -->" +selectionSet.contains("level1"));      // false
        System.out.println("level2 -->" +selectionSet.contains("level2"));      //true
        System.out.println("level2/level3 -->" +selectionSet.contains("level2/level3")); //true
        System.out.println("level3 -->" +selectionSet.contains("*/level3"));  //false
        System.out.println("*/level3 -->" +selectionSet.contains("*/level3"));  //true
        System.out.println("level2/*/level5 -->" +selectionSet.contains("level2/*/level5")); //false
        System.out.println("level2/**/level5 -->" +selectionSet.contains("level2/**/level5")); //true
        return null;
    }
}
