/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  org.springframework.stereotype.Controller
 *  org.springframework.ui.Model
 *  org.springframework.util.FileCopyUtils
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.ResponseBody
 *  org.springframework.web.multipart.MultipartFile
 *  org.springframework.web.servlet.mvc.support.RedirectAttributes
 */
package com.company.spring0716;

import com.company.domain.BoardVO;
import com.company.domain.CommentVO;
import com.company.service.BoardService;
import com.company.service.CommentService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value={"/board/*"})
public class HomeController {
    @Inject
    private BoardService Board;
    @Inject
    private CommentService Comment;

    @RequestMapping(value={"/logout"}, method={RequestMethod.GET})
    public void logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("location.href='http://adnerwin.cafe24.com/test/loginBoard/logout.jsp';");
        out.println("</script>");
    }

    @RequestMapping(value={"/list"}, method={RequestMethod.GET})
    public String board_list(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            model.addAttribute("id", (Object)id);
            session.setAttribute("id", (Object)id);
        }
        return "/board/list";
    }

    @RequestMapping(value={"/list_json"}, method={RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Object> selectAllBoard(Model model, @RequestParam(value="pstartno") int pstartno) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<Object> list = this.Board.select10List(pstartno);
        int listcount = this.Board.selectCount();
        int onepagelimit = 12;
        int pagetotal = (int)Math.ceil((float)listcount / (float)onepagelimit);
        int bottomlimit = 10;
        int currentpage = (int)Math.ceil((float)(pstartno + 1) / (float)onepagelimit);
        int startpage = (int)Math.floor((float)(currentpage - 1) / (float)bottomlimit) * bottomlimit + 1;
        int endpage = startpage + bottomlimit - 1;
        if (pagetotal < endpage) {
            endpage = pagetotal;
        }
        map.put("listcount", listcount);
        map.put("onepagelimit", onepagelimit);
        map.put("pagetotal", pagetotal);
        map.put("bottomlimit", bottomlimit);
        map.put("pstartno", pstartno);
        map.put("currentpage", currentpage);
        map.put("startpage", startpage);
        map.put("endpage", endpage);
        list.add(map);
        return list;
    }

    @RequestMapping(value={"/board_write"}, method={RequestMethod.GET})
    public String board_write_form(HttpServletRequest request, HttpServletResponse response, Model model) {
        String id = request.getParameter("id") != null ? request.getParameter("id") : "";
        model.addAttribute("bid", (Object)id);
        return "/board/board_write";
    }

    @RequestMapping(value={"/board_write"}, method={RequestMethod.POST})
    public String board_write(BoardVO vo, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(required=false) List<MultipartFile> mulfile) throws Exception {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Iterator<MultipartFile> iter = mulfile.iterator();
        HashMap<String, String> map = new HashMap<String, String>();
        if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            model.addAttribute("id", (Object)id);
            session.setAttribute("id", (Object)id);
        }
        int no = 0;
        String fileName = "";
        while (iter.hasNext()) {
            MultipartFile file = iter.next();
            UUID uid = UUID.randomUUID();
            String savedName = String.valueOf(uid.toString()) + "_" + file.getOriginalFilename();
            String path = request.getSession().getServletContext().getRealPath("/");
            String upload = "upload";
            String uploadPath = String.valueOf(path) + upload;
            File target = new File(uploadPath, savedName);
            FileCopyUtils.copy((byte[])file.getBytes(), (File)target);
            map.put("file" + no, savedName);
            fileName = String.valueOf(fileName) + savedName + "/";
            ++no;
        }
        vo.setBfile(fileName);
        rttr.addFlashAttribute("success", (Object)"글 작성 성공");
        vo.setBcontent(vo.getBcontent().replace("\r\n", "<br>"));
        this.Board.insertBoard(vo);
        return "redirect:/board/list";
    }

    @RequestMapping(value={"/board_detail"}, method={RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    public void board_detail_form(@RequestParam(value="bno") int bno, Model model) throws Exception {
        this.Board.updateHit(bno);
        model.addAttribute("vo", (Object)this.Board.detailBoard(bno));
    }

    @RequestMapping(value={"/board_modify"}, method={RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    public void board_modify_form(@RequestParam(value="bno") int bno, Model model) throws Exception {
        BoardVO vo = this.Board.detailBoard(bno);
        vo.setBcontent(vo.getBcontent().replace("<br>", "\r\n"));
        model.addAttribute("vo", (Object)vo);
    }

    @RequestMapping(value={"/board_modify"}, method={RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    public void board_modify(BoardVO vo, HttpServletResponse response, @RequestParam(value="bfile") String bfile, HttpServletRequest request, @RequestParam(required=false) List<MultipartFile> mulfile) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        Iterator<MultipartFile> iter = mulfile.iterator();
        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println(mulfile.size());
        if (mulfile.size() != 0) {
            int no = 0;
            String fileName = "";
            while (iter.hasNext()) {
                MultipartFile file = iter.next();
                UUID uid = UUID.randomUUID();
                String savedName = String.valueOf(uid.toString()) + "_" + file.getOriginalFilename();
                String path = request.getSession().getServletContext().getRealPath("/");
                String upload = "upload";
                String uploadPath = String.valueOf(path) + upload;
                File target = new File(uploadPath, savedName);
                FileCopyUtils.copy((byte[])file.getBytes(), (File)target);
                map.put("file" + no, savedName);
                fileName = String.valueOf(fileName) + savedName + "/";
                ++no;
            }
            vo.setBfile(fileName);
        } else {
            vo.setBfile(bfile);
        }
        vo.setBcontent(vo.getBcontent().replace("\r\n", "<br>"));
        PrintWriter out = response.getWriter();
        if (this.Board.checkboardPass(vo.getBno()).getBpass().equals(vo.getBpass())) {
            out.println("<script>");
            out.println("alert('글 수정 성공');");
            out.println("location.href='" + request.getContextPath() + "/board/board_detail?bno=" + vo.getBno() + "';");
            out.println("</script>");
            this.Board.updateBoard(vo);
        } else {
            out.println("<script>");
            out.println("alert('비밀번호를 확인해주세요');");
            out.println("history.go(-1);");
            out.println("</script>");
        }
    }

    @RequestMapping(value={"/board_delete"}, method={RequestMethod.GET})
    public void board_delete_form(@RequestParam(value="bno") int bno, Model model) {
        model.addAttribute("bno", (Object)bno);
    }

    @RequestMapping(value={"/board_delete"}, method={RequestMethod.POST})
    public void board_delete(BoardVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if (this.Board.checkboardPass(vo.getBno()).getBpass().equals(vo.getBpass())) {
            out.println("<script>");
            out.println("alert('글 삭제 성공');");
            out.println("location.href='" + request.getContextPath() + "/board/list';");
            out.println("</script>");
            this.Board.deleteBoard(vo.getBno());
        } else {
            out.println("<script>");
            out.println("alert('비밀번호를 확인해주세요');");
            out.println("history.go(-1);");
            out.println("</script>");
        }
    }

    @RequestMapping(value={"/search_json"}, method={RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Object> selectSearchBoard(@RequestParam(value="search") String search, @RequestParam(value="pstartno") int pstartno) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        BoardVO vo = new BoardVO();
        vo.setBtitle(search.trim());
        vo.setBno(pstartno);
        List<Object> list = this.Board.searchBoard(vo);
        int listcount = this.Board.searchCount(search.trim());
        int onepagelimit = 10;
        int pagetotal = (int)Math.ceil((float)listcount / (float)onepagelimit);
        int bottomlimit = 10;
        int currentpage = (int)Math.ceil((float)(pstartno + 1) / (float)onepagelimit);
        int startpage = (int)Math.floor((float)(currentpage - 1) / (float)bottomlimit) * bottomlimit + 1;
        int endpage = startpage + bottomlimit - 1;
        if (pagetotal < endpage) {
            endpage = pagetotal;
        }
        map.put("listcount", listcount);
        map.put("onepagelimit", onepagelimit);
        map.put("pagetotal", pagetotal);
        map.put("bottomlimit", bottomlimit);
        map.put("pstartno", pstartno);
        map.put("currentpage", currentpage);
        map.put("startpage", startpage);
        map.put("endpage", endpage);
        map.put("search", search.trim());
        list.add(map);
        return list;
    }

    @RequestMapping(value={"/comment_list_json"}, method={RequestMethod.GET})
    @ResponseBody
    public List<Object> selectCommentAllList(@RequestParam(value="check") String ck, @RequestParam(value="ckno") int ckno, @RequestParam(value="bno") int bno) throws Exception {
        List<Object> list = this.Comment.selectAllComment(bno);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ck", ck);
        map.put("ckno", ckno);
        list.add(map);
        return list;
    }

    @RequestMapping(value={"/comment_insert"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> insertComment(CommentVO vo, RedirectAttributes rttr) throws Exception {
        this.Comment.insertComment(vo);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("success", "댓글을 입력했습니다.");
        return map;
    }

    @RequestMapping(value={"/checkCommentPass"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> checkPassComment(CommentVO vo, @RequestParam(value="check") String check, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        System.out.println(vo);
        System.out.println(check);
        if (this.Comment.checkCommentPass(vo.getCno()).getPass().equals(vo.getPass())) {
            map.put("ck", check);
            map.put("ckno", vo.getCno());
            map.put("check", "비밀번호를 확인 성공");
        } else {
            map.put("check", "비밀번호를 확인해주세요.");
        }
        return map;
    }

    @RequestMapping(value={"/comment_update"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> updateComment(CommentVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        this.Comment.updateComment(vo);
        map.put("confirm", "댓글을 수정했습니다.");
        return map;
    }

    @RequestMapping(value={"/comment_delete"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> deleteComment(@RequestParam(value="cno") int cno, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        this.Comment.deleteComment(cno);
        map.put("del", "댓글을 삭제했습니다.");
        return map;
    }
}
