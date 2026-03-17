/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.springframework.stereotype.Service
 */
package com.company.service;

import com.company.domain.CommentVO;
import com.company.persistence.CommentDAO;
import com.company.service.CommentService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl
implements CommentService {
    @Inject
    CommentDAO dao;

    @Override
    public int insertComment(CommentVO vo) throws Exception {
        return this.dao.insertComment(vo);
    }

    @Override
    public List<Object> selectAllComment(int bno) throws Exception {
        return this.dao.selectAllComment(bno);
    }

    @Override
    public CommentVO checkCommentPass(int cno) throws Exception {
        return this.dao.checkCommentPass(cno);
    }

    @Override
    public int updateComment(CommentVO vo) throws Exception {
        return this.dao.updateComment(vo);
    }

    @Override
    public int deleteComment(int cno) throws Exception {
        return this.dao.deleteComment(cno);
    }
}
