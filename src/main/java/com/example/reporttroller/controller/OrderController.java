package com.example.reporttroller.controller;

import com.example.reporttroller.entity.Address;
import com.example.reporttroller.entity.Member;
import com.example.reporttroller.entity.Order;
import com.example.reporttroller.entity.item.Book;
import com.example.reporttroller.entity.item.Item;
import com.example.reporttroller.repository.OrderSearch;
import com.example.reporttroller.service.ItemService;
import com.example.reporttroller.service.MemberService;
import com.example.reporttroller.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.sql.SQLOutput;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    private final EntityManager em;

    @GetMapping("/order")
    public String createOrderForm(Model model){

        List<Member> members= memberService.findMembers();
        List<Item> items = itemService.findItems();


        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count){

        System.out.println(memberId +" "+ itemId+ " "+ count);
        orderService.order(memberId,itemId,count);


        return "redirect:/orders";
    }

    @GetMapping("/orders")
    //@Transactional
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){

        //Member member=createMember("회원1");
        //Book item = createBook("JPA",10000,10);

        //int orderCount=2;
        //Long orderId=orderService.order(member.getId(),item.getId(),orderCount);

        List<Order> orders = orderService.findOrders(orderSearch);


        System.out.println("===============================");
        System.out.println(orders);
        System.out.println("===============================");
        model.addAttribute("orders",orders);


        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }
}
