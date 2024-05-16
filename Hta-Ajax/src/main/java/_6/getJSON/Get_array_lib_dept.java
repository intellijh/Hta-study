package _6.getJSON;

import _6.db.DAO;
import _6.db.Dept;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/get_array_lib_dept")
public class Get_array_lib_dept extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        DAO dao = new DAO();
        ArrayList<Dept> depts = dao.selectAll();
        JsonArray array = new JsonArray();
        for (Dept dept : depts) {
            JsonObject json = new JsonObject();
            json.addProperty("deptno", dept.getDeptno());
            json.addProperty("dname", dept.getDname());
            json.addProperty("loc", dept.getLoc());
            array.add(json);
        }

        System.out.println(array);
        response.getWriter().print(array);
    }
}
