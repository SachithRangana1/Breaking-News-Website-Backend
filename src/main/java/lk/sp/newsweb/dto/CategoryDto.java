package lk.sp.newsweb.dto;

import jakarta.persistence.JoinColumn;
import lk.sp.newsweb.entity.News;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
//    private List<Long> news_id;
}
