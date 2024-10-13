package lk.sp.newsweb.controller;

import jakarta.persistence.EntityNotFoundException;
import lk.sp.newsweb.dto.ReporterDto;
import lk.sp.newsweb.entity.Reporter;
import lk.sp.newsweb.service.reporter.ReporterService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("app/reporter")
public class ReporterController {

    private final ReporterService reporterService;

    @PostMapping
    public ResponseEntity<?> postReporter(@RequestBody ReporterDto dto){
        Reporter addedReporter = reporterService.postReporter(dto);
        if (addedReporter != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(addedReporter);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateReporter(@PathVariable Long id, @RequestBody ReporterDto dto){
        try {
            return ResponseEntity.ok(reporterService.updateReporter(id, dto));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllReporters(){
        return ResponseEntity.ok(reporterService.getAllReporters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReporterById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(reporterService.getReporterById(id));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enf.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReporter(@PathVariable Long id){
        try {
            reporterService.deleteReporter(id);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (EntityNotFoundException enf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Reporter not founded"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Something went wrong"));
        }
    }
}


