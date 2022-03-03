package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private long id;


    @OneToOne
    private Orders orders;

    @OneToOne
    private Member member;


    private Address address;

    private Status status;


}
