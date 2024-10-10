package lk.sp.newsweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    private String id;
    private String headline;
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    @ManyToOne
    private Reporter reporter;
}
