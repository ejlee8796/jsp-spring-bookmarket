/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.apache.ibatis.session.SqlSession
 *  org.springframework.stereotype.Repository
 */
package com.company.persistence;

import com.company.domain.CommentVO;
import com.company.persistence.CommentDAO;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl
implements CommentDAO {
    @Inject
    private SqlSession sqlSession;
    private static final String namespace = "com.company.mapper.CommentMapper";

    @Override
    public int insertComment(CommentVO vo) {
        return this.sqlSession.insert("com.company.mapper.CommentMapper.insertComment", (Object)vo);
    }

    @Override
    public List<Object> selectAllComment(int bno) throws Exception {
        return this.sqlSession.selectList("com.company.mapper.CommentMapper.selectAllComment", (Object)bno);
    }

    @Override
    public CommentVO checkCommentPass(int cno) throws Exception {
        return (CommentVO)this.sqlSession.selectOne("com.company.mapper.CommentMapper.checkCommentPass", (Object)cno);
    }

    @Override
    public int updateComment(CommentVO vo) throws Exception {
        return this.sqlSession.update("com.company.mapper.CommentMapper.updateComment", (Object)vo);
    }

    @Override
    public int deleteComment(int cno) throws Exception {
        return this.sqlSession.delete("com.company.mapper.CommentMapper.deleteComment", (Object)cno);
    }
}
