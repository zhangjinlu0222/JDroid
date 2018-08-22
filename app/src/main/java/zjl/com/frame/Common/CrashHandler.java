package zjl.com.frame.Common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG  = false;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath()
            + "CrashLog/Log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private Context mContext;

    public static CrashHandler getInstance(){
        if (sInstance == null){
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    public CrashHandler() {

    }

    public void init(Context context){
        mContext = context.getApplicationContext();
        defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!DEBUG){
            return;
        }

        try{
            /**导出异常信息到SD卡*/
            DumpExceptionToSDCard(e);
            /**导出异常信息到服务器*/
            DumpExceptionToServer(e);
        }catch (Exception e1){
            e1.printStackTrace();
        }

        /*如果没有默认异常处理器，则交给我们处理掉*/
        if (defaultExceptionHandler != null){
            defaultExceptionHandler.uncaughtException(t,e);
        }else{
            Process.killProcess(Process.myPid());
        }
    }
    private void DumpExceptionToSDCard(Throwable e) {
        /*如果没有SD卡，则不能写入*/
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED){
            if (DEBUG){
                Log.e(TAG,e.toString());
            }
            return;
        }

        File dir = new File(PATH);

        if (!dir.exists()){
            dir.mkdirs();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));

        File file = new File(PATH + FILE_NAME + time +FILE_NAME_SUFFIX);
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            pw.println(time);

            dumpPhoneInfo(pw);

            pw.println();

            e.printStackTrace(pw);
            pw.close();
        }catch (Exception e1){
            Log.e(TAG,"dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);

        /**版本信息*/
        pw.print("App Version :");
        pw.print(pi.versionName);
        pw.print("_");
        pw.println(pi.versionCode);

        /**Android版本*/
        pw.print("OS Version :");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        /**手机厂商*/
        pw.print("Vendor :");
        pw.println(Build.MANUFACTURER);

        /**CPU架构*/
        pw.print("CPU ABIS:");
        pw.println(Build.SUPPORTED_ABIS);
    }

    private void DumpExceptionToServer(Throwable e) {
        /**此处实现伤处异常到服务器*/
    }

}
