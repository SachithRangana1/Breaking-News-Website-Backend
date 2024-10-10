package lk.sp.newsweb.repository;

import lk.sp.newsweb.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
