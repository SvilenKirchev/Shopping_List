package shopping.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shopping.model.entity.Category;
import shopping.model.entity.CategoryName;
import shopping.model.entity.Product;
import shopping.model.service.ProductServiceModel;
import shopping.model.view.ProductViewModel;
import shopping.repository.ProductRepository;
import shopping.service.CategoryService;
import shopping.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);

        product.setCategory(this.categoryService.find(productServiceModel.getCategory().getName()));

        this.productRepository.saveAndFlush(product);
    }


    @Override
    public List<ProductViewModel> findAllProducts() {

        return this.productRepository.findAll().stream().map(product -> {
            ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);

            return productViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String categoryName) {
        Category category = this.modelMapper.map(this.categoryService.find(CategoryName.valueOf(categoryName)), Category.class);
        return this.productRepository.findAllByCategory(category).stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProductViewModel findById(String id) {
        return this.productRepository.findById(id).map(product -> {
            ProductViewModel productViewModel = this.modelMapper.map(product, ProductViewModel.class);

            return productViewModel;
        }).orElse(null);

    }


    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}
