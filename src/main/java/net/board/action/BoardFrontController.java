package net.board.action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.member.action.*;

import java.io.IOException;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
    protected void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("요청 주소: " + request.getRequestURL());
        /*
            요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
            예)
            http://localhost:8088/JspProject/BoardList.bo인 경우
            "/JspProject/BoardList.bo" 반환됩니다.
        */
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        System.out.println("requestURI = " + requestURI);
        System.out.println("contextPath = " + contextPath);
        System.out.println("command = " + command);

        ActionForward forward = null;
        Action action = null;

        switch (command) {
            case "/BoardList.bo":
                action = new BoardListAction();
                break;
            case "/BoardWrite.bo":
                action = new BoardWriteAction();
                break;
        } //switch (command)

        forward = action.execute(request, response);

        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }
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
