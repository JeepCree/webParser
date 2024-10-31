package ua.com.mobifix.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    Product findAllByShopIdAndCategoriesInAndLink(Long shopId, Long categories, String link);

    List<Product> findAllByCategoriesIn(List<Long> categoryIds);
    Product findTopByOrderByIdDesc();
    List<Product> findAllByOrderByNameAsc();
    Product findByArticle(String article);
    Optional<Product> findByLink(String url);
    Optional<Product> findByLinkSha3(String linkSha3);
    List<Product> findAllByLinkSha3IsNull();

    @Query("SELECT p.link FROM Product p WHERE p.shopId = :shopId")
    List<String> findAllLinksByShopId(@Param("shopId") Long shopId);

    @Query("SELECT p.link FROM Product p WHERE p.shopId = :shopId AND p.description IS NULL")
    List<String> findLinksByShopIdAndDescriptionIsNull(@Param("shopId") Long shopId);
}
