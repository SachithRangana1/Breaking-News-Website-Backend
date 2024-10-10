package lk.sp.newsweb.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lk.sp.newsweb.entity.Reporter;

public class NewsDto {

    private String id;
    private String headline;
    private Reporter reporter;
}
