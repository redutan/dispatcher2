package com.nhnent.edu.dispatcher.controller;

import com.nhnent.edu.dispatcher.model.Member;
import com.nhnent.edu.dispatcher.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    MemberRepository memberRepository;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpSession session, Member loginInfo) {
        Member member = memberRepository.exists(loginInfo.getId(), loginInfo.getPassword());
        if (member == null) {
            return "loginFail";
        }
        else {
            session.setAttribute("member", member);
            return "redirect:/member/list";
        }
    }

}
