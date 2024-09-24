package com.service;

import com.dao.ForexDataRepository;
import com.dto.ApiForexData;
import com.entity.ForexData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ForexService {


  @Value("${forex.api.url}")
  private String apiUrl;

  private final RestTemplate restTemplate;

  private final ForexDataRepository forexDataRepository;

  public List<ApiForexData> fetchForexData() {
    ResponseEntity<ApiForexData[]> response = restTemplate.getForEntity(apiUrl, ApiForexData[].class);
    return Arrays.asList(response.getBody());
  }

  public List<ForexData> getForexRates( LocalDate startDate, LocalDate endDate,String currency) {
    // 從資料庫查詢資料
    return forexDataRepository.findByCurrencyAndDateBetween(currency, startDate, endDate)
  }
}
