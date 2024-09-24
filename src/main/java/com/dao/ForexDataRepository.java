package com.dao;

import com.entity.ForexData;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForexDataRepository extends JpaRepository<ForexData, Long> {
  List<ForexData> findByCurrencyAndDateBetween(String currency, LocalDate startDate, LocalDate endDate);

}
