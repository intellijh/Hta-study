package ex8.emp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

//http://localhost:8088/JSP/emp_select
@WebServlet(name = "emp_select", urlPatterns = "/emp_select")
public class Emp_select extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        ArrayList<Emp> list = dao.selectAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex8_db/_2.list.emp/list.jsp"); //View
        request.setAttribute("list", list);
        dispatcher.forward(request, response);
    }
}
