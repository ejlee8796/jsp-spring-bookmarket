/*
 * Decompiled with CFR 0.152.
 */
package com.company.service;

import com.company.domain.CommentVO;
import java.util.List;

public interface CommentService {
    public int insertComment(CommentVO var1) throws Exception;

    public List<Object> selectAllComment(int var1) throws Exception;

    public CommentVO checkCommentPass(int var1) throws Exception;

    public int updateComment(CommentVO var1) throws Exception;

    public int deleteComment(int var1) throws Exception;
}
