package com.example.demo.common;


import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description : 공통 static class
 * @Modification Information
 *               수정일 수정자 수정내용
 *               ---------- --------- -------------------------------
 *
 *
 * @author GEONLEE
 * @version 1.0.0
 * @since 2022-11-07
 */
public class CmmnVar {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmmnVar.class);

    public static ConfigurableApplicationContext CTX;
    public static Gson GSON = new Gson();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    public static Timestamp strToTimestamp(String date){
        try{
            return new Timestamp(DATE_TIME_FORMAT.parse(date).getTime());
        }catch(ParseException e){
            throw new IllegalArgumentException();
        }
    }

    public static String timestampToStr(Timestamp timestamp){
        Date date = new Date();
        date.setTime(Long.parseLong(String.valueOf(timestamp.getTime())));
        return DATE_TIME_FORMAT.format(date);
    }

    public static Timestamp getNowToTimestampe(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    public static String getNowToStr(){
        Date date = new Date();
        String now = DATE_TIME_FORMAT.format(date);
        return now;
    }

    public static Date strToDate(String dateStr){
        Date date = null;
        try {
            date = DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("Date로 변환하는데 실패했습니다.["+dateStr+"]");
        }
        return date;
    }
}
