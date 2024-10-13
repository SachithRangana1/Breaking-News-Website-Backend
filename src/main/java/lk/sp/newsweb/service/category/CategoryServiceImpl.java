package lk.sp.newsweb.service.category;

import jakarta.persistence.EntityNotFoundException;
import lk.sp.newsweb.dto.CategoryDto;
import lk.sp.newsweb.entity.Category;
import lk.sp.newsweb.entity.News;
import lk.sp.newsweb.repository.CategoryRepository;
import lk.sp.newsweb.repository.NewsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;

    private Category saveOrupdateCategory(Category category, CategoryDto categoryDto) {
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());

//        List<News> news = newsRepository.findAllById(categoryDto.getNews_id());
//        category.setNews(news);

//        List<News> newsList = newsRepository.findAllById(categoryDto.getNews_id());
//        category.setNews(newsList);

        return categoryRepository.save(category);
    }

    @Override
    public Category postCategory(CategoryDto categoryDto) {
        return saveOrupdateCategory(new Category(), categoryDto);
    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return saveOrupdateCategory(optionalCategory.get(), categoryDto);
        } else {
            throw new EntityNotFoundException("Category is not found with this Id " + id);
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new EntityNotFoundException("Category is not found with this id " + id);
        }
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll().stream().sorted(Comparator.comparing(Category::getId).reversed()).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
//        Optional<Category> optionalCategory = categoryRepository.findById(id);
//        if (optionalCategory.isPresent()) {
//            categoryRepository.deleteById(id);
//        } else {
//            throw new EntityNotFoundException("Category is not found with this id " + id);
//        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Step 1: Find all news related to the category
        List<News> newsList = newsRepository.findAllByCategory(category);

        // Step 2: Set the category field to null for all news entries related to the category
        for (News news : newsList) {
            news.setCategory(null); // Disassociate category from news
            newsRepository.save(news); // Update the news entry in the database
        }

        // Step 3: Now that news is disassociated, we can delete the category
        categoryRepository.delete(category);
    }
    }



