package com.example.reporttroller.controller;

import com.example.reporttroller.entity.Member;
import com.example.reporttroller.entity.item.Book;
import com.example.reporttroller.entity.item.Item;
import com.example.reporttroller.service.ItemService;
import com.example.reporttroller.service.MemberService;
import com.example.reporttroller.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createOrderForm(Model model){

        List<Member> members= memberService.findMembers();
        List<Item> items = itemService.findItems();


        model.addAttribute("members",members);
        model.addAttribute("items",items);

        return "order/orderForm";
    }

    @PostMapping("order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count){

        orderService.order(memberId,itemId,count);


        return "redirect:/orders";
    }
}
