package net.template.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "templateFrontController", urlPatterns = "*.net")
public class TemplateFrontController extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        System.out.println("requestURI = " + requestURI);
        System.out.println("contextPath = " + contextPath);
        System.out.println("command = " + command);
    }

    //doProcess(request, response) 메서드를 구현하여 요청이 GET 방식이든
    //POST 방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
