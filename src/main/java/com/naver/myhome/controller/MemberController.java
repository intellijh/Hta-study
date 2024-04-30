package com.naver.myhome.controller;

import com.naver.myhome.domain.MailVO;
import com.naver.myhome.domain.Member;
import com.naver.myhome.service.MemberService;
import com.naver.myhome.task.SendMail;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/*
    @Component를 이용해서 스프링 컨테이너가 해당 클래스 객체를 생성하도록 설정할 수 있지만
    모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기
    어렵습니다. 스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서
    @Component를 상속하여 다음과 같은 세 개의 애노테이션을 제공합니다.

    1. @Controller - 사용자의 요청을 제어하는 Controller 클래스
    2. @Repository - 데이터 베이스 연동을 처리하는 DAO클래스
    3. @Service - 비즈니스 로직을 처리하는 Service 클래스
* */
@Controller
@RequestMapping("/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final SendMail sendMail;

    @Autowired
    public MemberController(MemberService memberService,
                            PasswordEncoder passwordEncoder,
                            SendMail sendMail) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.sendMail = sendMail;
    }

    /*
        @CookieValue(value="saveid", required=false) Cookie readCookie
        이름이 saveid인 쿠키를 Cookie 타입으로 전달받습니다.
        지정한 이름의 쿠키가 존재하지 않을 수도 있기 때문에 required=false로 설정해야 합니다.
        즉, id 기억하기를 선택하지 않을 수도 있기 때문에 required=false로 설정해야 합니다.
        required=true 상태에서 지정한 이름을 가진 쿠키가 존재하지 않으면 스프링 MVC는 익셉션을 발생시킵니다.
    * */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv,
                              @CookieValue(value = "remember-me", required = false) Cookie readCookie,
                              HttpSession session,
                              Principal userPrincipal) {

        if (readCookie != null) {
            //userPrincipal.getName() : 로그인한 아이디 값을 알 수 있음
            logger.info("저장된 아이디 : " + userPrincipal.getName());
            mv.setViewName("redirect:/board/list");
        } else {
            mv.setViewName("member/loginForm");

            //세션에 저장된 값을 한번만 실행될 수 있도록 model에 저장
            mv.addObject("loginfail", session.getAttribute("loginfail"));
            
            session.removeAttribute("loginfail"); //세션 값 제거
        } 
        return mv;
    }

    @GetMapping("/join")
    public String join() {
        return "member/joinForm";
    }

    @ResponseBody
    @GetMapping("/idcheck")
    public int idcheck(@RequestParam String id) {
        return memberService.isId(id);
    }

    @PostMapping("/joinProcess")
    public String joinProcess(Member member,
                              RedirectAttributes rattr,
                              Model model,
                              HttpServletRequest request) {

        //비밀번호 암호화 추가
        String encPassword = passwordEncoder.encode(member.getPassword());
        logger.info(encPassword);
        member.setPassword(encPassword);

        int result = memberService.insert(member);

        /*
            스프링에서 제공하는 RedirectAttributes 는 기존의 Servlet 에서 사용되던
            response.sendRedirect()를 사용할 때와 동일한 용도로 사용합니다.
            리다이렉트로 전송하면 파라미터를 전달하고자 할 때 addAttribute()나 addFlashAttribute()를 사용합니다.
            예) response.sendRedirect("/test?result=1");
                => rattr.addAttribute("result",1)"
        * */
        if (result == 1) {
//            MailVO vo = new MailVO();
//            vo.setTo(member.getEmail());
//            vo.setContent(member.getId() + "님 회원가입을 축하드립니다.");
//            sendMail.sendMail(vo);

            rattr.addFlashAttribute("result", "joinSuccess");
            return "redirect:/member/login";
        } else {
            model.addAttribute("url", request.getRequestURL());
            model.addAttribute("message", "회원가입 실패");
            return "error/error";
        }
    }

    @GetMapping("/update")
    public ModelAndView update(Principal principal, ModelAndView mv) {

        String id = principal.getName();
        if (id == null) {
            mv.setViewName("redirect:/member/login");
            logger.info("id is null");
        } else {
            Member member = memberService.member_info(id);
            mv.setViewName("member/updateForm");
            mv.addObject("memberinfo", member);
        }
        return mv;
    }

    @PostMapping("/updateProcess")
    public String updateProcess(Member member,
                                Model model,
                                HttpServletRequest request,
                                RedirectAttributes rattr) {

        int result = memberService.update(member);
        if (result == 1) {
            rattr.addFlashAttribute("result", "updateSuccess");
            return "redirect:/board/list";
        } else {
            model.addAttribute("url", request.getRequestURL());
            model.addAttribute("message", "정보 수정 실패");
            return "error/error";
        }
    }

    /*
        1. header.jsp에서 이동하는 경우
           href="${pageContext.request.contextPath}/member/list"
        2. member_list.jsp에서 이동하는 경우
           <a href="list?page=2&search_field =- 1&search_word=" class="page-link">2</a>
    * */
    @RequestMapping("/list")
    public ModelAndView memberList(ModelAndView mv,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "3") int limit,
                                   @RequestParam(defaultValue = "-1") int search_field,
                                   @RequestParam(defaultValue = "") String search_word) {

        int listcount = memberService.getSearchListCount(search_field, search_word);

        List<Member> list = memberService.getSearchList(search_field, search_word, page, limit);

        int maxpage = (listcount - 1) / limit + 1;

        int startpage = ((page - 1) / 10) * 10 + 1;

        int endpage = startpage + 10 - 1;

        if (endpage > maxpage) {
            endpage = maxpage;
        }


        mv.setViewName("member/memberList");
        mv.addObject("page", page);
        mv.addObject("maxpage", maxpage);
        mv.addObject("startpage", startpage);
        mv.addObject("endpage", endpage);
        mv.addObject("listcount", listcount);
        mv.addObject("memberlist", list);
        mv.addObject("search_field", search_field);
        mv.addObject("search_word", search_word);
        return mv;
    }

    @GetMapping("/info")
    public ModelAndView info(String id,
                             ModelAndView mv,
                             HttpServletRequest request) {
        Member member = memberService.member_info(id);
        if (member == null) {
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "해당 정보가 없습니다.");
            mv.setViewName("error/error");
        } else {
            mv.addObject("memberinfo", member);
            mv.setViewName("member/memberInfo");
        }

        return mv;
    }

    @GetMapping("/delete")
    public String delete(String id) {
        memberService.delete(id);
        return "redirect:/member/list";
    }
}
