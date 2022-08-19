package jp.kobe_u.cs.daikibo.captainlunch.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.kobe_u.cs.daikibo.captainlunch.application.dto.RemainingSeatsDto;
import jp.kobe_u.cs.daikibo.captainlunch.application.dto.ReservationForm;
import jp.kobe_u.cs.daikibo.captainlunch.domain.entity.Reservation;
import jp.kobe_u.cs.daikibo.captainlunch.domain.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired

    ReservationRepository rRepo; // レポジトリ
    @Autowired
    CafeteriaService cService; // カフェサービス

    public Reservation createReservation(ReservationForm form) {
        // reserv.setSitFlag(false);
        // reserv.setLeaveFlag(false);
        Reservation r = new Reservation();
        r.setUid(form.getUid());
        r.setSeatNum(form.getSeatNum());
        r.setDate(new Date());
        r.setSlotId(form.getSlotId());
        String since = calculateSince(form.getSlotId());
        r.setSince(since);
        // r.setSince(form.getSince());
        // r.setDate(new Date());
        return rRepo.save(r);
    }

    public int getReservationNumBySlotId(Date date, int slotId) {

        String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
        str = str + " 00:00:00";

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date since = new Date();

        try {
            since = sdFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(since);
        calendar.add(Calendar.DATE, 1);

        Date until = calendar.getTime();

        // System.out.println(since + " ~ " + until);

        List<Reservation> list = rRepo.findAllByDateBetweenAndSlotId(since, until, slotId);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Reservation reservation = list.get(i);
            sum += reservation.getSeatNum();
        }
        return sum;
    }

    public int calculateRemainingSeatsNum(Date date, int slotId) {
        int remaining = cService.getChairNum() - getReservationNumBySlotId(date, slotId);
        return remaining;
    }

    public List<RemainingSeatsDto> getTodayRemainingSeats(Date date) {
        ArrayList<RemainingSeatsDto> list = new ArrayList<RemainingSeatsDto>();

        int slotNum = cService.getSlotNum(); // スロット数
        for (int i = 0; i < slotNum; i++) {
            RemainingSeatsDto slot = new RemainingSeatsDto();
            slot.setRemainSeatsNum(calculateRemainingSeatsNum(date, i + 1));
            slot.setSlotId(i + 1);
            String since = calculateSince(i + 1);
            slot.setSince(since);
            list.add(slot);
        }
        return list;
    }

    public String calculateSince(int slotId) {
        int hour = 11;
        int minute = 0;
        minute += (slotId - 1) * cService.getSlotTime();
        if (minute >= 60) {
            int minuteToHour = minute / 60;
            hour += minuteToHour;
            minute -= 60 * minuteToHour;
        }
        String since = Integer.valueOf(hour).toString() + ":" + Integer.valueOf(minute).toString();
        if (minute < 10) {
            since = Integer.valueOf(hour).toString() + ":0" + Integer.valueOf(minute).toString();
        }
        return since;
    }

    // public List<RemainingSeatsDto> getTommorrowRemainingSeats(Date date) {
    // ArrayList<RemainingSeatsDto> list = new ArrayList<RemainingSeatsDto>();

    // int slotNum = cService.getSlotNum(); // スロット数

    // return list;
    // }

    // public List<RemainingSeatsDto> getDayAfterTommorrowRemainingSeats(Date date)
    // {
    // ArrayList<RemainingSeatsDto> list = new ArrayList<RemainingSeatsDto>();

    // int slotNum = cService.getSlotNum(); // スロット数

    // return list;
    // }
}