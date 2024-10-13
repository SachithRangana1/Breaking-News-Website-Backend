package lk.sp.newsweb.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.sp.newsweb.dto.NewsDto;
import lk.sp.newsweb.entity.News;
import lk.sp.newsweb.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;

@RestController
@RequestMapping("app/news")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<?> postNews(@RequestBody NewsDto dto){
        News createdNews = newsService.postNews(dto);
        if (createdNews != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable Long id, @RequestBody NewsDto dto){
        try {
            return ResponseEntity.ok(newsService.updateNews(id, dto));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(newsService.getNewsById(id));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNews(){
        return ResponseEntity.ok(newsService.GetAllNews());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id){
        try {
            newsService.deleteNews(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "News not founded"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }
}
