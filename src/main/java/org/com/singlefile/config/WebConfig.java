package org.com.singlefile.config;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

public class WebConfig implements WebFluxConfigurer {



    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        SortHandlerMethodArgumentResolver argumentResolver = new SortHandlerMethodArgumentResolver();
        argumentResolver.setSortParameter("sort");
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver(argumentResolver);
        resolver.setFallbackPageable(null);
        resolver.setPageParameterName("page");
        resolver.setSizeParameterName("size");
        configurer.addCustomResolver((HandlerMethodArgumentResolver) resolver);
    }
}
