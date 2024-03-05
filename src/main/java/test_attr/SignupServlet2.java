package test_attr;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ex1/test_attr/send")
public class SignupServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("pass", request.getParameter("pass"));
        request.setAttribute("jumin", (request.getParameter("jumin1") + "-"+request.getParameter("jumin2")));
        request.setAttribute("email", (request.getParameter("email") + "@"+request.getParameter("domain")));

        String gender = request.getParameter("gender").equals("m") ? "남자" : "여자";
        request.setAttribute("gender", gender);

        String[] hobbys = request.getParameterValues("hobby");
        String hobby = "";
        for (String h : hobbys) {
            hobby += h + " ";
        }

        request.setAttribute("hobby", hobby);
        request.setAttribute("post", request.getParameter("post1"));
        request.setAttribute("address", request.getParameter("address"));
        request.setAttribute("intro", request.getParameter("intro"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("signupSuccess.jsp");
        dispatcher.forward(request, response);
    }
}