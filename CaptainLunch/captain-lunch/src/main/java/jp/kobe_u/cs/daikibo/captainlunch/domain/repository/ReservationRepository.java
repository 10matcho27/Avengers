package jp.kobe_u.cs.daikibo.captainlunch.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import jp.kobe_u.cs.daikibo.captainlunch.domain.entity.Reservation;

@Repository

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    // public List<Reservation> findAllByUidAndSitFlagOderedByDateDesc(String uid,
    // Boolean sitflag, Date date);

    public List<Reservation> findAllByDateBetweenAndSlotId(Date since, Date until, int slotId);

    public List<Reservation> findAllByDateAndSlotId(Date date, int slotId);

    // Reservation findByUidAndSitFlagAndDateAndDateBetween(String uid, Date since,
    // Date until);
}
