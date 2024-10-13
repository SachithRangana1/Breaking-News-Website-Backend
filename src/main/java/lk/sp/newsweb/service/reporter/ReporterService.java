package lk.sp.newsweb.service.reporter;

import lk.sp.newsweb.dto.ReporterDto;
import lk.sp.newsweb.entity.Reporter;

import java.util.List;

public interface ReporterService {

    Reporter postReporter(ReporterDto reporterDto);

    Reporter updateReporter(Long id, ReporterDto reporterDto);

    Reporter getReporterById(Long id);

    List<Reporter> getAllReporters();

    void deleteReporter(Long id);

}
