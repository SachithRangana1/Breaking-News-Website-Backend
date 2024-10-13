package lk.sp.newsweb.service.reporter;

import jakarta.persistence.EntityNotFoundException;
import lk.sp.newsweb.dto.NewsDto;
import lk.sp.newsweb.dto.ReporterDto;
import lk.sp.newsweb.entity.News;
import lk.sp.newsweb.entity.Reporter;
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
public class ReporterServiceImpl implements ReporterService{

    private final ReporterRepository reporterRepository;
    private final NewsRepository newsRepository;

    private Reporter saveorUpdateReporter(Reporter reporter, ReporterDto reporterDto){
        reporter.setId(reporterDto.getId());
        reporter.setName(reporterDto.getName());
        reporter.setLocation(reporterDto.getLocation());

//        List<News> newList = reporterDto.getNewsDtos().stream().map(newsDto -> convertToNewsEntity(newsDto)).collect(Collectors.toList());
//
//        reporter.setNews(newList);
//
        return reporterRepository.save(reporter);
    }
//    private News convertToNewsEntity(NewsDto newsDto) {
//        News news = new News();
//        news.setId(newsDto.getId());
//        news.setHeadline(newsDto.getHeadline());
//        return news;
//    }

    @Override
    public Reporter postReporter(ReporterDto reporterDto) {
        return saveorUpdateReporter(new Reporter(), reporterDto);
    }

    @Override
    public Reporter updateReporter(Long id, ReporterDto reporterDto) {
        Optional<Reporter> optionalReporter = reporterRepository.findById(id);
        if (optionalReporter.isPresent()){
            return saveorUpdateReporter(optionalReporter.get(), reporterDto);
        }else {
            throw new EntityNotFoundException("Reporter is not found with this Id " + id);
        }
    }

    @Override
    public Reporter getReporterById(Long id) {
        Optional<Reporter> optionalReporter = reporterRepository.findById(id);
        if (optionalReporter.isPresent()){
            return optionalReporter.get();
        }else {
            throw new EntityNotFoundException("Reporter is not found with this Id " + id);
        }
    }

    @Override
    public List<Reporter> getAllReporters() {
        return reporterRepository.findAll().stream().sorted(Comparator.comparing(Reporter::getId).reversed()).collect(Collectors.toList());
    }

    @Override
    public void deleteReporter(Long id) {
//        Optional<Reporter> optionalReporter = reporterRepository.findById(id);
//        if (optionalReporter.isPresent()){
//            reporterRepository.deleteById(id);
//        }else {
//            throw new EntityNotFoundException("Reporter is not found with this Id " + id);
//        }
//    }

        Reporter reporter = reporterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reporter not found"));

        // Set reporter to null in all related news before deleting the reporter
        List<News> newsList = newsRepository.findAllByReporter(reporter);
        for (News news : newsList) {
            news.setReporter(null); // Remove the reporter from the news
            newsRepository.save(news); // Save the news without the reporter
        }

        // Now it's safe to delete the reporter without deleting the associated news
        reporterRepository.delete(reporter);
    }
}
