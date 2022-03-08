package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    private int orderPrice; //주문 당시 가격

    //왜 여기에 order을 넣어주진않는가?
    //무조건 order이 선행되어야(필요하진)않기때문
    //이 객체를 만들때 미리 만들어져야하는것들만 넣어주면됨
    public static OrderItem createOrderItem(Item item, int count, int orderPrice){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(orderPrice);

        //그리고 orderItem이 생성됐다는건.. item의 재고를 줄여줘야함으르 말함
        item.minStock(count);

        return orderItem;
    }
    public void cancel(){
        //내가 주문한 수량만큼을 추가시켜주는거야
        getItem().addStock(count);
    }
    public int totalPrice(){
        return getOrderPrice() * getCount();
    }
}
//ok