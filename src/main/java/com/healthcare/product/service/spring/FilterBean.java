package com.healthcare.product.service.spring;

import com.healthcare.product.service.web.filters.ContentCachingRequestWrappingFilter;
import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wichon on 2/15/17.
 */
@Configuration
public class FilterBean {
    @Bean
    public Filter contentCachingRequestWrappingFilter() {
        return new ContentCachingRequestWrappingFilter();
    }
}