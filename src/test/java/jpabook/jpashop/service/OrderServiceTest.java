package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void order(){
        //given
        Item item = new Book();
        item.setStockQuantity(10);
        item.setName("책1");
        item.setPrice(10000);
        itemRepository.save(item);


        Member member = new Member();
        member.setName("minji");
        member.setAddress(new Address("전주시", "길이름", "zipcode"));
        memberRepository.save(member);

        //when
        int count = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), count);
        //then
        Orders getOrder = orderRepository.findById(orderId);


        Assertions.assertEquals(OrderStatus.Order, getOrder.getStatus());
        Assertions.assertEquals(1, getOrder.getOrderItems().size());
        //주문 가격은 가격 * 수량이다
        Assertions.assertEquals(10000 * 2, getOrder.getTotalPrice());
        //주문 수량만큼 재고가 줄어야한다
        Assertions.assertEquals(8, item.getStockQuantity());

    }
    @Test
    public void cancel(){
        //given
        //when
        //then

    }
    @Test
    public void exceedQuantity(){
        //given
        //when
        //then

    }

}