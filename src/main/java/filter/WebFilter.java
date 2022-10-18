package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebFilter(filterName = "WebFilter")
public class WebFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String path = httpServletRequest.getServletPath();

        if (path.startsWith("/admin")) {
            HttpSession session = httpServletRequest.getSession();
            Integer role = (Integer) session.getAttribute("role");
            if (role != null) {
                if (role == 1) {
                    chain.doFilter(request, response);
                }
                else {
                    httpServletResponse.sendRedirect("/");
                }
            }
            else {
                httpServletResponse.sendRedirect("/log-in");
            }
        }
        else
            if(path.endsWith(".jsp") && !path.contains("login.jsp")) {
                httpServletResponse.sendRedirect("/");
            }
            else {
                // accept request and response
                chain.doFilter(request, response);
            }

    }
}
