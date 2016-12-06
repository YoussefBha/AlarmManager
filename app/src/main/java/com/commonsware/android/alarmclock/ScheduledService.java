package com.commonsware.android.alarmclock;

import android.content.Intent;
import android.util.Log;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import de.greenrobot.event.EventBus;

public class ScheduledService extends WakefulIntentService {
  private Random rng=new Random();

  public ScheduledService() {
    super("ScheduledService");
  }

  @Override
  protected void doWakefulWork(Intent intent) {
    RandomEvent event=new RandomEvent(rng.nextInt());
    File log=new File(getExternalFilesDir(null), "alarmclock-log.txt");

    log.getParentFile().mkdirs();

    EventBus.getDefault().post(event);
    append(log, event);
  }

  private void append(File f, RandomEvent event) {
    try {
      FileOutputStream fos=new FileOutputStream(f, true);
      Writer osw=new OutputStreamWriter(fos);

      osw.write(event.when.toString());
      osw.write(" : ");
      osw.write(Integer.toHexString(event.value));
      osw.write('\n');
      osw.flush();
      fos.flush();
      fos.getFD().sync();
      fos.close();

      Log.d(getClass().getSimpleName(),
        "logged to "+f.getAbsolutePath());
    }
    catch (IOException e) {
      Log.e(getClass().getSimpleName(),
        "Exception writing to file", e);
    }
  }
}
