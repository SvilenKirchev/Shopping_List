package shopping.service;

import shopping.model.entity.Category;
import shopping.model.entity.CategoryName;

public interface CategoryService {
    void initCategories();

    Category find(CategoryName name);
}
