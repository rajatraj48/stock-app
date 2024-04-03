package com.stock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.model.OrderMaster;
@Repository
public interface OrderMasterRepo extends JpaRepository<OrderMaster, Integer>{

}
