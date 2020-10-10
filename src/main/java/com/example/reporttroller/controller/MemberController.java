package com.example.reporttroller.controller;

import com.example.reporttroller.entity.Address;
import com.example.reporttroller.entity.Member;
import com.example.reporttroller.service.MemberService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jmx.export.assembler.MethodExclusionMBeanInfoAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String createMemberForm(Model model){

        MemberForm memberForm= new MemberForm();
        model.addAttribute("memberForm",memberForm);
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String membersJoin(@Valid MemberForm memberForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            //Thymeleaf -Spring 에 의해 bindingResult 에 대한 정보가 자동으로 프론트로 넘어감.
            return "members/createMemberForm";
        }
        Address address= new Address(memberForm.getCity(),memberForm.getStreet(),memberForm.getZipcode());
        Member member= new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);
        memberService.join(member);

        return "redirect:/";
    }
}
