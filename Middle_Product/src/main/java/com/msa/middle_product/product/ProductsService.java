package com.msa.middle_product.product;
import com.msa.middle_product.product.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;


    @Transactional(readOnly = true)
    public List<Products> productsfindAll() {
        return productsRepository.findAllByOrderByProductIdDesc();
    }

    public Products productsfindById(Long id) {
        return productsRepository.findProductsByProduct_id(id);
    }
    //사용 가능성 있음
    public List<Products> productsfindAllByname(String product_name) {
        return productsRepository.findProductsByProduct_name(product_name);
    }

    @Transactional
    public void productssave(Products products) {
            productsRepository.save(products);
//            throw new IllegalStateException("이미 상품이 존재합니다. 상품 이름 : " + tempProduct.getProduct_name()
//                    + " 상품 코드 : {}" + tempProduct.getProduct_id());

    }
}
