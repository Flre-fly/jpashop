package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Embeddable
public class Address {
    private String city;

    private String street;

    private String zipcode;

    private Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;

    }

    public Address() {

    }
}
