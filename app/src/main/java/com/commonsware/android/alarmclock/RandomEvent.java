package com.commonsware.android.alarmclock;

import java.util.Calendar;
import java.util.Date;

public class RandomEvent {
  Date when=Calendar.getInstance().getTime();
  int value;
  
  RandomEvent(int value) {
    this.value=value;
  }
}
