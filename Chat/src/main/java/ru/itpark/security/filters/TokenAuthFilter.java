package ru.itpark.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.itpark.models.User;
import ru.itpark.repository.UsersRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter extends GenericFilterBean {

    @Autowired
    private UsersRepository repository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // явно преобразовали ServletRequest к HttpServletRequest
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if (!isPermittedRequest(request)) {
            // вытаскиваем хеадер Auth-Token
            String token = request.getHeader("Auth-Token");
            User user = repository.findOneByToken(token);
            if (user == null) {
                throw new IllegalArgumentException();
            }
        }
        // разрешаем фильтру идти дальше
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public boolean isPermittedRequest(HttpServletRequest request) {
        return request.getRequestURI().equals("/login") &&
                request.getMethod().equals("POST") ||
                request.getRequestURI().equals("/users") &&
                        request.getMethod().equals("POST");
    }
}
