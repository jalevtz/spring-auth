package org.com.singlefile.route.person;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.com.singlefile.domain.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonPredicateBuilder {

    private List<SearchCriteria> params;

    public PersonPredicateBuilder() {
        params = new ArrayList<>();
    }

    public PersonPredicateBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {
            PersonPredicate predicate = new PersonPredicate(param);
            return predicate.getPredicate();
        }).filter(Objects::nonNull).toList();

        BooleanBuilder builder = null;


        for (int i = 0; i < predicates.size(); i++) {
            if (i == 0) {
                builder = new BooleanBuilder(predicates.get(i));
            } else {
                builder.and(predicates.get(i));
            }
        }
        BooleanExpression result = Expressions.asBoolean(builder);

        return result;
    }
}
