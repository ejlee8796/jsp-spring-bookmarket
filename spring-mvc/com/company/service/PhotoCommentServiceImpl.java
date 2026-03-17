/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.springframework.stereotype.Service
 */
package com.company.service;

import com.company.domain.PhotoCommentVO;
import com.company.persistence.PhotoCommentDAO;
import com.company.service.PhotoCommentService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class PhotoCommentServiceImpl
implements PhotoCommentService {
    @Inject
    PhotoCommentDAO dao;

    @Override
    public int insertComment(PhotoCommentVO vo) throws Exception {
        return this.dao.insertComment(vo);
    }

    @Override
    public List<Object> selectAllComment() throws Exception {
        return this.dao.selectAllComment();
    }

    @Override
    public PhotoCommentVO checkPass(int cno) throws Exception {
        return this.dao.checkPass(cno);
    }

    @Override
    public int updateComment(PhotoCommentVO vo) throws Exception {
        return this.dao.updateComment(vo);
    }

    @Override
    public int deleteComment(int cno) throws Exception {
        return this.dao.deleteComment(cno);
    }

    @Override
    public List<Object> selectLimitComment() throws Exception {
        return this.dao.selectLimitComment();
    }
}
