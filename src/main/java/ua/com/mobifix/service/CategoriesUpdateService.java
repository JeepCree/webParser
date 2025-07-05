package ua.com.mobifix.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.ProductRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoriesUpdateService {

    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;
    private final Gson gson = new Gson();

    @Transactional
    public void updateCategoriesFromBreadcrumbs(Long shopId) {
        List<String> breadcrumbsJsons = productRepository.findAllBreadcrumbsByShopId(shopId);

        Map<String, Categories> urlToCategory = categoriesRepository
                .findAllByShopId(shopId)
                .stream()
                .collect(HashMap::new, (map, cat) -> map.put(cat.getUrl(), cat), HashMap::putAll);

        for (String json : breadcrumbsJsons) {
            JsonObject breadcrumb = gson.fromJson(json, JsonObject.class);

            Categories parent = null;

            for (Map.Entry<String, JsonElement> entry : breadcrumb.entrySet()) {
                String name = entry.getKey();
                String url = entry.getValue().getAsString();
                Long parentId = parent != null ? parent.getId() : 0L;

                Categories category = urlToCategory.get(url);

                if (category == null) {
                    category = categoriesRepository
                            .findFirstByNameAndParentIdAndShopId(name, parentId, shopId)
                            .orElse(null);
                }

                if (category == null) {
                    category = new Categories();
                    category.setName(name);
                    category.setUrl(url);
                    category.setParentId(parentId);
                    category.setRootCategory(parent == null);
                    category.setActive(true);
                    category.setShopId(shopId);
                    category.setDescription(null);
                    category = categoriesRepository.save(category);
                } else {
                    boolean changed = false;

                    if (!category.getName().equals(name)) {
                        category.setName(name);
                        changed = true;
                    }
                    if (!Objects.equals(category.getParentId(), parentId)) {
                        category.setParentId(parentId);
                        changed = true;
                    }

                    if (changed) {
                        category = categoriesRepository.save(category);
                    }
                }

                urlToCategory.put(url, category);
                parent = category;
            }
        }
    }
}
