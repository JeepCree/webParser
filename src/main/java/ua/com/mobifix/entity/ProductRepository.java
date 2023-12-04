package ua.com.mobifix.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategoriesIn(List<Long> categoryIds);
    Product findTopByOrderByArticleDesc();
    List<Product> findAllByOrderByNameAsc();
}
