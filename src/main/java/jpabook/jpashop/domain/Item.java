package jpabook.jpashop.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @Generated
    @Column(name="item_id")
    private long id;

    private String name;

    private int price;

    private int stockQuantity;

    private List<Category> categoriesList = new ArrayList<Category>();

}
