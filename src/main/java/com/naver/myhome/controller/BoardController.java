package com.naver.myhome.controller;

import com.naver.myhome.domain.Board;
import com.naver.myhome.service.BoardService;
import com.naver.myhome.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Value("${my.savefolder}")
    private String saveFolder;

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    private final CommentService commentService;

    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/list")
    public ModelAndView boardList(@RequestParam(defaultValue = "1") int page,
                                  ModelAndView mv,
                                  HttpSession session) {

        int limit = 10; //한 화면에 출력할 로우 갯수
        int listCount = boardService.getListCount(); //총 리스트 수를 받아옴

        int maxPage = (listCount + limit - 1) / limit; //총 페이지 수
        int startPage = (page - 1) / 10 * 10 + 1; //현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 ...)
        int endPage = startPage + 10 - 1; //현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30 ...)

        if (endPage > maxPage) {
            endPage = maxPage;
        }

        List<Board> boardList = boardService.getBoardList(page, limit);

        session.setAttribute("readCheck", "readCheck");
        mv.setViewName("board/boardList");
        mv.addObject("boardList", boardList);
        mv.addObject("limit", limit);
        mv.addObject("listCount", listCount);
        mv.addObject("page", page);
        mv.addObject("maxPage", maxPage);
        mv.addObject("startPage", startPage);
        mv.addObject("endPage", endPage);
        return mv;
    }

    @GetMapping("/write")
    public String write() {
        return "board/boardWrite";
    }

    /*
        스프링 컨테이너는 매개변수Board객체를 생성하고
        Board객체의 setter 메서드들을 호출하여 사용자 입력값을 설정합니다.
        매개변수의 이름과 setter의 property가 일치하면 됩니다.
    * */
    @PostMapping("/add")
    public String add(Board board, HttpServletRequest request) throws Exception {

        MultipartFile uploadfile = board.getUploadfile();

        if (!uploadfile.isEmpty()) {
            String fileName = uploadfile.getOriginalFilename(); //원래 파일명
            board.setBoard_original(fileName); //원래 파일명 저장
//            String saveFolder = request.getSession().getServletContext().getRealPath("resources" + "/upload");

            String fileDBName = fileDBName(fileName, saveFolder);
            logger.info("fileDBName: " + fileDBName);

            //transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
            uploadfile.transferTo(new File(saveFolder + fileDBName));
            logger.info("tranferTo path: " + saveFolder + fileDBName);

            //바뀐 파일명으로 저장
            board.setBoard_file(fileDBName);
        }

        boardService.insertBoard(board); //저장 메서드 호출
        logger.info(board.toString()); //selectKey 로 정의한 board_num 값 확인
        return "redirect:list";
    }

    private String fileDBName(String fileName, String saveFolder) {

        //새로운 폴더 이름 :오늘 년+월+일
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateTimeFormatter.format(LocalDate.now());

        String homedir = saveFolder + "/" + date;
        logger.info(homedir);
        File path1 = new File(homedir);
        if (!(path1.exists())) {
            path1.mkdirs(); //새로운 폴더 생성
        }

        Random r = new Random();
        int random = r.nextInt(100000000);

        //홗장자 구하기 시작
        int index = fileName.lastIndexOf(".");
        /*
            문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
            indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
            lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
            (파일명에 점이 여려개 있을 경우 맨 마지막에 발견되는 묹나열의 위치를 리턴합니다.)
        * */
        logger.info("index: " + index);

        String fileExtension = fileName.substring(index + 1);
        logger.info("fileExtension: " + fileExtension);
        //확자자 구하기 끝

        //새로운 파일명
        String refileName = "bbs" + date + random + "." + fileExtension;
        logger.info("refileName: " + refileName);

        //오라클 디비에 저장될 파일명
        //String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
        String fileDBName = File.separator + date + File.separator + refileName;
        logger.info("fileDBName: " + fileDBName);

        return fileDBName;
    }

    @ResponseBody
    @RequestMapping("/list_ajax")
    public Map<String, Object> boardListAjax(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "10") int limit) {

        int listCount = boardService.getListCount(); //총 리스트 수를 받아옴

        int maxPage = (listCount + limit - 1) / limit; //총 페이지 수
        int startPage = (page - 1) / 10 * 10 + 1; //현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 ...)
        int endPage = startPage + 10 - 1; //현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30 ...)

        if (endPage > maxPage) {
            endPage = maxPage;
        }

        List<Board> boardList = boardService.getBoardList(page, limit);

        Map<String, Object> map = new HashMap<>();
        map.put("boardList", boardList);
        map.put("listCount", listCount);
        map.put("limit", limit);
        map.put("page", page);
        map.put("maxPage", maxPage);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        return map;
    }

    @GetMapping("/detail")
    public ModelAndView detail(int num,
                               ModelAndView mv,
                               HttpServletRequest request,
                               HttpSession session,
                               @RequestHeader(value = "referer", required = false) String beforeURL) {

        /*
            1. String beforeURL = request.getHeader("referer"); 의미로
               어느 주소에서 detail로 이동했는지 header의 정보 중에서 "referer"를 통해 알 수 있습니다.
            2. 수정 후 이곳으로 이동하는 경우 조회수는 증가하지 않도록 합니다.
            3. myhome/board/list 에서 제목을 클릭한 경우 조회수가 증가하도록 합니다.
        * */

        String readCheck = (String) session.getAttribute("readCheck");
        logger.info("referer: " + beforeURL);
        if (beforeURL != null && beforeURL.endsWith("list") && readCheck != null) {
            boardService.setReadCountUpdate(num);
            session.removeAttribute("readCheck");
        }

        Board board = boardService.getDetail(num);
//        board=null; //error 페이지 이동 확인하고자 임의로 지정합니다.
        if (board == null) {
            logger.info("상세보기 실패");
            mv.setViewName("error/error");
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "상세보기 실패입니다.");
        } else {
            logger.info("상세보기 성공");
            int count = commentService.getListCount(num);
            mv.setViewName("board/boardView");
            mv.addObject("count", count);
            mv.addObject("boarddata", board);
        }
        return mv;
    }

    @GetMapping("/modifyView")
    public ModelAndView modify(int num,
                               ModelAndView mv,
                               HttpServletRequest request) {

        Board boarddata = boardService.getDetail(num);

        if (boarddata == null) {
            logger.info("수정보기 실패");
            mv.setViewName("error/error");
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "수정보기 실패입니다.");
        } else {
            mv.setViewName("board/boardModify");
            mv.addObject("boarddata", boarddata);
        }
        return mv;
    }

    @PostMapping("/modifyAction")
    public String boardModifyAction(Board boarddata,
                                    String check,
                                    Model model,
                                    HttpServletRequest request,
                                    RedirectAttributes rattr) throws Exception {

        boolean usercheck = boardService.isBoardWriter(boarddata.getBoard_num(), boarddata.getBoard_pass());
        String url = "";
        logger.info("usercheck: " + usercheck);
        //비밀번호가 다른 경우
        if (usercheck == false) {
            rattr.addFlashAttribute("result", "passFail");
            rattr.addAttribute("num", boarddata.getBoard_num());
            return "redirect:/modifyView";
        }

        MultipartFile uploadfile = boarddata.getUploadfile();
//        String saveFolder = request.getSession().getServletContext().getRealPath("resources")
//                + "/upload";

        if (check != null && !check.isEmpty()) {
            logger.info("기존 파일 그대로 사용합니다.");
            boarddata.setBoard_original(check);
            //<input type="hidden" name="board_file" value="${boarddata.board_file}">
            //위 문장 때문에 board.setBoard_file 값은 자동 저장됩니다.
        } else {
            //답변글의 경우 파일 첨부에 대한 기능이 없습니다.
            //만약 답변글을 수정할 경우
            //<input type="file" id="upfile" name="uploadfile"> 엘리먼트가 존재하지 않아
            //private MultipartFile uploadfile;에서 uploadfile은 null 입니다.
            if (uploadfile != null && !uploadfile.isEmpty()) {
                logger.info("파일 변경되었습니다.");

                String fileName = uploadfile.getOriginalFilename();
                boarddata.setBoard_original(fileName);

                String fileDBName = fileDBName(fileName, saveFolder);
                logger.info("fileDBName = " + fileDBName);
                //transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
                uploadfile.transferTo(new File(saveFolder + fileDBName));
                logger.info("transferTo path = " + saveFolder + fileDBName);
                //바뀐 파일명으로 저장
                boarddata.setBoard_file(fileDBName);
            } else { //기존 파일이 없는데 파일 선택하지 않은 경우 또는 기존 파일이 있었는데 삭제한 경우
                logger.info("선택 파일이 없습니다.");
                //<input type="hidden" name="board_file" value="${boarddata.board_file}">
                //위 태그에 값이 없다면 ""로 값을 변경합니다.
                boarddata.setBoard_file("");
                boarddata.setBoard_original("");
            }
        }

        int result = boardService.boardModify(boarddata);
        //수정에 실패한 경우
        if (result == 0) {
            logger.info("게시판 수정 실패");
            model.addAttribute("url", request.getRequestURL());
            model.addAttribute("message", "게시판 수정 실패");
            url = "error/error";
        } else { //수정 성공인 경우
            logger.info("게시판 수정 완료");
            //수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
            url = "redirect:detail";
            rattr.addAttribute("num", boarddata.getBoard_num());
        }
        return url;
    }

    @GetMapping("/replyView")
    public ModelAndView reply(int num,
                               ModelAndView mv,
                               HttpServletRequest request) {

        Board boarddata = boardService.getDetail(num);

        if (boarddata == null) {
            mv.setViewName("error/error");
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "게시판 답변글 가져오기 실패");
        } else {
            mv.setViewName("board/boardReply");
            mv.addObject("boarddata", boarddata);
        }
        return mv;
    }

    @PostMapping("/replyAction")
    public ModelAndView boardReplyAction(Board board,
                                         ModelAndView mv,
                                         HttpServletRequest request,
                                         RedirectAttributes rattr) {

        int result = boardService.boardReply(board);
        if (result == 0) {
            mv.setViewName("error/error");
            mv.addObject("url", request.getRequestURL());
            mv.addObject("message", "게시판 답변 처리실패");
        } else {
            mv.setViewName("redirect:detail");
//            mv.setViewName("redirect:detail?num=" + board.getBoard_num());
            rattr.addAttribute("num", board.getBoard_num());
        }
        return mv;
    }

    @PostMapping("/delete")
    public String delete(int num,
                         String board_pass,
                         HttpServletRequest request,
                         RedirectAttributes rattr,
                         Model model) {

        boolean isBoardWriter = boardService.isBoardWriter(num, board_pass);
        if (!isBoardWriter) {
            rattr.addFlashAttribute("result", "passFail");
            rattr.addAttribute("num", num);
            return "redirect:detail";
        }

        int result = boardService.boardDelete(num);
        if (result == 0) {
            model.addAttribute("url", request.getRequestURL());
            model.addAttribute("message", "게시판 삭제 처리실패");
            return "error/error";
        } else {
            logger.info("게시판 삭제 성공");
            rattr.addFlashAttribute("result", "deleteSuccess");
            return "redirect:list";
        }
    }

    @ResponseBody
    @PostMapping("/down")
    public byte[] boardFileDown(String filename,
                                HttpServletRequest request,
                                String original,
                                HttpServletResponse response) throws Exception {

//        String savePath = "resources/upload";
        //서블릿의 실행 환경 정보를 담고있는 객체를 리턴합니다.
//        ServletContext context = request.getSession().getServletContext();
//        String sDownloadPath = context.getRealPath(savePath);

        String sFilePath = saveFolder + filename;

        File file = new File(sFilePath);

        byte[] bytes = FileCopyUtils.copyToByteArray(file);

        String sEncoding = new String(original.getBytes("utf-8"), "iso-8859-1");

        //Context-Disposition: attachment: 브라우저는 해당 Content를 처리하지 않고 다운로드하게됩니다.
        response.setHeader("Content-Disposition", "attachment; filename=" + sEncoding);
        response.setContentLength(bytes.length);
        return bytes;
    }
}
