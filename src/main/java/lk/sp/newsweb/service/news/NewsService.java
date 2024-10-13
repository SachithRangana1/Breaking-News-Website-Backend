package lk.sp.newsweb.service.news;

import lk.sp.newsweb.dto.NewsDto;
import lk.sp.newsweb.entity.News;

import java.util.List;

public interface NewsService {

    News postNews(NewsDto newsDto);

    News updateNews(Long id, NewsDto newsDto);

    News getNewsById(Long id);

    List<News> GetAllNews();

    void deleteNews(Long id);
}
