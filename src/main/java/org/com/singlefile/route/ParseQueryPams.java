package org.com.singlefile.route;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.com.singlefile.route.person.PersonPredicateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParseQueryPams {

    private final MultiValueMap params;

    final String REGEX_PAGE = "";
    final int DEFAULT_PAGE_NUMBER = 0;
    final int DEFAULT_PAGE_SIZE = 20;

    public ParseQueryPams(MultiValueMap params) {
        this.params = params;
    }

    public PageRequest getPageble(Optional<String> pageNumber, Optional<String> pageSize) {

        return PageRequest.of(pageNumber.map(Integer::parseInt).orElse(DEFAULT_PAGE_NUMBER),
                pageSize.map(Integer::parseInt).orElse(DEFAULT_PAGE_SIZE));
    }

    public Sort getSort(Optional<String> sortQueryParam) {

        return sortQueryParam.map(string -> Sort.by(
                        Arrays.stream(string.split(","))
                                .map(s -> s.split("\\."))
                                .map(s -> new Sort.Order(Sort.Direction.fromString(s[1]), s[0]))
                                .collect(Collectors.toList())))
                .orElse(Sort.unsorted());
    }

    public PageRequest getPageAndSort() {

        return getPageble(getParam("page"), getParam("size"))
                .withSort(getSort(getParam("sort")));

    }

    private Optional<String> getParam(String paramName) {
        return Optional.ofNullable(this.params.get(paramName) != null ?
                ((List<String>)this.params.get(paramName)).getFirst() : null);
    }

    public BooleanExpression getPredicate(){
        Optional<String> searchParm = getParam("search");
        PersonPredicateBuilder builder = new PersonPredicateBuilder();
        if (searchParm.isPresent()) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(searchParm.get() + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        return builder.build();
    }

}
