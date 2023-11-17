package com.restapi.repository;

import com.restapi.model.OrderedDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedDishRepository extends JpaRepository<OrderedDish,Long> {
}
