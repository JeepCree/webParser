package ua.com.mobifix.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Shop getById(Long id);
}
