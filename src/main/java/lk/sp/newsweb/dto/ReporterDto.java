package lk.sp.newsweb.dto;

import lk.sp.newsweb.entity.News;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReporterDto {

    private Long id;
    private String name;
    private String location;
//    private List<News> news;
}
