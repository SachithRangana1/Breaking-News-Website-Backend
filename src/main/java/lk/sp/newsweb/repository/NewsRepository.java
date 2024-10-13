package lk.sp.newsweb.repository;

import lk.sp.newsweb.entity.Category;
import lk.sp.newsweb.entity.News;
import lk.sp.newsweb.entity.Reporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

        List<News> findAllByReporter(Reporter reporter);
        List<News> findAllByCategory(Category category);
}
