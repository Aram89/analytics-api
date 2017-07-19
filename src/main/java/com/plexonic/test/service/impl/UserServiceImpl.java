package com.plexonic.test.service.impl;

import com.plexonic.test.domain.DAU;
import com.plexonic.test.domain.RetentionType;
import com.plexonic.test.repository.UserRepository;
import com.plexonic.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Aram Kirakosyan.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * User repository.
     */
    private UserRepository userRepository;

    /**
     * Constructor for autowiring user repository.
     *
     * @param userRepository userRepository.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @see com.plexonic.test.service.UserService
     */
    @Override
    public List<DAU> calculateDAUs(Date[] date) {
        List <DAU> daus = new ArrayList<>();
        for (Date aDate : date) {
            // Get the begining of the day (00:00:00), in order to use same format as in DB.
            Date start = convertDateTime(aDate, true);
            // Get the end time of the day. (23:59:59).
            Date end = convertDateTime(aDate, false);

            // Get dau for concrete day.
            Integer count = userRepository.getDAU(start, end);
            DAU dau = new DAU(aDate, count);
            daus.add(dau);
        }
        return daus;
    }

    /**
     * @see com.plexonic.test.service.UserService
     */
    @Override
    public Double calculateRetention(RetentionType type, Date date) {
        Date firstDayStart = convertDateTime(date, true);
        Date firstDayEnd = convertDateTime(date, false);
        Date lastDayStart = addDays(type, firstDayStart);
        Date lastDayEnd = addDays(type, firstDayEnd);
        int numberOfInstalledUsers = userRepository.getNumberOfInstalledUsers(firstDayStart, firstDayEnd);
        int retention = userRepository.getRetention(firstDayStart, firstDayEnd, lastDayStart, lastDayEnd);
        if (numberOfInstalledUsers == 0) {
            return 0d;
        }
        double retentionRate = (double)retention/(double)numberOfInstalledUsers;
        return retentionRate*100;


    }

    /**
     * This method is used for adding days to given date.
     * Number of the days to be added is in type parameter.
     *
     * @param type retention typy.
     * @param date given date.
     * @return date with added days.
     */
    private Date addDays(RetentionType type, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, type.getValue());
        return c.getTime();
    }


    /**
     * This method is used for converting Date in dd/mm/yyyy to datetime in yyyy/mm/yyyy hh:mm:ss
     * with the start of the day or end of the day.
     * It is useful when we retrieving records for concrete day from Database.
     *
     * @param date given date.
     * @param isStartDate if true convert to the start of the day, otherwise to the end.
     * @return the start or end time of the day in yyyy/mm/yyyy hh:mm:ss format.
     */
    private Date convertDateTime(Date date, boolean isStartDate) {
        int second = 0;
        int minute = 0;
        int hour = 0;
        if (!isStartDate) {
            second = 59;
            minute = 59;
            hour = 23;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }
}
