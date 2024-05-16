package ex6_filedown;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//   /JSP/fileUpload
@WebServlet("/down")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fileName = request.getParameter("file_name");
		System.out.println("[fileName] = " + fileName);
		
		String savePath = "upload";
		
		// 서블릿의 실행 환경 정보를 담고 있는 객체를 리턴합니다.
		ServletContext context = request.getSession().getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		// Windows 운영 체제에서는 디렉토리 구분자로 역슬래시(\)를 사용하고,
		// UNIX 또는 Linux 기반의 운영 체제에서는 슬래시(/)를 사용합니다.
		// File.separator를 이용해서 운영체제에 맞게 설정합니다.
		String sFilePath = sDownloadPath + File.separator + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		// sFilePath에 있는 파일의 MimeType을 구해옵니다.
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);
		/*
		 *  1. Content-Type: 전송되는 Content가 어떤 유형인지 알려주는 목적을 가지고 있습니다.
		 *    - text/html, image/png, application/octet-stream 등의 값을 가집니다.
		 *  2. Content-Type을 통해서 브라우저는 해당 데이터를 어떻게 처리해야 할 지 판단할 수
		 *  	있게 됩니다.
		 *  	(예)
		 *  		1) image/png: 브라우저는 해당 컨텐트를 이미지로써 간주하게 됩니다.
		 *  		2) application/octet-stream: 미확인 Binary 정보를 의미하며,
		 *  		   이 경우 브라우저는 파일을 다운로드하는 형태로 동작합니다.
		 *  		3) text/javascript: 브라우저는 Content를 javascript문서로 취급하게 됩니다.
		 */
		
		/*
		 * octet-stream은 8비트로 된 데이터를 의미하며 지정되지 않은 파일 형식을 의미합니다.
		 */
		
		if (sMimeType == null) 
			sMimeType = "application/octet-stream";
		
		response.setContentType(sMimeType);
		
		/*
		- 이 부분이 한글 파일명이 깨지는 것을 방지해 줍니다.
		- getBytes(캐릭터셋): 자바 내부에 관리되는 유니코드 문자열을 인자로
						    지정된 캐릭터셋의 바이트 배열로 반환하는 메서드입니다.
		- String(byte[] bytes, Charset charset)
		  String(바이트배열, 캐릭터셋) 생성자 : 해당 바이트 배열을 주어진 캐릭터 셋으로 
		  									간주하여 스트링을 만드는 생성자입니다.
		*/
		
		String sEncoding = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		
		/*
		 - Content-Disposition: Content가 어떻게 처리되어야 하는지 나타냅니다.
		   1) Content-Disposition:
		   	  inline: 브라우저가 Content를 즉시 출력해야 함을 나타냅니다.
		   	  		  이미지인 경우 브라우저 내에서 즉시 출력하며, 문서인 경우 텍스트로 출력합니다.
		   2) Content-Disposition:
		   	  attachment: 브라우저는 해당 Content를 처리하지 않고 다운로드하게 됩니다.
		   	  
		 */
		response.setHeader("Content-Disposition","attachment; filename=" + sEncoding);
		// response.setHeader("Content-Disposition", "inline; filename=" + sEncoding);

		/*
			1. try-with-resource문으로 try()괄호 안에 선먼된 자원은 try문이 끝날 때 자동으로 close()메서드를 호출합니다.

			2. try-with-resource운머 의해 자동으로 각체의 close()가 호출월 수 있으려면 글래스가
				AutoCloseable이라는 인터페이스를 구현한 것이어야 합니다.
				형식) try(){
					} catch(){
					} finally{}
		*/
		try (//웹 브라우저로의 출력 스트림을 생성합니다.
			 BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());

			 //sFilePath로 지정한 파일에 대한 입력 스트림을 생성합니다.
			 BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));) {
			int numRead;

			//read(b, 0, b.length) : 바이트 배열 b의 0번부터 b.length 크기 만큼 읽어옵니다.
			while ((numRead = in.read(b, 0, b.length)) != -1) { //읽을 데이터가 존재하는 경우
				out2.write(b, 0, numRead); //바이트 배열 b의 0번부터 numRead 크기만큼 브라우저로 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}//doPost() end
}//class end