package com.healthcare.product.service.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * Created by wichon on 2/15/17.
 */
@Component
public class ContentCachingRequestWrappingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest
                && servletResponse instanceof HttpServletResponse) {
            ContentCachingRequestWrapper requestWrapper =
                    new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
            ContentCachingResponseWrapper responseWrapper =
                    new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
            chain.doFilter(requestWrapper, responseWrapper);
            /*
             * Hack to avoid having an empty response
             * ContentCachingRequestWrapper reads the body from the response output stream to cache
             * it, moving the stream cursor to the end, so we need to write back the body to the
             * response output stream to be able to use it in the controllers response.
             */
            responseWrapper.copyBodyToResponse();
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}