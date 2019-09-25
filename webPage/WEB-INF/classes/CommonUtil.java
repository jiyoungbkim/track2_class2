package common;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil{
	// 오늘날짜
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}
	// 날짜형식 검사
	public static boolean checkDate(String checkDate){
		SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyy-MM-dd");
		boolean checkD = true;
		dateFormat.setLenient(false);
		try{
			dateFormat.parse(checkDate);
		}catch(ParseException e){
			checkD = false;
		}
		return checkD;
	}
	// 오늘날짜 이전,이후 검사
	public static boolean checkToday(String inputDate) {
		boolean checkD = true;
		//String inputDate = "2019-08-29";
		int year = Integer.parseInt(inputDate.substring(0,4));
		int month = Integer.parseInt(inputDate.substring(5,7))-1;
		int day = Integer.parseInt(inputDate.substring(8,10));
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar checkToday = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		checkToday.set(Calendar.HOUR_OF_DAY, 0);
		checkToday.set(Calendar.MINUTE, 0);
		checkToday.set(Calendar.SECOND, 0);
		checkToday.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		checkToday.set(year, month, day);

		if (checkToday.before(today)) {
			checkD = false;
		}
		return checkD;
	}
	//대여일자 검사
	public static boolean checkSRdate(String start, String end) {
	    boolean checkD = true;
	    
	    int year = Integer.parseInt(start.substring(0, 4));
	    int month = Integer.parseInt(start.substring(5, 7)) - 1;
	    int day = Integer.parseInt(start.substring(8, 10));
	    
	    int year2 = Integer.parseInt(end.substring(0, 4));
	    int month2 = Integer.parseInt(end.substring(5, 7)) - 1;
	    int day2 = Integer.parseInt(end.substring(8, 10));
	    
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar checkStart = Calendar.getInstance();
	    Calendar checkEnd = Calendar.getInstance();
	    
	    checkStart.set(11, 0);
	    checkStart.set(12, 0);
	    checkStart.set(13, 0);
	    checkStart.set(14, 0);
	    checkEnd.set(11, 0);
	    checkEnd.set(12, 0);
	    checkEnd.set(13, 0);
	    checkEnd.set(14, 0);
	    
	    checkStart.set(year, month, day);
	    checkEnd.set(year2, month2, day2);
	    
	    if (checkEnd.before(checkStart)) {
	      checkD = false;
	    }
	    return checkD;
	  }
	// 부족한 자리수 만큼 채우기 왼쪽채우기
    public static String getLPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// 부족한 자리수 만큼 채우기 양쪽채우기
    public static String getCPad(String str, int size, String strFillText) {
        int intPadPos = 0;
        for(int i = (str.getBytes()).length; i < size; i++) {
            if(intPadPos == 0) {
                str += strFillText;
                intPadPos = 1;
            } else {
                str = strFillText + str;
                intPadPos = 0;
            }
        }
        return str;
    }
	// 부족한 자리수 만큼 채우기 오른쪽채우기
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }
}
