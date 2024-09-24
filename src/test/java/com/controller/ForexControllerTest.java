package com.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.controller.ForexController;
import com.entity.ForexData;
import com.service.ForexService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class ForexControllerTest {

  private MockMvc mockMvc;

  @InjectMocks
  private ForexController forexController;

  @Mock
  private ForexService forexService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(forexController).build();
  }

  @Test
  public void testGetForexHistory_Success() throws Exception {
    // 設置模擬服務返回的數據
    LocalDate startDate = LocalDate.of(2024, 1, 1);
    LocalDate endDate = LocalDate.of(2024, 1, 5);

    List<ForexData> mockRates = Arrays.asList(
        new ForexData(1L, LocalDate.of(2024, 1, 3), "usd", 31.01),
        new ForexData(2L, LocalDate.of(2024, 1, 4), "usd", 31.016)
    );

    when(forexService.getForexDatas(startDate, endDate, "usd")).thenReturn(mockRates);

    String requestJson = "{ \"startDate\": \"2024/01/01\", \"endDate\": \"2024/01/05\", \"currency\": \"usd\" }";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/forex/history")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.error.code").value("0000"))
        .andExpect(jsonPath("$.currency[0].date").value("2024-01-03"))
        .andExpect(jsonPath("$.currency[0].usd").value(31.01))
        .andExpect(jsonPath("$.currency[1].usd").value(31.016));

    verify(forexService, times(1)).getForexDatas(startDate, endDate, "usd");
  }

  @Test
  public void testGetForexHistory_InvalidDateRange() throws Exception {
    // 測試錯誤情況 - 日期超過一年的範圍
    String requestJson = "{ \"startDate\": \"2022/01/01\", \"endDate\": \"2024/01/01\", \"currency\": \"usd\" }";

    mockMvc.perform(MockMvcRequestBuilders.post("/api/forex/history")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.error.code").value("E001"))
        .andExpect(jsonPath("$.error.message").value("日期區間不符"));

    verify(forexService, never()).getForexDatas(any(), any(), anyString());
  }
}
