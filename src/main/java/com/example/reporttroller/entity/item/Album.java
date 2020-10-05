package com.example.reporttroller.entity.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("A")
@Setter
public class Album extends Item  {

    private String artist;

}
