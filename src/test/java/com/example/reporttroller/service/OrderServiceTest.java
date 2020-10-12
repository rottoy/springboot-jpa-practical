package com.example.reporttroller.service;

import com.example.reporttroller.entity.Address;
import com.example.reporttroller.entity.Member;
import com.example.reporttroller.entity.Order;
import com.example.reporttroller.entity.OrderStatus;
import com.example.reporttroller.entity.item.Book;
import com.example.reporttroller.entity.item.Item;
import com.example.reporttroller.exception.NotEnoughStockException;
import com.example.reporttroller.repository.OrderRepository;
import com.example.reporttroller.repository.OrderSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
       //given
        Member member = createMember("회원1");



        Book book=createBook("JPA",10000, 10 );

        em.persist(book);


       //when
        int orderCount=2;
       Long orderId=orderService.order(member.getId(), book.getId(),orderCount);

       //then

        Order getOrder=orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.",10000*orderCount,getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",8,book.getStockQuantity());
    }




    @Test(expected = NotEnoughStockException.class)
    public void 재고수량초과() throws Exception{
       //given
       Member member=createMember("회원1");

       Item item=createBook("JPA",10000, 10 );

       //when

        int orderCount=11;
        //int orderCount=8;
        orderService.order(member.getId(),item.getId(),orderCount);

       //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }
    
    @Test
    public void 주문취소() throws Exception{
       //given
        Member member=createMember("회원1");
        Book item = createBook("JPA",10000,10);

        int orderCount=2;
        Long orderId=orderService.order(member.getId(),item.getId(),orderCount);

       //when
       orderService.cancelOrder(orderId);

       //then
        Order getOrder=orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL",getOrder.getStatus(),OrderStatus.CANCEL);
        assertEquals("주문 취소시 재고는 원상복구 되어야 함.",10,item.getStockQuantity());
    
    }
    private Book createBook(String name,int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }


    private Member createMember(String name) {
        Member member= new Member();
        member.setName(name);
        member.setAddress(new Address("서울","경기","123-123"));
        em.persist(member);
        return member;
    }

    @Test
    public void OrderSearch로주문() throws Exception{
       //given
        Member member=createMember("회원1");
        Book item = createBook("JPA",10000,10);

        int orderCount=2;
        Long orderId=orderService.order(member.getId(),item.getId(),orderCount);


        OrderSearch orderSearch = new OrderSearch();
        List<Order> orders = orderService.findOrders(orderSearch);

        System.out.println(orders);
       //when

       //then

    }
}