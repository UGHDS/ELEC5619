package com.usyd.group08.elec5619.rest;

import com.usyd.group08.elec5619.models.Stall;
import com.usyd.group08.elec5619.models.StallDate;
import com.usyd.group08.elec5619.models.Venue;
import com.usyd.group08.elec5619.repositries.StallDateRepository;
import com.usyd.group08.elec5619.repositries.StallRepository;
import com.usyd.group08.elec5619.repositries.VenueRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StallController.class)
class StallControllerTest {
    @Autowired
    private MockMvc mockMvc;
    MockHttpSession mockSession;
    @MockBean
    StallRepository stallRepository;
    @MockBean
    VenueRepository venueRepository;
    @MockBean
    StallDateRepository stallDateRepository;

    @Test
    void getStallsByVenueId() throws Exception {
        String venueID = "123";
        Venue mockVenue = new Venue();
        List<Stall> stalls = Arrays.asList(new Stall(), new Stall());
        mockVenue.setStalls(stalls);

        when(venueRepository.findById(Integer.parseInt(venueID))).thenReturn(Optional.of(mockVenue));

        mockMvc.perform(get("/api/stalls").param("venueID", venueID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getStallDates() throws Exception {
        int stallId = 1;
        List<StallDate> mockStallDates = Arrays.asList(
                new StallDate(),
                new StallDate()
        );

        when(stallDateRepository.getStallDateByStallId(stallId)).thenReturn(mockStallDates);

        // When & Then
        mockMvc.perform(get("/api/stalls/{id}/stallDates", stallId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}