package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Orders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.OneToOne;

@Repository
@AllArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public Long save(Orders order){
        em.persist(order);
        return order.getId();
    }
    public Orders findById(Long id){
        return em.find(Orders.class, id);
    }
}
