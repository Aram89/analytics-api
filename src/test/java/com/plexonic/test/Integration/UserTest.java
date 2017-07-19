package com.plexonic.test.Integration;

import com.plexonic.test.BaseTest;
import com.plexonic.test.domain.DAU;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.assertEquals;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Aram Kirakosyan
 */
public class UserTest extends BaseTest{

    @Test
    @SqlGroup
            ({
            @Sql("/sql/test.sql"),
            @Sql(scripts = "/sql/clean-db.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void calculateDAUTest() throws Exception {
        MvcResult result  = mockMvc.perform(get("/dau?dates=21/06/2017,20/06/2017"))
                .andExpect(status().isOk()).andReturn();

        // Get response body in json String
        String content = result.getResponse().getContentAsString();

        // Convert to DAU list.
        List <DAU> daus = (List<DAU>) convertJsonToList(content, DAU.class);

        assertEquals(1L, (long)daus.get(0).getValue());
        assertEquals(2L, (long)daus.get(1).getValue());
    }

    @Test
    @SqlGroup
            ({@Sql("/sql/test.sql"),@Sql(scripts = "/sql/clean-db.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
            })
    public void calculateRetentionTest() throws Exception {
        MvcResult result  = mockMvc.perform(get("/retention?type=DAY1&date=20/06/2017"))
                .andExpect(status().isOk()).andReturn();

        // Get response body in json String
        String content = result.getResponse().getContentAsString();
        assertEquals("50.0", content);
    }
}
