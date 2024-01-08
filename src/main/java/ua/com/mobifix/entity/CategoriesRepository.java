package ua.com.mobifix.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CategoriesRepository extends JpaRepository<Categories, Integer> {


    List<Categories> findAllByOrderByNameAsc();
    List<Categories> findAllByIdNotInOrderByNameAsc(List<Long> idsToExclude);
    List<Categories> findAllByShopIdOrderByNameAsc(Long shopId);

    Optional<Categories> findById(Long article);

    default Categories updateOrSaveById(Categories newCategory) {

        Optional<Categories> existingCategoryOptional = findById(newCategory.getId());

        if (existingCategoryOptional.isPresent()) {
            Categories existingCategory = existingCategoryOptional.get();
            // Выполните обновление полей существующей категории, например:
            existingCategory.setId(newCategory.getId());
            existingCategory.setName(newCategory.getName());
            existingCategory.setUrl(newCategory.getUrl());
            existingCategory.setDescription(newCategory.getDescription());
            existingCategory.setParentId(newCategory.getParentId());
            existingCategory.setShopId(5L);
            // Другие поля для обновления
            System.out.println("true");
            return save(existingCategory);
        } else {
            // Если категория с таким артикулом не существует, добавьте новую запись
            System.out.println("else");
            System.out.println(newCategory.getId());
            return save(newCategory);
        }
    }
}