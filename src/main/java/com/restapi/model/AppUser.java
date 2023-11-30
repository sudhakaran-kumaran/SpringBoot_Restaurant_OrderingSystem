package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users") // don't use User
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private List<Order> orderList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private  List<Cart> carts;


    @JsonIgnore
    @OneToOne(mappedBy = "appUser")
    private TableDetails tableDetails;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
