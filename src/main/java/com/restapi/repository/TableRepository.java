package com.restapi.repository;

import com.restapi.model.TableDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableDetails,Long> {

}
