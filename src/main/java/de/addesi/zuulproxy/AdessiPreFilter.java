package de.addesi.zuulproxy;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

public class AdessiPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        joiner.add("AuthType=" + request.getAuthType());
        joiner.add("Methodname=" + request.getMethod());
        joiner.add("ContextPath=" + request.getContextPath());
        joiner.add("User=" + request.getRemoteUser());
        joiner.add("URL=" + request.getRequestURL());
        joiner.add("UserPrincipal=" + request.getUserPrincipal());

        System.out.println(joiner);

        return joiner.toString();
    }
}
