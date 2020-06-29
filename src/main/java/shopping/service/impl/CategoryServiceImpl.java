package shopping.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shopping.model.entity.Category;
import shopping.model.entity.CategoryName;
import shopping.repository.CategoryRepository;
import shopping.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0){
            Arrays.stream(CategoryName.values()).forEach(name -> {
                this.categoryRepository.save(new Category(name, String.format("Description for %s", name.name())));
            });
        }
    }

    @Override
    public Category find(CategoryName name) {

        return this.categoryRepository.findByName(name).orElse(null);
    }
}
