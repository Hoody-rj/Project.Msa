package com.msa.middle_product.product;
import com.msa.middle_product.product.dto.request.RequestRegist;
import com.msa.middle_product.product.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional
    public void productssave(RequestRegist products) {
        try {
            Products tempproduct = new Products(
                    products.getProduct_name(),
                    products.getProduct_price(),
                    products.getProduct_supply_price(),
                    products.getCurrency()
            );
            productsRepository.save(tempproduct);
        } catch (IllegalArgumentException e) {
            // 입력이 null일 때 등
            throw new IllegalArgumentException("유효하지 않은 제품 정보입니다.", e);
        } catch (DataIntegrityViolationException e) {
            // DB 제약조건 위반 등
            throw new IllegalStateException("제품 저장 중 데이터 무결성 오류가 발생했습니다.", e);
        } catch (Exception e) {
            // 그 외 예상치 못한 예외
            throw new RuntimeException("알 수 없는 오류로 제품 저장에 실패했습니다.", e);
        }
    }
}
