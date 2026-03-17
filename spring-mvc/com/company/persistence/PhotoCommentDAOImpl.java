/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.apache.ibatis.session.SqlSession
 *  org.springframework.stereotype.Repository
 */
package com.company.persistence;

import com.company.domain.PhotoCommentVO;
import com.company.persistence.PhotoCommentDAO;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PhotoCommentDAOImpl
implements PhotoCommentDAO {
    @Inject
    private SqlSession sqlSession;
    private static final String namespace = "com.company.mapper.PhotoCommentMapper";

    @Override
    public int insertComment(PhotoCommentVO vo) {
        return this.sqlSession.insert("com.company.mapper.PhotoCommentMapper.insertComment", (Object)vo);
    }

    @Override
    public List<Object> selectAllComment() throws Exception {
        return this.sqlSession.selectList("com.company.mapper.PhotoCommentMapper.selectAllComment");
    }

    @Override
    public PhotoCommentVO checkPass(int cno) throws Exception {
        return (PhotoCommentVO)this.sqlSession.selectOne("com.company.mapper.PhotoCommentMapper.checkPass", (Object)cno);
    }

    @Override
    public int updateComment(PhotoCommentVO vo) throws Exception {
        return this.sqlSession.update("com.company.mapper.PhotoCommentMapper.updateComment", (Object)vo);
    }

    @Override
    public int deleteComment(int cno) throws Exception {
        return this.sqlSession.delete("com.company.mapper.PhotoCommentMapper.deleteComment", (Object)cno);
    }

    @Override
    public List<Object> selectLimitComment() throws Exception {
        return this.sqlSession.selectList("com.company.mapper.PhotoCommentMapper.selectLimitComment");
    }
}
