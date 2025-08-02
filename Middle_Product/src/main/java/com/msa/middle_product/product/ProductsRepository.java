package com.msa.middle_product.product;

import com.msa.middle_product.product.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {

    @Query("select p from Products p order by p.product_id")
    List<Products> findAllByOrderByProductIdDesc();
    @Query("select p from Products p where p.product_id = :product_id")
    Products findProductsByProduct_id(Long product_id);

    @Query("SELECT p FROM Products p WHERE p.product_name LIKE %:product_name%")
    List<Products> findProductsByProduct_name(String product_name);


}
