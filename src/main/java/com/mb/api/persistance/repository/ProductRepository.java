package com.mb.api.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.api.persistance.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	Product findByCode(String code);
}
