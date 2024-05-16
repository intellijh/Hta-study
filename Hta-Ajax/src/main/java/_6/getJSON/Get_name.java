package _6.getJSON;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/get_name")
public class Get_name extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        String dbq = "\"";
        //{"name":"홍길동"}
        String Message = "{" + dbq + "name" + dbq + ":" + dbq + "홍길동" + dbq + "}";

        //{name:"홍길동"} => error status : parseerror
//        String Message = "{name:" + dbq + "홍길동" + dbq + "}";

        System.out.println(Message);
        response.getWriter().print(Message);
    }
}
