package com.nhnent.edu.dispatcher.controller;

import com.nhnent.edu.dispatcher.model.Member;
import com.nhnent.edu.dispatcher.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberListController implements Controller {
    @Autowired
    MemberRepository memberRepository;


    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Member> members = memberRepository.list();

        ModelAndView mav = new ModelAndView("/memberList");
        mav.addObject("members", members);

        return mav;
    }

}
