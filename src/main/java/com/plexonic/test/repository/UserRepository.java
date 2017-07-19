package com.plexonic.test.repository;

import com.plexonic.test.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @author Aram Kirakosyan.
 */
public interface UserRepository extends CrudRepository <User, Long> {

     /**
     * Returns DAU for given day.
     * We use between for getting better performance and avoid date converting.
     *
     * @param startDate start date (dd/mm/yyyy 00:00:00).
     * @param endDate end date (dd/mm/yyyy 23:59:59).
     * @return DAU for given day.
     */
    @Query("SELECT COUNT (distinct r.user) FROM Request r where r.requestDate BETWEEN :startDate AND :endDate")
    Integer getDAU (@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * Returns number of users which are installed at first day and are active at last day.
     *
     * @param firstDayStart first day start (dd/mm/yyyy 00:00:00).
     * @param firstDayEnd  first day end (dd/mm/yyyy 23:59:59).
     * @param lastDayStart day start after given period (DAY1, DAY7, DAY40).
     * @param lastDayEnd day end after given period (DAY1, DAY7, DAY40).
     * @return returns number of users which are installed at first day and are active at last day.
     */
    @Query("SELECT COUNT (distinct r.user) FROM Request r join r.user u where u.installDate BETWEEN :firstDayStart AND :firstDayEnd AND r.requestDate BETWEEN :lastDayStart AND :lastDayEnd" )
    Integer getRetention (@Param("firstDayStart") Date firstDayStart,
                          @Param("firstDayEnd") Date firstDayEnd,
                          @Param("lastDayStart") Date lastDayStart,
                          @Param("lastDayEnd") Date lastDayEnd);

     /**
     * Returns number of users installed for given day.
     *
     * @param startDate start date (dd/mm/yyyy 00:00:00).
     * @param endDate end date (dd/mm/yyyy 23:59:59)
     * @return number of users installed for given day.
     */
    @Query("SELECT COUNT (u.userId) FROM User u where u.installDate BETWEEN :startDate AND :endDate" )
    Integer getNumberOfInstalledUsers (@Param("startDate") Date startDate,
                          @Param("endDate") Date endDate);

}
