package jp.kobe_u.cs.daikibo.captainlunch.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.captainlunch.domain.repository.CafeteriaRepository;

@Service
public class CafeteriaService {
    @Autowired
    CafeteriaRepository cRepo;

    public int getChairNum() {
        Long cid = 0l;
        // return cRepo.findFirstByCafeteria().getAllSeatNum();
        return cRepo.findByCid(cid).getAllSeatNum();
    }

    public int getSlotNum() {
        Long cid = 0l;
        return cRepo.findByCid(cid).getSlotNum();
    }

    public int getSlotTime() {
        Long cid = 0l;
        return cRepo.findByCid(cid).getSlotTime();
    }
}