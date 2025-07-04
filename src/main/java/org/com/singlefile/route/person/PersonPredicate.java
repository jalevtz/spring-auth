package org.com.singlefile.route.person;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;
import org.com.singlefile.domain.model.Person;
import org.com.singlefile.domain.model.SearchCriteria;

public class PersonPredicate {
    private SearchCriteria criteria;

    public PersonPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public BooleanExpression getPredicate() {
        PathBuilder<Person> entityPath = new PathBuilder<>(Person.class, "person");

        if (StringUtils.isNumeric(criteria.value().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.key(), Integer.class);
            int value = Integer.parseInt(criteria.value().toString());
            switch (criteria.operation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        }
        else {
            StringPath path = entityPath.getString(criteria.key());
            if (criteria.operation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.value().toString());
            }
        }
        return null;
    }

}
