package com.commonsware.android.alarmclock;

import android.app.Activity;
import android.os.Bundle;

//
public class EventDemoActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    android.util.Log.e(getClass().getSimpleName(), Integer.toHexString(hashCode()));

    if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
      getFragmentManager().beginTransaction()
                          .add(android.R.id.content,
                               new EventLogFragment()).commit();

      PollReceiver.scheduleAlarms(this);
    }
  }
}