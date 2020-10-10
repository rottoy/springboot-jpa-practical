package com.example.reporttroller.controller;

import com.example.reporttroller.entity.item.Book;
import com.example.reporttroller.entity.item.Item;
import com.example.reporttroller.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("items/new")
    public String createItemForm(Model model){
        BookForm bookForm=new BookForm();
        model.addAttribute("form",bookForm);

        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(BookForm bookForm){
        Book item =new Book();
        item.setAuthor(bookForm.getAuthor());
        item.setStockQuantity(bookForm.getStockQuantity());
        item.setName(bookForm.getName());
        item.setIsbn(bookForm.getIsbn());
        item.setStockQuantity(bookForm.getStockQuantity());

        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);

        return "items/itemList";
    }
}
