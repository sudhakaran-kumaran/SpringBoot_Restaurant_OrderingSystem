package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderedDish> orderedDishList;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private AppUser appUser;
    @OneToOne
    @JoinColumn(name = "status_id",referencedColumnName = "id")
    private OrderStatus orderStatus;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
