/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  org.springframework.stereotype.Service
 */
package com.company.service;

import com.company.persistence.SlidingDAO;
import com.company.service.SlidingService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class SlidingServiceImpl
implements SlidingService {
    @Inject
    SlidingDAO dao;

    @Override
    public List<Object> selectSliding1Board() throws Exception {
        return this.dao.selectSliding1Board();
    }

    @Override
    public List<Object> selectSliding2Board() throws Exception {
        return this.dao.selectSliding2Board();
    }

    @Override
    public List<Object> selectTab2Board() throws Exception {
        return this.dao.selectTab2Board();
    }
}
