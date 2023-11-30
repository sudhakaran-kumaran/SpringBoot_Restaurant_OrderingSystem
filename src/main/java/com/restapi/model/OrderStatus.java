package com.restapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
    private String status;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private List<Order> order;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public OrderStatus(String status) {
        this.status = status;
    }
}
