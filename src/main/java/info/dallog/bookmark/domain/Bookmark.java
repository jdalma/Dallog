package info.dallog.bookmark.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Bookmark {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Uri uri;

    private boolean star;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;
}