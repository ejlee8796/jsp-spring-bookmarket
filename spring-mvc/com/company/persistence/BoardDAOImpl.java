/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.apache.ibatis.session.SqlSession
 *  org.springframework.stereotype.Repository
 */
package com.company.persistence;

import com.company.domain.BoardVO;
import com.company.persistence.BoardDAO;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl
implements BoardDAO {
    @Inject
    private SqlSession sqlSession;
    private static final String namespace = "com.company.mapper.pagingMapper";

    @Override
    public int insertBoard(BoardVO vo) throws Exception {
        return this.sqlSession.insert("com.company.mapper.pagingMapper.insertBoard", (Object)vo);
    }

    @Override
    public List<Object> selectAllBoard() throws Exception {
        return this.sqlSession.selectList("com.company.mapper.pagingMapper.selectAllBoard");
    }

    @Override
    public BoardVO detailBoard(int bno) throws Exception {
        return (BoardVO)this.sqlSession.selectOne("com.company.mapper.pagingMapper.detailBoard", (Object)bno);
    }

    @Override
    public int updateHit(int bno) throws Exception {
        return this.sqlSession.update("com.company.mapper.pagingMapper.updateHit", (Object)bno);
    }

    @Override
    public BoardVO checkboardPass(int bno) throws Exception {
        return (BoardVO)this.sqlSession.selectOne("com.company.mapper.pagingMapper.checkboardPass", (Object)bno);
    }

    @Override
    public int updateBoard(BoardVO vo) throws Exception {
        return this.sqlSession.update("com.company.mapper.pagingMapper.updateBoard", (Object)vo);
    }

    @Override
    public int deleteBoard(int bno) throws Exception {
        return this.sqlSession.delete("com.company.mapper.pagingMapper.deleteBoard", (Object)bno);
    }

    @Override
    public List<Object> select10List(int pstartno) throws Exception {
        return this.sqlSession.selectList("com.company.mapper.pagingMapper.select10List", (Object)pstartno);
    }

    @Override
    public int selectCount() throws Exception {
        return (Integer)this.sqlSession.selectOne("com.company.mapper.pagingMapper.selectCount");
    }

    @Override
    public List<Object> searchBoard(BoardVO vo) throws Exception {
        return this.sqlSession.selectList("com.company.mapper.pagingMapper.searchBoard", (Object)vo);
    }

    @Override
    public int searchCount(String btitle) throws Exception {
        return (Integer)this.sqlSession.selectOne("com.company.mapper.pagingMapper.searchCount", (Object)btitle);
    }
}
