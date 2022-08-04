package jp.kobe_u.cs.daikibo.captainlunch.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long rid;
    String uid;
    Date date;
    String since;
    int slotId;
    int seatNum;
    boolean sitFlag;
    boolean leaveFlag;
}
