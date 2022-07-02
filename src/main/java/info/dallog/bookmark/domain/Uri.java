package info.dallog.bookmark.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Uri {
    private String domain;
    private String parameter;

    public Uri() {

    }
}
