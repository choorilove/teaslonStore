package com.example.teastore.repo;

import com.example.teastore.models.CustOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustOrderRepository extends CrudRepository<CustOrder,Long> {
    
    CustOrder findById(long id);

}
