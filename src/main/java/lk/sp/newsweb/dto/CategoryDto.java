package lk.sp.newsweb.dto;

import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class CategoryDto {
    private String id;
    private String name;
    private String news_id;
}
