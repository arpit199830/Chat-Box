package chat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Timestamp;

public class MyUtils {
    public static Date getToday(){
        Date d1=null;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        d1 = new java.sql.Date(currentTime);
        return d1;
    }
    public static Timestamp getTodayWithTime(){
    	Timestamp d1=null;
        long currentTime = new java.util.Date().getTime();
        d1 = new Timestamp(currentTime);
        return d1;
    }
    
    public static java.sql.Date getDate(String datStr){
	 java.sql.Date res = null;
	 try {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 java.util.Date  tDate = sdf.parse(datStr);
		 res = new Date(tDate.getTime());
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return res;
 }
    
    public static double getDouble(String no){
        double res = 0.0;
        try {
            res = Double.parseDouble(no);
        } catch (Exception e) {
        }
        return res;
    }
    
    public static int getInt(String no){
        int res = 0;
        try {
            res = Integer.parseInt(no);
        } catch (Exception e) {
        }
        return res;
    }

}
