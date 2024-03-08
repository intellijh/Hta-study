package ex8_emp_search;

import ex8.emp.DAO;
import ex8.emp.Emp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/field_select")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        int menuIndex = Integer.parseInt(request.getParameter("field"));
        String[] menus = { "empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno" };
        String input = (String) request.getParameter("search").toUpperCase();

        ArrayList<Emp> list;
        if (input.equals("")) {
            list = dao.selectAll();
        } else {
            list = dao.select(menus[menuIndex], input);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ex8_db/_2.list.emp/list.jsp");
        request.setAttribute("list", list);
        dispatcher.forward(request, response);
    }
}
