/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.springframework.stereotype.Controller
 *  org.springframework.util.FileCopyUtils
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.ResponseBody
 *  org.springframework.web.multipart.MultipartFile
 *  org.springframework.web.servlet.mvc.support.RedirectAttributes
 */
package com.company.spring0716;

import com.company.domain.PhotoCommentVO;
import com.company.service.PhotoCommentService;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value={"/photocomment/*"})
public class PhotoCommentController {
    @Inject
    private PhotoCommentService service;

    @RequestMapping(value={"photoComment"}, method={RequestMethod.GET})
    public void photoComment() {
    }

    @RequestMapping(value={"recommendComment"}, method={RequestMethod.GET})
    public void recommendComment() {
    }

    @RequestMapping(value={"comment_list_json"}, method={RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Object> selectAllList(@RequestParam(value="check") String ck, @RequestParam(value="ckno") int ckno) throws Exception {
        List<Object> list = this.service.selectAllComment();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ck", ck);
        map.put("ckno", ckno);
        list.add(map);
        return list;
    }

    @RequestMapping(value={"comment_limit_json"}, method={RequestMethod.GET}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public List<Object> selectLimitList(@RequestParam(value="check") String ck, @RequestParam(value="ckno") int ckno) throws Exception {
        List<Object> list = this.service.selectLimitComment();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ck", ck);
        map.put("ckno", ckno);
        list.add(map);
        return list;
    }

    @RequestMapping(value={"comment_insert"}, method={RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> insertComment(MultipartFile file, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception {
        UUID uid = UUID.randomUUID();
        String savedName = String.valueOf(uid.toString()) + "_" + file.getOriginalFilename();
        String uploadPath = String.valueOf(request.getSession().getServletContext().getRealPath("/")) + "upload";
        System.out.println("uploadPath : " + uploadPath);
        File target = new File(uploadPath, savedName);
        FileCopyUtils.copy((byte[])file.getBytes(), (File)target);
        String writer = request.getParameter("writer");
        String pass = request.getParameter("pass0");
        String secret = request.getParameter("secret");
        String comment = request.getParameter("comment");
        PhotoCommentVO vo = new PhotoCommentVO();
        vo.setWriter(writer);
        vo.setPass(pass);
        vo.setSecret(secret);
        vo.setComment(comment);
        vo.setFile(savedName);
        this.service.insertComment(vo);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("success", "댓글을 입력했습니다.");
        return map;
    }

    @RequestMapping(value={"checkPass"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> checkPassComment(PhotoCommentVO vo, @RequestParam(value="check") String check, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (this.service.checkPass(vo.getCno()).getPass().equals(vo.getPass())) {
            map.put("ck", check);
            map.put("ckno", vo.getCno());
            map.put("check", "비밀번호를 확인 성공");
        } else {
            map.put("check", "비밀번호를 확인해주세요");
        }
        return map;
    }

    @RequestMapping(value={"comment_update"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> updateComment(MultipartFile file, @RequestParam(value="orginfile") String orginfile, @RequestParam(value="comment") String comment, @RequestParam(value="no") int cno, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PhotoCommentVO vo = new PhotoCommentVO();
        if (file.getSize() != 0L) {
            UUID uid = UUID.randomUUID();
            String savedName = String.valueOf(uid.toString()) + "_" + file.getOriginalFilename();
            String uploadPath = String.valueOf(request.getSession().getServletContext().getRealPath("/")) + "upload";
            File target = new File(uploadPath, savedName);
            FileCopyUtils.copy((byte[])file.getBytes(), (File)target);
            vo.setFile(savedName);
        } else {
            vo.setFile(orginfile);
        }
        vo.setComment(comment);
        vo.setCno(cno);
        HashMap<String, Object> map = new HashMap<String, Object>();
        this.service.updateComment(vo);
        map.put("confirm", "댓글을 수정했습니다.");
        return map;
    }

    @RequestMapping(value={"comment_delete"}, method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json;charset=utf-8"})
    @ResponseBody
    public Map<String, Object> deleteComment(@RequestParam(value="cno") int cno, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        this.service.deleteComment(cno);
        map.put("del", "댓글을 삭제했습니다.");
        return map;
    }
}
