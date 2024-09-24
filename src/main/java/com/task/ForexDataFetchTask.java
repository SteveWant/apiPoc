package com.task;

import com.dao.ForexDataRepository;
import com.dto.ApiForexData;
import com.entity.ForexData;
import com.service.ForexService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ForexDataFetchTask {

  private final ForexService forexService;
  private final ForexDataRepository forexDataRepository;


  @Scheduled(cron = "0 0 18 * * ?")  // 每日 18:00 執行
  public void saveForexData() {
    List<ApiForexData> forexDataList = forexService.fetchForexData();

    for (ApiForexData forexData : forexDataList) {
      ForexData entity = new ForexData();
      entity.setDate(LocalDate.parse(forexData.getDate(), DateTimeFormatter.ofPattern("yyyyMMdd")));
      entity.setExchangeRate(Double.parseDouble(forexData.getUsdNtd()));

      forexDataRepository.save(entity);
    }
  }
}
