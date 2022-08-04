package jp.kobe_u.cs.daikibo.captainlunch.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cafeteria {
    @Id
    Long cid;
    int allSeatNum;
    int slotNum;
    int slotTime;
}
