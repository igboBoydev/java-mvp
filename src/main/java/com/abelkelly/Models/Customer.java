package com.abelkelly.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import java.util.Objects;

@Entity
public class Customer {

    @jakarta.persistence.Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer Id;
    private String name;
    private String email;
    private Integer age;

    public Customer(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Customer() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(Id, customer.Id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, email, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
