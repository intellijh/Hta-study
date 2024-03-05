package ex1._1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/*
* 서블릿을 정의합니다.
* - 서블릿 클래스를 정의하려면 반드시 HttpServlet 클래스를 상속합니다.
* - HttpServlet 클래스에 서블릿에 관한 일반적인 기능이 정의되어 있기 때문에
* HttpServlet 클래스를 상속받은 자식 클래스 또한 서블릿 클래스가 됩니다.
*/
public class ServletTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // doGet 메소드를 정의하는 부분입니다.
    // 클라이언트에서 요청이 GET 방식으로 전송되어오면 doGet 메소드가 자동 실행되게 됩니다.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Java Servlet에서 Http 응답 헤더의 Content-Type 값을 설정하는 코드입니다.
        // 응답 데이터의 MIME 타입을 HTML 타입의 text로 지정합니다.
        response.setContentType("text/html");

        // 응답 타입의 문자 인코딩 타입을 한글이 제대로 추력되도록 "UTF-8"로 지정합니다.
        response.setCharacterEncoding("UTF-8");

        // 위 두 문장을 한번에 작성하면 다음과 같습니다.
        //response.setContentType("text/html;charset=UTF-8");

        //Calendar 객체를 생성하여 객체로부터 시간, 분, 초 값을 얻어옵니다.
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        // 응답에 내용을 출력할 출력 스트림을 생성합니다.
        PrintWriter out = response.getWriter();

        // 클라이언트로 응답할 내용을 HTML타입의 데이터로 출력하는 부분입니다.
        out.write("<HTML><HEAD><TITLE>ServletTest</TITLE></HEAD>");
//        out.write("<style>body {background: 'yellow';}</style>");
        out.write("<BODY style='background:green'><H1>");
        out.write("현재 시각은 ");
        out.write(Integer.toString(hour));
        out.write("시 ");
        out.write(Integer.toString(minute));
        out.write("분 ");
        out.write(Integer.toString(second));
        out.write("초입니다.");
        out.write("</H1></BODY></HTML>");
        out.close();
    }
}
