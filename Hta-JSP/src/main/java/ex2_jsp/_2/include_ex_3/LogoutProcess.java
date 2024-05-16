package ex2_jsp._2.include_ex_3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ex2_jsp/_2.include_ex_3/Logout")
public class LogoutProcess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); //세션의 모든 속성을 삭제합니다.

        //session.removeAttribute("id") //특정 속성에 대해 제거합니다.

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(
                "<script>\n" +
                "    alert('로그아웃');\n" +
                "    location.href='template.jsp';" +
                "</script>"
        );
        out.flush();
    }
}
