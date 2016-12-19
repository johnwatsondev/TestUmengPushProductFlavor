package com.jwdev.myproductflavordemo;

import android.app.Application;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmLog;

/**
 * Created by johnwatson on 16/12/2016.
 */
public class MyCustomApplication extends Application {
  private static final String TAG = MyCustomApplication.class.getName();
  //public static final String UPDATE_STATUS_ACTION =
  //    "com.jwdev.myproductflavordemo.action.UPDATE_STATUS";

  @Override public void onCreate() {
    super.onCreate();

    PushAgent mPushAgent = PushAgent.getInstance(this);
    mPushAgent.setDebugMode(true);

    mPushAgent.setPushCheck(true);

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
  }

  //12-19 14:58:04.828 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$drawable
  //12-19 14:58:04.831 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$layout
  //12-19 14:58:04.833 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$id
  //12-19 14:58:04.836 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$anim
  //12-19 14:58:04.838 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$style
  //12-19 14:58:04.840 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$string
  //12-19 14:58:04.841 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$array
  //12-19 14:58:04.843 1726-2154/? E/com.umeng.message.common.c: cn.jwdev.myproductflavor.paid.R$raw
  //12-19 14:58:04.843 1726-2154/? E/com.umeng.message.common.c: getRes(null,umeng_push_notification_default_small_icon)
  //12-19 14:58:04.843 1726-2154/? W/System.err: java.lang.IllegalArgumentException: ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have cn.jwdev.myproductflavor.paid.R$* configured in obfuscation. field=umeng_push_notification_default_small_icon
  //12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.common.c.a(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.common.c.c(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.UmengMessageHandler.getSmallIconId(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.UmengMessageHandler.a(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.UmengMessageHandler.dealWithNotificationMessage(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.UmengMessageHandler.handleMessage(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at com.umeng.message.UmengMessageCallbackHandlerService.onHandleIntent(Unknown Source)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at android.app.IntentService$ServiceHandler.handleMessage(IntentService.java:66)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at android.os.Handler.dispatchMessage(Handler.java:102)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at android.os.Looper.loop(Looper.java:148)
  //    12-19 14:58:04.844 1726-2154/? W/System.err:     at android.os.HandlerThread.run(HandlerThread.java:61)
}