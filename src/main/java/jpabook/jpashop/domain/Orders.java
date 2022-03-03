package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "orders_id")
    private long id;

    //member와 orders는 일대다 관계다
    //일대다 관계 어떻게 설정하는지 잘 모르겠
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //일대다고 연관관계의 주인은 해당 orderItems의 멤버변수인 orders에 있음
    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;



}
//ok