package info.dallog.bookmark.repository;

import info.dallog.bookmark.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookmarkRepository {

    private final EntityManager em;

    public Long save(Bookmark bookmark){
        if(bookmark.getId() == null){
            em.persist(bookmark);
        }
        else{
            em.merge(bookmark);
        }
        return bookmark.getId();
    }

    public Bookmark findOne(Long id){
        return em.find(Bookmark.class , id);
    }

    public Bookmark findOneLock(Long id){
        return em.find(Bookmark.class , id , LockModeType.PESSIMISTIC_WRITE);
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
