package com.example.reporttroller.api;

import com.example.reporttroller.entity.Address;
import com.example.reporttroller.entity.Order;
import com.example.reporttroller.entity.OrderItem;
import com.example.reporttroller.entity.OrderStatus;
import com.example.reporttroller.repository.OrderRepository;
import com.example.reporttroller.repository.OrderSearch;
import com.example.reporttroller.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;


    //v1 문제점 : 엔티티 스펙 노
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all=orderRepository.findAllByString(new OrderSearch());

        //Lazy 이므로 조회하고 싶은 속성에 대하여 전부 초기화 필요.

        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems= order.getOrderItems();
            orderItems.stream().forEach(o->o.getItem().getName());
        }

        return all;
    }

    //v2 문제점 : lazy 쿼리가 너무 많이 나감.
    @GetMapping("/api/v2/orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        return orders.stream()
                .map(o->new SimpleOrderDto(o))
                .collect(Collectors.toList());
    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId= order.getId();
            name=order.getMember().getName();
            orderStatus=order.getStatus();
            orderDate=order.getOrderDate();
            address=order.getDelivery().getAddress();
        }

    }
}
