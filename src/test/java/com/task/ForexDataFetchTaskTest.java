package com.task;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.ForexService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ForexDataFetchTaskTest {

  @Mock
  private ForexService forexDataService;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private ForexDataFetchTask forexDataFetchTask;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }


}