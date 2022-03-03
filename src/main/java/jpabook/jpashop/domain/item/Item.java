package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item {
    @Id
    @Generated
    @Column(name="item_id")
    private long id;

    private String name;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    private int price;

    private int stockQuantity;


}
//ok