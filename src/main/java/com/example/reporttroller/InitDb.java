package com.example.reporttroller;

import com.example.reporttroller.entity.*;
import com.example.reporttroller.entity.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){

            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA1 Book", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1= OrderItem.createOrderItem(book1,10000,1);
            OrderItem orderItem2= OrderItem.createOrderItem(book2,20000,2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order=Order.createOrder(member,delivery,orderItem1,orderItem2);
            em.persist(order);


        }

        public void dbInit2(){

            Member member = createMember("userB", "부산", "2", "1211");
            em.persist(member);

            Book book1 = createBook("Spring1 Book", 12300, 120);
            em.persist(book1);

            Book book2 = createBook("Spring2 BOOK", 20020, 104);
            em.persist(book2);

            OrderItem orderItem1= OrderItem.createOrderItem(book1,10000,1);
            OrderItem orderItem2= OrderItem.createOrderItem(book2,20000,2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order=Order.createOrder(member,delivery,orderItem1,orderItem2);
            em.persist(order);


        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book2 = new Book();
            book2.setName(name);
            book2.setPrice(price);
            book2.setStockQuantity(stockQuantity);
            return book2;
        }

        private Member createMember(String name, String address, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(address, street, zipcode));
            return member;
        }
    }
}

