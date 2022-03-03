package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CategoryItem {
    @Id
    @GeneratedValue
    @Column(name = "category_item_id")
    private long id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Item item;
}
