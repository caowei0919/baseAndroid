package com.zj.wz.wbyx.baseandroid.utils;


import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.zj.wz.wbyx.wbyxAndroid.event.TimeOutEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtils {
    private static CountDownTimer timer;

    /**
     *
     * @param dateType  日期格式
     * @return  当前日期的指定格式
     */
    public static String getNowDate(String dateType ,int offset){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, offset);
        String day = new SimpleDateFormat(dateType).format(cal.getTime());
        return day;
    }

    public static String intervalDay(long start_time) {

        return (start_time - System.currentTimeMillis()/1000 )/(3600*24) + "";
    }

    /*

     * 将时间戳转换为时间

     */

    public static String stampToDate(String s){

        String res;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long lt = new Long(s);

        Date date = new Date(lt);

        res = simpleDateFormat.format(date);

        return res;

    }

    /**
     * 时间戳
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowtime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        return mDateFormat.format(new Date());
    }
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMDtime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyyMMdd");
        return mDateFormat.format(new Date());
    }
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }

    /**
     * MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    public static Date getYMDHMSTime(String yymmdd_hhmmss) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
            date= mDateFormat.parse(yymmdd_hhmmss);
        } catch (Exception e) {
        }
        return date;
    }
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMDHMSDate(Date date) {
        if (date==null)
            return null;
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return mDateFormat.format(date);
    }
    @SuppressLint("SimpleDateFormat")
    public static Date getYMDHMSDate(String yymmddhhmmss) {
        if (yymmddhhmmss==null)
            return null;
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date date=null;
        try {
            date= mDateFormat.parse(yymmddhhmmss);
        } catch (Exception e) {
        }
        return date;
    }
    /**
     * MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMD() {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return mDateFormat.format(new Date());
    }

    /**
     * yyyy-MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getYMD(Date date) {
        if (date==null){
            return "";
        }
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return mDateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getMD(Date date) {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd");
        return mDateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getYMDDate(String yymmdd) {
        if (yymmdd==null)
            return null;
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date date=null;
        try {
            date= mDateFormat.parse(yymmdd);
        } catch (Exception e) {
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getYMDhmsDate(String yymmdd) {
        if (yymmdd==null)
            return null;
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
            date= mDateFormat.parse(yymmdd);
        } catch (Exception e) {
        }
        return date;
    }

    public static void getCountDownTimeUnit(String text ,String timeStemp, TextView mTvCountH) {
        //倒计时为0时执行此方法
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(Long.valueOf(timeStemp)*1000, 1000) {
            @Override
            public void onTick(long l) {
                int totalSeconds = (int) (l/1000);
                int seconds = totalSeconds % 60;
                int minutes = (totalSeconds / 60) % 60;
                int hours = totalSeconds / 3600 % 24;
                int day = totalSeconds /3600/24 ;
                String timeText = text;
                if(day > 0){
                    timeText = timeText + day + "天";
                }
                if (hours < 10) {
                    timeText = timeText + "0" + hours + "时" ;
                } else {
                    timeText = timeText + "" + hours + "时" ;
                }
                if (minutes < 10) {
                    timeText = timeText + "0" + minutes + "分" ;
                } else {
                    timeText = timeText + "" + minutes + "分" ;
                }
                if (seconds < 10) {
                    timeText = timeText + "0" + seconds + "秒" ;
                } else {
                    timeText = timeText + "" + seconds + "秒" ;
                }
                mTvCountH.setText(timeText);
            }

            @Override
            public void onFinish() {
                //倒计时为0时执行此方法
                EventBus.getDefault().post(new TimeOutEvent());
            }
        };
        timer.start();
    }
}