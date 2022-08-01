package jp.kobe_u.cs.daikibo.captainlunch.application.dto;

import java.util.Date;
import lombok.Data;

@Data
public class RemainingSeatsDto {
    int slotId;
    String since;
    int remainSeatsNum;
}