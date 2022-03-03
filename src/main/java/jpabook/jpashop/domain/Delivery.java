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
    private Long id;


    //onetoone인데 보통 order에서 delivery를 조회하니까 연관관계의 주인을 delivery로 생각하고 여기에다가
    //mappedby를써주는것임
    @OneToOne(mappedBy = "delivery")
    private Orders orders;


    @Embedded
    private Address address;

    //oridnal이 아니라 string으로 정해준다
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


}
//ok