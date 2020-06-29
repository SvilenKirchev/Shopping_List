package shopping.service;


import shopping.model.service.ProductServiceModel;
import shopping.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllProducts();

    List<ProductServiceModel> findAllByCategory(String categoryName);

    ProductViewModel findById(String id);

    void delete(String id);

    void deleteAll();
}
