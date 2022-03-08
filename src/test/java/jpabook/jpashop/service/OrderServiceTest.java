package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void order(){
        //given
        //
        Item item = new Book();
        item.setStockQuantity(100);
        item.setName("책1");
        item.setPrice(10000);



        Member member = new Member();
        member.setName("minji");
        //address 생성자 public맞나?
        member.setAddress(new Address("전주시", "길이름", "zipcode"));

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, 1, item.getPrice());


        //이렇게 하고나니까 뭘 어떻게 해야할지를 잘 모르겠네..
        //when
        memberRepository.save(member);
        itemRepository.save(item);
        Orders order = Orders.createOrder(member, delivery, orderItem);
        Long id = orderRepository.save(order);
        //then
        Assertions.assertThat(id).isEqualTo(order.getId());

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