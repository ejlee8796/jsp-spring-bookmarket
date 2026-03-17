/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.inject.Inject
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpSession
 *  org.springframework.stereotype.Controller
 *  org.springframework.ui.Model
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 */
package com.company.spring0716;

import com.company.service.SlidingService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"/index/*"})
public class IndexController {
    @Inject
    private SlidingService sliding;

    @RequestMapping(value={"/galleryIndex"}, method={RequestMethod.GET})
    public void listAll(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        if (request.getParameter("id") != null) {
            String id = request.getParameter("id");
            model.addAttribute("id", (Object)id);
            session.setAttribute("id", (Object)id);
        }
    }

    @RequestMapping(value={"/gallerySliding1"}, method={RequestMethod.GET})
    public void gallerySliding1(Model model) throws Exception {
        model.addAttribute("vo", this.sliding.selectSliding1Board());
        model.addAttribute("tab2", this.sliding.selectTab2Board());
    }

    @RequestMapping(value={"/gallerySliding2"}, method={RequestMethod.GET})
    public void gallerySliding2(Model model) throws Exception {
        model.addAttribute("vo", this.sliding.selectSliding2Board());
    }

    @RequestMapping(value={"/bookblock"}, method={RequestMethod.GET})
    public void bookblock() throws Exception {
    }

    @RequestMapping(value={"/galleryRoom"}, method={RequestMethod.GET})
    public void galleryRoom() throws Exception {
    }
}
