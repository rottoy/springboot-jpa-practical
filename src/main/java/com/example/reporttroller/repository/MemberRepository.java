package com.example.reporttroller.repository;

import com.example.reporttroller.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 컴포넌트 스캔으로 자동 관리 됨.
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // jpa 표준 어노테이션 - auto injection
//    private EntityManager em;

    //@Autowired //Spring-data-jpa 면 PersistenceContext 어노테이션 없이도 Autowired 만으로도 찾아냄.
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //jpql 문법은 일반 sql 문법과 비슷함.
    //한가지 다른 것은 엔티티가 대상이 아닌 객체를 대상으로 함.
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름에 의한 조회. where 절 추가.
    //:으로 명시한 경우 파라미터 설정을 해줘야함.
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
