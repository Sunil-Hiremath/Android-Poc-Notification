package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID="My Channel";
    private static final int NOTIFICATION_ID=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// use png image to show the large and small icons makes it transparent
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.c2,null);

        // whenever img is to be stored use bitmap(class or datatype)
        BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;

        Bitmap largeIcon=bitmapDrawable.getBitmap();

        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           notification=new Notification.Builder(this)
                    .setLargeIcon(largeIcon)  //setLargeIcon() this wants bitmap but .png is in drawable so first get drawable see above
                    .setSmallIcon(R.drawable.c1)
                    .setContentText("New Message")
                    .setSubText("New Message of Raman")
                    .setChannelId(CHANNEL_ID)//if user wants to block notification we need channel id
                    .build();
           nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
        }
        else{
            notification=new Notification.Builder(this)
                    .setLargeIcon(largeIcon)  //setLargeIcon() this wants bitmap but .png is in drawable so first get drawable see above
                    .setSmallIcon(R.drawable.c1)
                    .setContentText("New Message")
                    .setSubText("New Message of Raman")
                    .build();




        }

       nm.notify(NOTIFICATION_ID,notification);


    }
}