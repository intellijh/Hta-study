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

public class MemberUpdateProcessAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDAO mdao = new MemberDAO();
        Member m = new Member();
        ActionForward forward = new ActionForward();

        String saveFolder = "memberupload";

        int filesize = 5 * 1024 * 1024;

        ServletContext sc = request.getServletContext();
        String realFolder = sc.getRealPath(saveFolder);
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

        } catch (IOException e) {
            e.printStackTrace();
            forward.setPath("error/error.jsp");
            request.setAttribute("message", "정보 수정 중 실패입니다.");
            forward.setRedirect(false);
            return forward;
        }
        return forward;
    }
}
