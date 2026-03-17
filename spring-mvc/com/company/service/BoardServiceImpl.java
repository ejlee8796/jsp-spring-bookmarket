/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.springframework.stereotype.Service
 */
package com.company.service;

import com.company.domain.BoardVO;
import com.company.persistence.BoardDAO;
import com.company.service.BoardService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl
implements BoardService {
    @Inject
    BoardDAO dao;

    @Override
    public int insertBoard(BoardVO vo) throws Exception {
        return this.dao.insertBoard(vo);
    }

    @Override
    public List<Object> selectAllBoard() throws Exception {
        return this.dao.selectAllBoard();
    }

    @Override
    public BoardVO detailBoard(int bno) throws Exception {
        return this.dao.detailBoard(bno);
    }

    @Override
    public int updateHit(int bno) throws Exception {
        return this.dao.updateHit(bno);
    }

    @Override
    public BoardVO checkboardPass(int bno) throws Exception {
        return this.dao.checkboardPass(bno);
    }

    @Override
    public int updateBoard(BoardVO vo) throws Exception {
        return this.dao.updateBoard(vo);
    }

    @Override
    public int deleteBoard(int bno) throws Exception {
        return this.dao.deleteBoard(bno);
    }

    @Override
    public List<Object> select10List(int pstartno) throws Exception {
        return this.dao.select10List(pstartno);
    }

    @Override
    public int selectCount() throws Exception {
        return this.dao.selectCount();
    }

    @Override
    public List<Object> searchBoard(BoardVO vo) throws Exception {
        return this.dao.searchBoard(vo);
    }

    @Override
    public int searchCount(String btitle) throws Exception {
        return this.dao.searchCount(btitle);
    }
}
