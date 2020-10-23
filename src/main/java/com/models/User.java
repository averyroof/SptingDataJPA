package com.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByNameQuery",
                query = "Select u from User u where u.name in ('Tom', 'Emily') "),
        @NamedQuery(name = "User.findByEmailQuery",
                query = "Select u from User u where u.email like '%j%'"),
})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressForUser;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Address getAddressForUser() {
        return addressForUser;
    }

    public void setAddressForUser(Address addressForUser) {
        this.addressForUser = addressForUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email);
    }

    @Override
    public String toString() {
        return "User {" +
                "userId = " + userId +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", addressForUser = " + addressForUser +
                '}';
    }
}
