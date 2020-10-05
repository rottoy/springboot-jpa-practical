package com.example.reporttroller.entity.item;

import com.example.reporttroller.entity.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter @Setter
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


}
