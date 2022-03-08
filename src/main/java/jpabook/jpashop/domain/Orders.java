package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

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

    //==연관관계 메서드==// - 이게 뭐지
    //delivery나 member같은 경우 연관관계로 엮여있기때문에
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }

    //주문하는 메서드 == 정적 팩토리 메서드로 static으로 선언한다
    public static Orders createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Orders order = new Orders();
        //어떤 회원이 주문하는지 배달지는 어디인지 주문하는 아이템이 무엇인지는 매번 달라지니까 이것들을 매개변수로 삼은거야
        order.setMember(member);
        order.setDelivery(delivery);
        order.setOrderDate(LocalDateTime.now());
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        return order;
    }
    //주문 취소하는 메서드
    public Long cancelOrder(){
        //근데 배송이 이미 출발했으면 취소를 못해
        //이런 예외 터뜨리는것들도 다 비지니스 로직들이야
        if(delivery.getStatus().equals(DeliveryStatus.COMP)){
            //배송중인 상태인 경우 예외를 던진다
            throw new IllegalStateException("이미 배송중인 상품은 취소할 수 없습니다");
        }
        setStatus(OrderStatus.Cancel);

        //재고도 다시 올려줘야함
        for(OrderItem orderItem : orderItems){
            //내원래 생각은 아래와 같은데.. 취소 매서드를 orderItem에 따로 만든다
            //시켰던것은 마이너스시켰을테니 그걸 다시 + 해준다
            //orderItem.getItem().setStockQuantity(orderItem.getItem().getStockQuantity() + orderItem.getCount());
            orderItem.cancel();
        }
        //어떤 주문이 취소됐는지를 알려주는게 좋을 것 같아서
        return this.id;
    }
    public int getTotalPrice(){
        int total = 0;
        for (OrderItem orderItem : orderItems){
            //근데 orderitem에 수량과 가격이 다있으니까 그냥 이걸 그 객체의 함수로 만들어버린다 아래와같이 코드 작성하지 말고
            //total += orderItem.getItem().getPrice() * orderItem.getCount();
            total += orderItem.totalPrice();
        }
        return total;
    }

}
//ok