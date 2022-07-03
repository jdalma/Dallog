package info.dallog.bookmark;

import info.dallog.bookmark.domain.Bookmark;
import info.dallog.bookmark.domain.Uri;
import info.dallog.bookmark.service.BookmarkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class BookmarkTests {

    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 북마크_추가(){
        // given
        Bookmark bookmark = new Bookmark();
        bookmark.setName("SI 탈출");
        bookmark.setUri(new Uri("https://jdalma.github.io/" , "docs/algorithm"));
        bookmark.setStar(true);

        // when
        Long newId = bookmarkService.save(bookmark);
        System.out.println(newId);

        // then
        Assertions.assertThat(bookmarkService.findByName("SI 탈출").size() > 0);
    }
}
