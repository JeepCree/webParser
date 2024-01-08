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
    Categories findFirstByOrderByIdDesc();

    Optional<Categories> findByUrl(String url);

    default Categories updateOrSaveByUrl(Categories newCategory) {

        Optional<Categories> existingCategoryOptional = findByUrl(newCategory.getUrl());

        if (existingCategoryOptional.isPresent()) {
            Categories existingCategory = existingCategoryOptional.get();
            // Выполните обновление полей существующей категории, например:

            existingCategory.setName(newCategory.getName());
            existingCategory.setShopId(newCategory.getShopId());
            // Другие поля для обновления

            return save(existingCategory);
        } else {
            // Если категория с таким артикулом не существует, добавьте новую запись
            return save(newCategory);
        }
    }
}