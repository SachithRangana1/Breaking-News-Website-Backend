package lk.sp.newsweb.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.sp.newsweb.entity.Reporter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class NewsDto {

    private Long id;
    private String headline;
    private Long reporter_id;
    private Long category_id;

}
