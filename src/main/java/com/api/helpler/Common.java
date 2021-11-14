package com.api.helpler;

import java.util.Calendar;
import java.util.Date;

public final class Common {
  public static Date time(int time) {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.SECOND, time);
    return c.getTime();
  }
}
