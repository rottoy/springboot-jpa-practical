package com.example.reporttroller.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    @NotEmpty(message = "이름이 필수적으로 입력되어야 합니다.")
    private  String name;

    private int price;

    private int stockQuantity;


    private String author;
    private String isbn;
}
