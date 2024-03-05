package test_attr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ex1/test_attr/send")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("id", request.getParameter("id"));
        session.setAttribute("pass", request.getParameter("pass"));
        session.setAttribute("jumin", (request.getParameter("jumin1") + "-"+request.getParameter("jumin2")));
        session.setAttribute("email", (request.getParameter("email") + "@"+request.getParameter("domain")));

        String gender = request.getParameter("gender").equals("m") ? "남자" : "여자";
        session.setAttribute("gender", gender);

        String[] hobbys = request.getParameterValues("hobby");
        String hobby = "";
        for (String h : hobbys) {
            hobby += h + " ";
        }

        session.setAttribute("hobby", hobby);
        session.setAttribute("post", request.getParameter("post1"));
        session.setAttribute("address", request.getParameter("address"));
        session.setAttribute("intro", request.getParameter("intro"));

        response.sendRedirect("signupSuccess.jsp");
    }
}
