package com.example.reporttroller.service;

import com.example.reporttroller.entity.Member;
import com.example.reporttroller.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{

        //given - 얘가 주어질 꺼야
        Member member= new Member();
        member.setName("kim");

        //when
        Long savedId=memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{
       //given

       //when

       //then

    }
}