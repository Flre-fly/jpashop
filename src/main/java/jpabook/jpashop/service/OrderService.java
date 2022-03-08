package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    //주문, 취소, 검색 기능을 만들어야함

    //주문하는 메서드를 만든다.. 주문은 한번에 하나의 상품을 주문할 수 있다
    @Transactional
    public void order(Long memberId, Long itemId, int count){
        //그냥 엔티티에 있는 order만 하면 되는게 아니다
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);
        //엔티티 조회해서 가져오기

        //배송정보는 고객의 기본 배송지로 설정하기
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        //주문상품들을 생성한다
        OrderItem orderItem = OrderItem.createOrderItem(item, count, item.getPrice());

        //주문을 생성한다
        Orders order = Orders.createOrder(member, delivery, orderItem);
        //주문을 저장한다
        orderRepository.save(order);
    }

    @Transactional
    public void cancel(Long orderId){
        Orders order = orderRepository.findById(orderId);
        order.cancelOrder();
    }

}
