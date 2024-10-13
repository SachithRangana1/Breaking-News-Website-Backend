package lk.sp.newsweb.service.news;

import jakarta.persistence.EntityNotFoundException;
import lk.sp.newsweb.dto.NewsDto;
import lk.sp.newsweb.entity.Category;
import lk.sp.newsweb.entity.News;
import lk.sp.newsweb.entity.Reporter;
import lk.sp.newsweb.repository.CategoryRepository;
import lk.sp.newsweb.repository.NewsRepository;
import lk.sp.newsweb.repository.ReporterRepository;
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
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final ReporterRepository reporterRepository;
    private final CategoryRepository categoryRepository;

    private News savorUpdateNews(News news, NewsDto newsDto){
        news.setId(newsDto.getId());
        news.setHeadline(newsDto.getHeadline());
        Reporter reporter = reporterRepository.findById(newsDto.getReporter_id()).orElseThrow(()->
                new RuntimeException("Reporter not found"));
        news.setReporter(reporter);

        Category category = categoryRepository.findById(newsDto.getCategory_id()).orElseThrow(()->
                new RuntimeException("Category not found"));
        news.setCategory(category);

        return newsRepository.save(news);
    }

    @Override
    public News postNews(NewsDto newsDto) {
        return savorUpdateNews(new News(), newsDto);
    }

    @Override
    public News updateNews(Long id, NewsDto newsDto) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()){
            return savorUpdateNews(optionalNews.get(), newsDto);
        }else {
            throw new EntityNotFoundException("News is not found with this Id " + id);
        }
    }

    @Override
    public News getNewsById(Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()){
            return optionalNews.get();
        }else {
            throw new EntityNotFoundException("News is not found with this Id " + id);
        }
    }

    @Override
    public List<News> GetAllNews() {
        return newsRepository.findAll().stream().sorted(Comparator.comparing(News::getId).reversed()).collect(Collectors.toList());
    }

    @Override
    public void deleteNews(Long id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()){
            newsRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("News is not found with this Id " + id);
        }
    }
}
