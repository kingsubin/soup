package me.soob.springbootwebservice.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.soob.springbootwebservice.domain.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Shop extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String product_image;
    private String product_info;

}
