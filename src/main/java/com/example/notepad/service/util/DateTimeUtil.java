package com.example.notepad.service.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeUtil {

  public static final boolean noteDateDueToday(LocalDateTime date) {

    LocalDate today = LocalDate.now().plusDays(1L);

    if (today.getYear() == date.getYear()
        && today.getMonthValue() == date.getMonthValue()
        && today.getDayOfMonth() == date.getDayOfMonth()) {
      return true;
    }

    return false;
  }
}
