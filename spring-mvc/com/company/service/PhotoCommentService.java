/*
 * Decompiled with CFR 0.152.
 */
package com.company.service;

import com.company.domain.PhotoCommentVO;
import java.util.List;

public interface PhotoCommentService {
    public int insertComment(PhotoCommentVO var1) throws Exception;

    public List<Object> selectAllComment() throws Exception;

    public List<Object> selectLimitComment() throws Exception;

    public PhotoCommentVO checkPass(int var1) throws Exception;

    public int updateComment(PhotoCommentVO var1) throws Exception;

    public int deleteComment(int var1) throws Exception;
}
