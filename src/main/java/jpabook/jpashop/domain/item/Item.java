package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item {
    @Id
    @Generated
    @Column(name="item_id")
    private long id;

    private String name;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    private int price;

    private int stockQuantity;

    public int add(int x){
        stockQuantity += x;
        return stockQuantity;
    }
    public int min(int x){
        if(stockQuantity > 0){
            stockQuantity -= x;
        }
        return stockQuantity;
    }

    public Item update(Item item){
        this.name = item.name;
        this.categories = item.categories;
        this.price = item.price;
        this.stockQuantity = item.stockQuantity;

        return this;
    }


}
//ok