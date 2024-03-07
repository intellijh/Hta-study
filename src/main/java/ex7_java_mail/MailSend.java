package ex7_java_mail;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet("/mailSend")
public class MailSend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String sender = request.getParameter("sender");
        String receiver = request.getParameter("receiver");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        /*
            JavaMail을 사용하여 이메일을 전송하기 위한 3가지 절차는 다음과 같습니다.
            1. 호스트이름, 사용자명, 비밀번호 등과 같은 호스트의 모든 정보를 저장하는 Session 객체를 얻습니다.
            2. 메시지를 구성합니다.
            3. 메시지를 보냅니다.
        */
        //SMTP 서버 주소를 지정합니다.
        String server = "smtp.naver.com";
        int port = 587;

        try {
            //서버 정볼르 Properties 객체에 저장합니다.
            Properties properties = new Properties();

            //SMTP 서버 정보 설정
            //네이버일 경우 smtp.naver.com
            properties.put("mail.smtp.host", server);
            properties.put("mail.smtp.port", port);

            //SSL(Secure Sockets Layer)과 같은 TLS(전송 계층 보안-Transport Layer Security)는
            //네트워크를 통해 전송될 때 데이터의 보안을 유지하기 위한 암호화 프로토콜입니다
            //메일 전송에 사용되는 프로토콜을 TLSv1.2 로 지정합니다.
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

            //저장한 Properties객체의 값으로 세션의 인스턴스를 생성합니다.
            //public static Session getInstance(Properties props)
            //Session 클래스는 사용자의 이름, 암호, 메일 서버와 같이
            //메일 서버를 이용하는데 필요한 이메일 계정 정보를 관리합니다.
            Session s = Session.getInstance(properties);

            //보내는 메일과 받는 주소를 설정합니다.
            Address sender_address = new InternetAddress(sender);
            Address receiver_address = new InternetAddress(receiver);

            //메일을 보내기 위한 정보를 입력하기 위해 Message 객체를 생성합니다.
            Message message = new MimeMessage(s);

            //보내는 메일의 내용이 한글일 경우 깨지지 않도록 content-type을 지정합니다.
            message.setHeader("content-type", "text/html;charset=utf-8");

            //보내는 메일 주소 정보를 설정합니다.
            message.setFrom(sender_address);

            //받는 메일 주소 정보를 설정합니다.
            //Message.RecipientType은 Message 클래스에서 허용하는 수신자 유형을 정의합니다.
            //Message.RecipientType.TO : 받는 사람을 의미합니다.
            message.addRecipient(Message.RecipientType.TO, receiver_address);

            //제목 정보를 설정합니다.
            message.setSubject(subject);

            //내용 정보를 설정합니다.
            message.setContent(content, "text/html;charset=utf-8");

            //보내는 날짜를 설정합니다.
            message.setSentDate(new java.util.Date());

            //public Transport getTransport(String protocol) throws NoSuchProviderException
            //지정된 프로토콜(smtp)을 구현하는 Transport 객체를 가져옵니다.
            //메일을 전송하는 클래스 입니다.
            Transport transport = s.getTransport("smtp");

            //첫번째 인자는 연결할 호스트입니다.
            //두번째 인자는 아이디 세번째 인자는 비밀번호를 의미합니다.
            transport.connect(server, MyInfo.naverid, MyInfo.naverpass);

            //getAllRecipients() : 메세지의 모든 수신자 주소를 가져옵니다.
            //지정한 주소 목록으로 메세지를 보냅니다.
            transport.sendMessage(message, message.getAllRecipients());

            //연결을 종료합니다.
            transport.close();

            out.println("<h3>메일이 정상적으로 전송되었습니다.</h3>");
        } catch (Exception e) {
            out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.");
            e.printStackTrace();
        }
    }
}
