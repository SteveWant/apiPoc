package com.dto;

import lombok.Data;

@Data
public class ForexRequest {

  ForexRequest() {
  }
  ForexRequest(String startDate, String endDate, String currency) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.currency = currency;
  }
  private String startDate;
  private String endDate;
  private String currency;

  // Getters and Setters
}