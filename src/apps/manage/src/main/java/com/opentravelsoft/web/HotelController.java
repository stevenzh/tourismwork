package com.opentravelsoft.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opentravelsoft.entity.vacation.Hotel;
import com.opentravelsoft.providers.HotelDao;

@Controller
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelDao hotelDao;

    @RequestMapping(method = RequestMethod.GET)
    void getInitialMessage() {
        // do nothing
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ModelAttribute("message")
    String create(Hotel user) {
        try {
            hotelDao.save(user);
            return "A n";
        } catch (Exception e) {
            return "An ";
        }
    }
}
