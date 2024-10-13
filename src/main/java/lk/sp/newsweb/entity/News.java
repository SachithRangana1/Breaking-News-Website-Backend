package lk.sp.newsweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String headline;

    @JoinColumn(name = "reporter_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    @JsonManagedReference
    private Reporter reporter;


    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
    @ManyToOne
    @JsonManagedReference
    private Category category;
}
