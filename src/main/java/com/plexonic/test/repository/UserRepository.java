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

    @Query("SELECT COUNT (distinct r.user) FROM Request r where r.requestDate BETWEEN :startDate AND :endDate")
    Integer getDAU (@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT COUNT (distinct r.user) FROM Request r join r.user u where u.installDate BETWEEN :firstDayStart AND :firstDayEnd AND r.requestDate BETWEEN :lastDayStart AND :lastDayEnd" )
    Integer getRetention (@Param("firstDayStart") Date firstDayStart,
                          @Param("firstDayEnd") Date firstDayEnd,
                          @Param("lastDayStart") Date lastDayStart,
                          @Param("lastDayEnd") Date lastDayEnd);

    @Query("SELECT COUNT (u.userId) FROM User u where u.installDate BETWEEN :startDate AND :endDate" )
    Integer getNumberOfInstalledUsers (@Param("startDate") Date startDate,
                          @Param("endDate") Date endDate);

}
