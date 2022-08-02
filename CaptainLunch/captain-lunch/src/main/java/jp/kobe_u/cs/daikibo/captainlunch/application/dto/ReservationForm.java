package jp.kobe_u.cs.daikibo.captainlunch.application.dto;

import java.util.Date;

import jp.kobe_u.cs.daikibo.captainlunch.domain.entity.Reservation;
import lombok.Data;

@Data
public class ReservationForm {
    String uid;
    int slotId;
    int seatNum;
    Date date;
    String since;

    public Reservation toEntity() {
        // return new Reservation(null, this.getUid(), null, this.getSlotId(),
        // this.getSeatNum(), false, false);

        // return new Reservation(null, null, null, null, null, false, false);
        // return new Reservation(null, uid, new Date(), slotId, seatNum, false, false);
        Reservation reservation = new Reservation();
        reservation.setUid(uid);
        reservation.setSlotId(slotId);
        reservation.setDate(date);
        reservation.setSince(since);
        reservation.setSeatNum(seatNum);
        reservation.setSitFlag(false);
        reservation.setLeaveFlag(false);

        return reservation;
    }
}