package jpabook.jpashop.domain;

import jpabook.jpashop.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Member member;

    private LocalDateTime orderDate;



}
