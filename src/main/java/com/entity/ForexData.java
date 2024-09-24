package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
public class ForexData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String currencyPair;
  @Column
  private double exchangeRate;
  @Column
  private LocalDate date;

  public ForexData() {

  }

  public ForexData(long id, LocalDate date, String currencyPair, double exchangeRate) {
    this.id = id;
    this.date = date;
    this.currencyPair = currencyPair;
    this.exchangeRate = exchangeRate;
  }
}
