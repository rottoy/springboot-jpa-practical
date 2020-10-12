package com.example.reporttroller.service;

import com.example.reporttroller.entity.item.Book;
import com.example.reporttroller.entity.item.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemRepositoryTest {

    @Autowired
     ItemService itemService;

    @Test
    public void 아이템_출력() throws Exception{
       //given


        Item item = new Book();
        item.setPrice(10000);
        item.setId(1L);
        item.setName("aa");

        itemService.saveItem(item);

        System.out.println(itemService.findItems());
       //when

       //then

    }
}
