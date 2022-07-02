package info.dallog.bookmark.service;

import info.dallog.bookmark.domain.Bookmark;
import info.dallog.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public Bookmark save(Bookmark bookmark){
        bookmarkRepository.save(bookmark);
        return bookmark;
    }

    public List<Bookmark> findBookmarks(){
        return bookmarkRepository.findAll();
    }

    public List<Bookmark> findByName(String name){
        return bookmarkRepository.findByName(name);
    }
}
