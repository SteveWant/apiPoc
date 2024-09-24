package com.controller;

import com.dto.ApiResponse;
import com.dto.ErrorResponse;
import com.dto.ForexRequest;
import com.entity.ForexData;
import com.service.ForexService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forex")
public class ForexController {

  @Autowired
  private ForexService forexService;

  @PostMapping("/history")
  public ApiResponse getForexHistory(@RequestBody ForexRequest request) {
    // 取得當前日期 - 1 天
    LocalDate today = LocalDate.now().minusDays(1);
    LocalDate oneYearAgo = today.minus(1, ChronoUnit.YEARS);

    // 將傳入的日期格式轉換
    LocalDate startDate = LocalDate.parse(request.getStartDate(),
        DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    LocalDate endDate = LocalDate.parse(request.getEndDate(),
        DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    // 日期區間驗證
    if (startDate.isBefore(oneYearAgo) || endDate.isAfter(today) || startDate.isAfter(endDate)) {
      return new ApiResponse(new ErrorResponse("E001", "日期區間不符"), null);
    }

    // 查詢匯率資料
    List<ForexData> rates = forexService.getForexRates(startDate, endDate, request.getCurrency());

    return new ApiResponse(new ErrorResponse("0000", "成功"), rates);
  }
}
