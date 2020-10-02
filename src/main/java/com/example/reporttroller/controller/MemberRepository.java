package com.example.reporttroller.controller;

import com.example.reporttroller.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 자동으로 Spring bean 등록
public class MemberRepository {

    @PersistenceContext //이 어노테이션이 존재하면, 스프링이 알아서 주입을 해줌.
                        //즉, EntityManger 자동 생성. by spring-boot-starter-jpa
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
        // 커맨드와 쿼리를 분리하라는 원칙에 의해서 원래 return 값 설정안함
        // 그래서 id 정도만 반환.


    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }



}
