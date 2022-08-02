package jp.kobe_u.cs.daikibo.captainlunch.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.captainlunch.domain.entity.Cafeteria;

@Repository
public interface CafeteriaRepository extends CrudRepository<Cafeteria, Long> {
    // Cafeteria findById(String cid);
    Cafeteria findByCid(Long cid);
}
