package com.kq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    private String userId;
    @JsonIgnore
    private String password;
    private String userName;
    private int userSex;
    //@Column(columnDefinition = "mediumtext")
    //private String userImg;
    private int delTag;
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Cart> cart;
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)//级联删除
    private List<DeliveryAddress> deliveryAddresses;
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Orders> orders;
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)//级联删除
    private List<Feedback> feedbacks;//反馈
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)//级联删除
    private List<Note> notes;//记录

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Reservation> reservations;//预约

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true
    )
    private List<Bookmark> bookmarks;//收藏夹

    public void addCarts(Cart cart){
        this.cart.add(cart);
        cart.setUser(this);
    }

    public void addOrders(Orders orders){
        this.orders.add(orders);
        orders.setUser(this);
    }

    public void addDeliveryAddresses(DeliveryAddress deliveryAddress){
        this.deliveryAddresses.add(deliveryAddress);
        deliveryAddress.setUser(this);
    }
    public void removeDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress != null) {
            this.deliveryAddresses.remove(deliveryAddress);
            deliveryAddress.setUser(null);
        }
    }
    public void removeFeedback(Feedback feedback) {
        if (feedback != null) {
            this.feedbacks.remove(feedback);
            feedback.setUser(null);
        }
    }
    public void removeNote(Note note) {
        if (note != null) {
            this.notes.remove(note);
            note.setUser(null);
        }
    }
    public void removeCarts(Cart cart){
        this.cart.remove(cart);
        cart.setUser(null);
    }
}
