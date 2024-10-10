package lk.sp.newsweb.repository;

import lk.sp.newsweb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
