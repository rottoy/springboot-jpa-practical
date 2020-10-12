package com.example.reporttroller.entity.item;

import com.example.reporttroller.entity.Category;
import com.example.reporttroller.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter @Setter
@ToString
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private  String name;

    private int price;

    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns =@JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();


    //==비즈니스 로직 ==//
    /*
    stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }
    /*
    * stock 감소
    * */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity=restStock;
    }
}
