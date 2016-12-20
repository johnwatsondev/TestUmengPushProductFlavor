package com.jwdev.myproductflavordemo;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengDownloadResourceService;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;

/**
 * Created by johnwatson on 16/12/2016.
 * <br>http://dev.umeng.com/push/android/integration
 * <br>http://bbs.umeng.com/thread-5911-1-1.html
 */
public class MyApplication extends Application {
  private static final String TAG = MyApplication.class.getName();

  private static final String b = TAG;

  @Override public void onCreate() {
    super.onCreate();

    PushAgent mPushAgent = PushAgent.getInstance(this);
    mPushAgent.setDebugMode(true);

    //注册推送服务 每次调用register都会回调该接口
    mPushAgent.register(new IUmengRegisterCallback() {
      @Override public void onSuccess(String deviceToken) {
        UmLog.i(TAG, "device token: " + deviceToken);
        //sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
      }

      @Override public void onFailure(String s, String s1) {
        UmLog.i(TAG, "register failed: " + s + " " + s1);
        //sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
      }
    });

    //sdk开启通知声音
    mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);

    UmengMessageHandler messageHandler = new UmengMessageHandler() {

      @Override public int getSmallIconId(Context var1, UMessage var2) {
        int var3 = -1;

        try {
          if (!TextUtils.isEmpty(var2.icon)) {
            var3 = MyUtil.a(var1).c(var2.icon);
          }

          if (var3 < 0) {
            var3 = MyUtil.a(var1).c("umeng_push_notification_default_small_icon");
          }

          if (var3 < 0) {
            UmLog.d(b, "no custom notificaiton icon, fail back to app icon.");
            var3 = var1.getPackageManager()
                .getPackageInfo(var1.getPackageName(), 0).applicationInfo.icon;
          }

          if (var3 < 0) {
            UmLog.e(b,
                "Cann\'t find appropriate icon for notification, please make sure you have specified an icon for this notification or the app has defined an icon.");
          }
        } catch (Exception var5) {
          var5.printStackTrace();
        }

        return var3;
      }

      @Override public Bitmap getLargeIcon(Context var1, UMessage var2) {
        int a = 64;

        Bitmap var3 = null;

        try {
          if (var2.isLargeIconFromInternet()) {
            String var4 = UmengDownloadResourceService.getMessageResourceFolder(var1, var2)
                + var2.img.hashCode();
            var3 = BitmapFactory.decodeFile(var4);
          }

          int var7;
          if (var3 == null) {
            var7 = -1;
            if (!TextUtils.isEmpty(var2.largeIcon)) {
              var7 = MyUtil.a(var1).c(var2.largeIcon);
            }

            if (var7 < 0) {
              var7 = MyUtil.a(var1).c("umeng_push_notification_default_large_icon");
            }

            if (var7 > 0) {
              var3 = BitmapFactory.decodeResource(var1.getResources(), var7);
            }
          }

          if (var3 != null) {
            if (Build.VERSION.SDK_INT >= 11) {
              var7 = (int) var1.getResources().getDimension(17104902);
            } else {
              var7 = com.umeng.message.proguard.i.a(var1, (float) a);
            }

            Bitmap var5 = Bitmap.createScaledBitmap(var3, var7, var7, true);
            return var5;
          }
        } catch (Exception var6) {
          var6.printStackTrace();
        }

        return null;
      }

      /**
       * 自定义通知栏样式的回调方法
       * */
      @Override public Notification getNotification(Context context, UMessage msg) {
        UmLog.i(TAG, "msg.builder_id: " + msg.builder_id);

        UmLog.i(TAG, "getNotification msg: " + msg.title);
        UmLog.i(TAG, "getNotification msg: " + msg.text);

        switch (msg.builder_id) {
          case 1:
            Notification.Builder builder = new Notification.Builder(context);
            RemoteViews myNotificationView =
                new RemoteViews(context.getPackageName(), R.layout.notification_view);
            myNotificationView.setTextViewText(R.id.notification_title, msg.title);
            myNotificationView.setTextViewText(R.id.notification_text, msg.text);
            myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
                getLargeIcon(context, msg));
            //myNotificationView.setImageViewResource(R.id.notification_large_icon,
            //    R.drawable.custom_icon);
            myNotificationView.setImageViewResource(R.id.notification_small_icon,
                getSmallIconId(context, msg));
            builder.setContent(myNotificationView).setSmallIcon(getSmallIconId(context, msg))
                //.setSmallIcon(R.drawable.custom_icon)
                .setTicker(msg.ticker).setAutoCancel(true);

            return builder.getNotification();
          default:
            //默认为0，若填写的builder_id并不存在，也使用默认。
            return super.getNotification(context, msg);
        }
      }
    };
    mPushAgent.setMessageHandler(messageHandler);
  }
}