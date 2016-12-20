package com.jwdev.myproductflavordemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmLog;
import java.lang.reflect.Field;

/**
 * Created by johnwatson on 20/12/2016.
 */

public class MyUtil {
  private static final String a = MyUtil.class.getSimpleName();

  private static MyUtil b;
  private Context c;
  private String d;
  private static Class e = null;
  private static Class f = null;
  private static Class g = null;
  private static Class h = null;
  private static Class i = null;
  private static Class j = null;
  private static Class k = null;
  private static Class l = null;

  private static final String PACKAGE_NAME = "com.jwdev.myproductflavordemo";

  private MyUtil(Context var1) {
    this.c = var1.getApplicationContext();
    UmLog.d(a, "packageName=" + this.c.getPackageName());

    try {
      PackageManager pm = this.c.getPackageManager();
      PackageInfo packageInfo = pm.getPackageInfo(this.c.getPackageName(), 0);
      UmLog.d(a, "PackageManager packageName=" + packageInfo.packageName);
    } catch (PackageManager.NameNotFoundException e) {
    }

    if (TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())) {
      UmLog.d(a, "PushAgent getResourcePackageName = null");
    } else {
      UmLog.d(a, "PushAgent getResourcePackageName = " + PushAgent.getInstance(this.c)
          .getResourcePackageName());
    }

    try {
      //f = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      f = Class.forName(PACKAGE_NAME + ".R$drawable");
    } catch (ClassNotFoundException var10) {
      UmLog.e(a, var10.getMessage());
    }

    try {
      //g = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$layout");

      g = Class.forName(PACKAGE_NAME + ".R$layout");
    } catch (ClassNotFoundException var9) {
      UmLog.e(a, var9.getMessage());
    }

    try {
      //e = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$id");
      e = Class.forName(PACKAGE_NAME + ".R$id");
    } catch (ClassNotFoundException var8) {
      UmLog.e(a, var8.getMessage());
    }

    try {
      //h = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$anim");
      h = Class.forName(PACKAGE_NAME + ".R$anim");
    } catch (ClassNotFoundException var7) {
      UmLog.e(a, var7.getMessage());
    }

    try {
      //i = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$style");
      i = Class.forName(PACKAGE_NAME + ".R$style");
    } catch (ClassNotFoundException var6) {
      UmLog.e(a, var6.getMessage());
    }

    try {
      //j = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$string");
      j = Class.forName(PACKAGE_NAME + ".R$string");
    } catch (ClassNotFoundException var5) {
      UmLog.e(a, var5.getMessage());
    }

    try {
      //k = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$array");
      k = Class.forName(PACKAGE_NAME + ".R$array");
    } catch (ClassNotFoundException var4) {
      UmLog.e(a, var4.getMessage());
    }

    try {
      //l = Class.forName((!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName())
      //    ? PushAgent.getInstance(this.c).getResourcePackageName() : this.c.getPackageName())
      //    + ".R$raw");
      l = Class.forName(PACKAGE_NAME + ".R$raw");
    } catch (ClassNotFoundException var3) {
      UmLog.e(a, var3.getMessage());
    }
  }

  public static MyUtil a(Context var0) {
    if (b == null) {
      b = new MyUtil(var0);
    }

    return b;
  }

  public int a(String var1) {
    return this.a(h, var1);
  }

  public int b(String var1) {
    return this.a(e, var1);
  }

  public int c(String var1) {
    return this.a(f, var1);
  }

  public int d(String var1) {
    return this.a(g, var1);
  }

  public int e(String var1) {
    return this.a(i, var1);
  }

  public int f(String var1) {
    return this.a(j, var1);
  }

  public int g(String var1) {
    return this.a(k, var1);
  }

  public int h(String var1) {
    return this.a(l, var1);
  }

  private int a(Class<?> var1, String var2) {
    if (var1 == null) {
      UmLog.e(a, "getRes(null," + var2 + ")");
      throw new IllegalArgumentException(
          "ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have "
              + this.c.getPackageName()
              + ".R$* configured in obfuscation. field="
              + var2);
    } else {
      try {
        Field var3 = var1.getField(var2);
        int var4 = var3.getInt(var2);
        UmLog.e(a, "var4 = " + var4);
        return var4;
      } catch (Exception var5) {
        UmLog.e(a, "getRes(" + var1.getName() + ", " + var2 + ")");
        UmLog.e(a,
            "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
        UmLog.e(a, var5.getMessage());
        return -1;
      }
    }
  }

  //public void i(String var1) {
  //  this.d = var1;
  //}

  //public String a() {
  //  return TextUtils.isEmpty(this.d) ? this.c.getPackageName() : this.d;
  //}
}