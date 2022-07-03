package info.dallog.bookmark.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Bookmark {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Uri uri;

    private boolean star;

    @CreationTimestamp
    private Date createTimestamp;

    public Bookmark(){}
}