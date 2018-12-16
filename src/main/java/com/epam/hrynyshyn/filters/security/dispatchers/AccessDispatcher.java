package com.epam.hrynyshyn.filters.security.dispatchers;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AccessDispatcher {
    void checkAccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,ServletException;
}
