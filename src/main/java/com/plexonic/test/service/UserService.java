package com.plexonic.test.service;

import com.plexonic.test.domain.DAU;
import com.plexonic.test.domain.Request;
import com.plexonic.test.domain.RetentionType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Aram Kirakosyan.
 */
public interface UserService {

    /**
     * Calculates DAUs for given dates.
     *
     * @param date array of dates.
     * @return List of DAU objects.
     */
    List<DAU> calculateDAUs (Date[] date);

    /**
     * Calculates user retention for given day.
     *
     * @param type retention type.
     * @param date given date.
     * @return retention for given day and type.
     */
    Double calculateRetention (RetentionType type, Date date);

}
