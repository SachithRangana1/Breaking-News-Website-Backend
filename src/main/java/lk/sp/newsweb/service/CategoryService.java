package lk.sp.newsweb.service;

import lk.sp.newsweb.dto.CategoryDto;
import lk.sp.newsweb.entity.Category;

import java.util.List;

public interface CategoryService {

    Category postCategory(CategoryDto categoryDto);

    Category updateCategory(Long id, CategoryDto categoryDto);

    Category getCategoryById(Long id, CategoryDto categoryDto);

    List<Category> getAllCategory();

    void deleteCategory(Long id);
}
