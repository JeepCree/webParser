package ua.com.mobifix.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Shop getByIdShop(Long id);
    List<Shop> findAllByOrderByNameShopAsc();
    Shop findByIdShop(Long id);
    }
