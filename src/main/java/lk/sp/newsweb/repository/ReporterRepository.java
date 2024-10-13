package lk.sp.newsweb.repository;

import lk.sp.newsweb.entity.Reporter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Long> {
}
