package net.member.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.member.db.Member;
import net.member.db.MemberDAO;

import java.io.IOException;
import java.util.List;

public class MemberSearchAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        MemberDAO mdao = new MemberDAO();

        int page = 1;
        int limit = 3;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        System.out.println("넘어온 page = " + page);

        List<Member> list = null;
        int listcount = 0;
        int index = -1; //search_field에 존재하지 않는 값으로 초기화

        String search_word = "";

        //메뉴-관리자-회원정보 클릭한 경우(memberList.net)
        //또는 메뉴-관리자-회원정보 클릭 후 페이지 클릭한 경우
        //(memberList.net?page=2&search_field=-1&search_word=)
        if (request.getParameter("search_word") == null
                || request.getParameter("search_word").equals("")) {
            //총 리스트 수를 받아옵니다.
            listcount = mdao.getListCount();
            list = mdao.getList(page, limit);
        } else { //검색을 클릭한 경우
            index = Integer.parseInt(request.getParameter("search_field"));
            String[] search_field = {"id", "name", "age", "gender"};
            search_word = request.getParameter("search_word");

            listcount = mdao.getListCount(search_field[index], search_word);
            System.out.println("listcount = " + listcount);
            list = mdao.getList(search_field[index], search_word, page, limit);
        }

        int maxpage = (listcount + limit - 1) / limit;
        System.out.println("총 페이지 수 maxpage = " + maxpage);

        int startpage = ((page - 1) / 10) * 10 + 1;
        int endpage = startpage + 10 - 1;
        System.out.println("현재 페이지에 보여줄 마지막 페이지 endpage = " + endpage);
        System.out.println("현재 페이지에 보여줄 시작 페이지 startpage = " + startpage);

        if (endpage > maxpage) {
            endpage = maxpage;
        }
        
        request.setAttribute("page", page); //현재 페이지 수
        request.setAttribute("maxpage", maxpage); //최대 페이지 수
        
        //현재 페이지에 표시할 첫 페이지 수
        request.setAttribute("startpage", startpage);
        
        //현재 페이지에 표시할 마지막 페이지 수
        request.setAttribute("endpage", endpage);
        
        request.setAttribute("listcount", listcount); //총 글의 수
        request.setAttribute("totallist", list);
        request.setAttribute("search_field", index);
        request.setAttribute("search_word", search_word);

        forward.setPath("member/memberList.jsp");
        forward.setRedirect(false);
        return forward;
    }
}
