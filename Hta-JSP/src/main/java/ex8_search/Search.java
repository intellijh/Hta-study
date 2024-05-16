package ex8_search;

import ex8.DAO;
import ex8.Dept;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

//http://localhost:8088/JSP/dept_select
@WebServlet(name = "search", urlPatterns = "/search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        ArrayList<Dept> list = dao.select(Integer.parseInt(request.getParameter("deptno")));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex8_db/_2.list/list.jsp"); //View
        request.setAttribute("list", list);
        dispatcher.forward(request, response);
    }
}
