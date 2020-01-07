package com.zj.wz.wbyx.baseandroid.mvp.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zj.wz.wbyx.baseandroid.dagger.component.AppComponent;
import com.zj.wz.wbyx.baseandroid.dagger.component.DaggerAppComponent;
import com.zj.wz.wbyx.baseandroid.dagger.module.AppModule;
import com.zj.wz.wbyx.baseandroid.download.DownloadConfiguration;
import com.zj.wz.wbyx.baseandroid.download.DownloadManager;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;


/**
 * FileName: BaseApplication
 * Author: 曹伟
 * Date: 2019/9/12 17:22
 * Description:
 */

public class BaseApplication extends Application {

    private static AppComponent mAppComponent;
    private static BaseAppManager mAppManager;
    private static RefWatcher mRefWatcher;

    public static boolean DEBUGMODE = false;

    public static String getDormitory() {
        return dormitory;
    }

    public static void setDormitory(String dormitory) {

        BaseApplication.dormitory = dormitory;
    }

    public static String dormitory = "" ;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white);
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidUtils.init(this);

        initAppInject(this);

        initDownloader(this);

        Hawk.init(this).build();
    }

    public static void setDebugMode(boolean debugmode) {
        BaseApplication.DEBUGMODE = debugmode;

        if (BaseApplication.DEBUGMODE) {
            mRefWatcher = LeakCanary.install((Application) AndroidUtils.getContext());

            Stetho.initializeWithDefaults(AndroidUtils.getContext());

            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .build());

        }
    }

    public static RefWatcher getRefWatcher(Context context) {
        // LeakCanary: Detect all memory leaks!
        // LeakCanary.install() returns a pre configured RefWatcher. It also
        // installs an ActivityRefWatcher that automatically detects if an activity is
        // leaking after Activity.onDestroy() has been called.
        return mRefWatcher;
    }

    private static void initDownloader(Context context) {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(6);
        DownloadManager.getInstance().init(context, configuration);
    }

    public static BaseAppManager getAppManager() {
        if (mAppManager == null) {
            synchronized (BaseApplication.class) {
                if (mAppManager == null) {
                    mAppManager = BaseAppManager.getManager();
                }
            }
        }
        return mAppManager;
    }

    public void initAppInject(Context context) {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule((Application) context))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
    }

    public static void exitApp() {
        mAppManager.exitApp();
    }

    public static void restartApp(@NonNull Activity activity) {
        Intent intent = activity.getPackageManager()
                .getLaunchIntentForPackage(activity.getPackageName());
        Class<? extends Activity> resolvedActivityClass;
        if (intent != null && intent.getComponent() != null) {
            try {
                resolvedActivityClass = (Class<? extends Activity>) Class.forName(intent.getComponent()
                        .getClassName());
                intent.setClass(activity, resolvedActivityClass);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                if (intent.getComponent() != null) {
                    //If the class name has been set, we force it to simulate a Launcher launch.
                    //If we don't do this, if you restart from the error activity, then press home,
                    //and then launch the activity from the launcher, the main activity appears twice on the backstack.
                    //This will most likely not have any detrimental effect because if you set the Intent component,
                    //if will always be launched regardless of the actions specified here.
                    intent.setAction(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                }
                activity.finish();
                activity.startActivity(intent);
                exitApp();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                //Should not happen, print it to the log!
            }
        }

    }

}