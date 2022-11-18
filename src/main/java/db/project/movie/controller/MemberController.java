package db.project.movie.controller;

import db.project.movie.dto.MemberDto;
import db.project.movie.entity.MemberEntity;
import db.project.movie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/member/new")
    public String memberForm(){

        return "member/newMember";
    }

    @PostMapping("/member/create")
    public String createMember(MemberDto memberDto) throws Exception{
        MemberEntity memberEntity = memberService.create(memberDto, passwordEncoder);
        return "member/memberLogin";
    }
    @GetMapping(value = "/member/login")
    public String loginMember(){
        return "/member/memberLogin";
    }


    @GetMapping(value = "/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLogin";
    }
}
