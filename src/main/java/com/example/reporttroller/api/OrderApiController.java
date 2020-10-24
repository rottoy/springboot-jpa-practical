package com.example.reporttroller.api;

import com.example.reporttroller.entity.Order;
import com.example.reporttroller.entity.OrderItem;
import com.example.reporttroller.repository.OrderRepository;
import com.example.reporttroller.repository.OrderSearch;
import com.example.reporttroller.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;


    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all=orderRepository.findAllByString(new OrderSearch());

        //Lazy 이므로 조회하고 싶은 속성에 대하여 전부 초기화 필요.
        /*
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems= order.getOrderItems();
            orderItems.stream().forEach(o->o.getItem().getName());
        }
        */
        return all;
    }
}
