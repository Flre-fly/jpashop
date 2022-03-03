package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Order_item {
    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private long id;

    @ManyToOne
    private Orders orders;

    private int count;
}
