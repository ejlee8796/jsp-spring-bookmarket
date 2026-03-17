/*
 * Decompiled with CFR 0.152.
 */
package com.company.persistence;

import com.company.domain.BoardVO;
import java.util.List;

public interface BoardDAO {
    public int insertBoard(BoardVO var1) throws Exception;

    public List<Object> selectAllBoard() throws Exception;

    public BoardVO detailBoard(int var1) throws Exception;

    public int updateHit(int var1) throws Exception;

    public BoardVO checkboardPass(int var1) throws Exception;

    public int updateBoard(BoardVO var1) throws Exception;

    public int deleteBoard(int var1) throws Exception;

    public List<Object> select10List(int var1) throws Exception;

    public int selectCount() throws Exception;

    public List<Object> searchBoard(BoardVO var1) throws Exception;

    public int searchCount(String var1) throws Exception;
}
