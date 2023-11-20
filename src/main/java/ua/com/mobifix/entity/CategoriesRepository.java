package ua.com.mobifix.entity;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    List<Categories> findAllByOrderByNameAsc();
}