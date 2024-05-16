package net.member.action;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

import java.io.IOException;
import java.io.PrintWriter;

public class MemberUpdateProcessAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MemberDAO mdao = new MemberDAO();
        Member m = new Member();
        ActionForward forward = new ActionForward();

        String saveFolder = "memberupload";

        int filesize = 5 * 1024 * 1024;

//        ServletContext sc = request.getServletContext();
//        String realFolder = sc.getRealPath(saveFolder);
        String realFolder = "\\\\C:\\test\\memberupload";
        System.out.println("realFolder = " + realFolder);

        try {
            MultipartRequest multi = new MultipartRequest(
                    request,
                    realFolder,
                    filesize,
                    "utf-8",
                    new DefaultFileRenamePolicy());

            m.setId(multi.getParameter("id"));
            m.setName(multi.getParameter("name"));
            m.setAge(Integer.parseInt(multi.getParameter("age")));
            m.setGender(multi.getParameter("gender"));
            m.setEmail(multi.getParameter("email"));

            String memberfile = multi.getFilesystemName("memberfile");
            if (memberfile != null) {
                m.setMemberfile(memberfile);
            } else {
                m.setMemberfile(multi.getParameter("check"));
            }

            boolean isUpdateSuccess = mdao.updateMember(m);

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            if (isUpdateSuccess) {
                out.println("   alert('회원정보를 수정했습니다.');");
                out.println("   location.href='BoardList.bo';");
            } else {
                out.println("   alert('회원정보 수정에 실패했습니다.');");
                out.println("   history.back();");
            }
            out.println("</script>");
            out.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            forward.setPath("error/error.jsp");
            request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
            forward.setRedirect(false);
            return forward;
        }
    }
}
