package com.commonsware.android.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.commonsware.cwac.wakeful.WakefulIntentService;

public class PollReceiver extends BroadcastReceiver {
  private static final int PERIOD=15000; // 15 seconds

  @Override
  public void onReceive(Context ctxt, Intent i) {
    if (i.getAction()==null) {
      WakefulIntentService.sendWakefulWork(ctxt, ScheduledService.class);
    }

    scheduleAlarms(ctxt);
  }

  static void scheduleAlarms(Context ctxt) {
    AlarmManager mgr=
      (AlarmManager)ctxt.getSystemService(Context.ALARM_SERVICE);
    Intent i=new Intent(ctxt, PollReceiver.class);
    PendingIntent pi=PendingIntent.getBroadcast(ctxt, 0, i, 0);
    Intent i2=new Intent(ctxt, EventDemoActivity.class);
    PendingIntent pi2=PendingIntent.getActivity(ctxt, 0, i2, 0);

    AlarmManager.AlarmClockInfo ac=
      new AlarmManager.AlarmClockInfo(System.currentTimeMillis()+PERIOD,
        pi2);

    mgr.setAlarmClock(ac, pi);
  }
}
