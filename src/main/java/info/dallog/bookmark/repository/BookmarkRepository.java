package info.dallog.bookmark.repository;

import info.dallog.bookmark.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookmarkRepository {

    private final EntityManager em;

    public void save(Bookmark bookmark){
        em.persist(bookmark);
    }

    public Bookmark findOne(Long id){
        return em.find(Bookmark.class , id);
    }

    public List<Bookmark> findAll(){
        return em.createQuery("select b from Bookmark b" , Bookmark.class)
                .getResultList();
    }

    public List<Bookmark> findByName(String name){
        return em.createQuery("select b from Bookmark b where b.name = :name" , Bookmark.class)
                .setParameter("name" , name)
                .getResultList();
    }
}
