package com.dto;

import com.entity.ForexData;
import java.util.List;
import lombok.Data;

@Data
public class ApiResponse {

  private ErrorResponse error;
  private List<ForexData> currency;

  public ApiResponse(ErrorResponse error, List<ForexData> currency) {
    this.error = error;
    this.currency = currency;
  }
}
