package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    //내가 만든 type이기때문에 @Embedded라는 어노테이션을 붙인다
    @Embedded
    private Address address;

    @OneToOne
    private Delivery delivery;
}
//ok