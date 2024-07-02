package com.ahq.globals;

import com.ahq.globals.utilities.*;

public class Utils {

    public static String nric_singapore_generate(String type, String age) { return UtilNricSingapore.generate(type,Integer.parseInt(age)); }
//    public static String nric_singapore_validate(String nric) { return UtilNricSingapore.validate(nric); } // TO BE DEVELOPED
    public static String password_encrypt(String encryptedPassword) { return UtilPassword.encrypt(encryptedPassword);}
    public static String password_decrypt(String password) { return UtilPassword.decrypt(password);}
    public static String password_check(String password) { return UtilPassword.check(password);}
    public static String base64_decrypt(String base64String) { return UtilBase64.decrypt(base64String);}

    //Date & Time Utils
    public static String dateTime_currentDate() { return UtilDateTime.currentDate();}
    public static String dateTime_currentDatePlusAdditionalDays(int numberOfAdditionalDays) { return UtilDateTime.currentDatePlusAdditionalDays(numberOfAdditionalDays);}
    public static String dateTime_currentTimeWithFormatPlusAdditionalDays(String timeFormat, int numberOfAdditionalDays) { return UtilDateTime.currentTimeWithFormatPlusAdditionalDays(timeFormat,numberOfAdditionalDays);}

    public static String dateTime_currentDateTime() { return UtilDateTime.currentDateTime();}
    public static String dateTime_currentDateWithFormat(String dateFormat) { return UtilDateTime.currentDateWithFormat(dateFormat);}
    public static String dateTime_currentTimeWithFormat(String timeFormat) { return UtilDateTime.currentTimeWithFormat(timeFormat);}

}
