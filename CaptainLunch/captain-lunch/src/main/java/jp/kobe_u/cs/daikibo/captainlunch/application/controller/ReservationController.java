package jp.kobe_u.cs.daikibo.captainlunch.application.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.captainlunch.application.dto.ReservationForm;
import jp.kobe_u.cs.daikibo.captainlunch.domain.entity.Reservation;
import jp.kobe_u.cs.daikibo.captainlunch.domain.service.ReservationService;

@Controller
public class ReservationController {
    @Autowired
    ReservationService rService;

    @GetMapping("/{uid}/reservation")
    String showReservationView(@PathVariable String uid, Model model) {
        model.addAttribute("uid", uid);
        // String today;

        // // try {
        // today = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        // System.out.println("String型 = " + str);

        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        Date today = new Date();
        model.addAttribute("todayReservations", rService.getTodayRemainingSeats(today));
        // model.addAttribute("tomorrowReservations",
        // rService.getTommorrowRemainingSeats(today));
        // model.addAttribute("dayAfterTomorrowReservations",
        // rService.getDayAfterTommorrowRemainingSeats(today));

        model.addAttribute("reservationForm", new ReservationForm());

        return "seatReservationScreen";
    }

    @PostMapping("/{uid}/reservation")
    String ReservationComplete(@PathVariable String uid, @ModelAttribute("reservationForm") ReservationForm form,
            Model model) {

        Reservation r = rService.createReservation(form);

        // System.out.println(form.getSince());

        model.addAttribute("uid", uid);
        model.addAttribute("reservation", r);

        return "seatReservationCompleteScreen";
    }

}