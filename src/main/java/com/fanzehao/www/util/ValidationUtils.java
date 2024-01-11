package com.fanzehao.www.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    // 验证手机号码格式
    public static boolean isValidPhoneNumber(String phoneNumber)
    {
        return isNumeric(phoneNumber) && (phoneNumber.length() == 11);
    }

    // 验证邮箱地址格式
    public static boolean isValidEmailAddress(String email)
    {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return matchesRegex(email, regex);
    }
    private static boolean matchesRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
//    public static boolean isValidChineseString(String input) {
//        // 正则表达式，匹配只包含中文字符的字符串
//        String regex = "^[\u4e00-\u9fa5]{1,11}$";
//        return input.matches(regex);
//    }
    public static boolean isNumeric(String str) {
        // 使用正则表达式检查字符串是否只包含数字
        return str.matches("\\d+");
    }
}