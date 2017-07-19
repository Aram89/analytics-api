package com.plexonic.test.controller;

import com.plexonic.test.domain.DAU;
import com.plexonic.test.domain.RetentionType;
import com.plexonic.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Aram Kirakosyan.
 */
@RestController
public class UserController {

    private UserService userService;

    /**
     * Ctor for autowiring user service.
     *
     * @param userService user service.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for getting daily active users.
     *
     * @param dates array of dates in dd//mm/yyyy format.
     * @return date and coreeponding dau in json format.
     */
    @RequestMapping(value = "dau", method = RequestMethod.GET)
    public List<DAU> getDAU(@RequestParam("dates") @DateTimeFormat(pattern = "dd/MM/yyyy") Date[] dates) {
        return userService.calculateDAUs(dates);
    }

    /**
     * Endpoint for getting user retention.
     *
     * @param type retention type. Accepted values are DAY1,DAY7,DAY40.
     * @param date date for whihc retention must be calculated (dd/mm/yyyy format).
     * @return retention rate for given day and type.
     */
    @RequestMapping(value = "retention", method = RequestMethod.GET)
    public Double getRetention(@RequestParam("type") RetentionType type, @RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        return userService.calculateRetention(type, date);

    }

}
