/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.apache.ibatis.session.SqlSession
 *  org.springframework.stereotype.Repository
 */
package com.company.persistence;

import com.company.persistence.SlidingDAO;
import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class SlidingDAOImpl
implements SlidingDAO {
    @Inject
    private SqlSession sqlSession;
    private static final String namespace = "com.company.mapper.imgMapper";

    @Override
    public List<Object> selectSliding1Board() throws Exception {
        return this.sqlSession.selectList("com.company.mapper.imgMapper.selectSliding1Board");
    }

    @Override
    public List<Object> selectSliding2Board() throws Exception {
        return this.sqlSession.selectList("com.company.mapper.imgMapper.selectSliding2Board");
    }

    @Override
    public List<Object> selectTab2Board() throws Exception {
        return this.sqlSession.selectList("com.company.mapper.imgMapper.selectTab2Board");
    }
}
