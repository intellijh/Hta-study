package ex1._2.mem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ex1/_2.mem/memReg")
public class memReg extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String addr = request.getParameter("addr");
        String tel = request.getParameter("tel");
        String hobby = request.getParameter("hobby");

        PrintWriter out = response.getWriter();

        out.println(
                "<head>\n" +
                "    <style>\n" +
                "        table {\n" +
                "            border-collapse: collapse;\n" +
                "            width: 50%;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "        tr {\n" +
                "            height: 3em;\n" +
                "            border-bottom: 1px solid black;\n" +
                "        }\n" +
                "        td {\n" +
                "            width: 60%;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <th>회원명</th><td>"+name+"</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <th>주소</th><td>"+addr+"</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <th>전화번호</th><td>"+tel+"</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <th>취미</th><td>"+hobby+"</td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");
        out.close();
    }
}
