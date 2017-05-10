package com.kk.tool.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <font size="3" color="green"><b>.</b></font><p>
 * <p>
 * <font size="2" color="green"><b>返回：.</b></font><p>
 * <p>
 * <font size="1">Created on 2016-07-27.</font><p>
 * <p>
 * <font size="1">@author LuoShuiquan.</font>
 */
public class InfoCheckUtils {
    /**
     * 检测手机号是否合法
     */
    public static boolean checkPhoneNum(String phoneNum) {
        String pattern = "^(((13[0-9])|(14[5,7])|(15([0-3]|[5-9]))|(17[0-9])|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
        return phoneNum.matches(pattern) || TextUtils.isEmpty(phoneNum);
    }

    /**
     * 检测固话号是否合法
     */
    public static boolean checkLandLine(String phoneNo) {
        String pattern = "^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?";
        return phoneNo.matches(pattern) || TextUtils.isEmpty(phoneNo);
    }

    /**
     * 检测邮箱是否合法
     */
    public static boolean checkEmail(String emailName) {
        String pattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return emailName.matches(pattern) || TextUtils.isEmpty(emailName);
    }

    /**
     * 检测密码是否6-16数字或者字母组合
     *
     * @param psd
     * @return
     */
    public static boolean checkPsd(String psd) {
        Pattern pat = Pattern.compile("[0-9a-zA-Z]{6,16}");
        Matcher mat = pat.matcher(psd);
        return mat.matches();
    }

    /**
     * 检测身份证号
     *
     * @param s
     * @return
     */
//    public static boolean checkIdCard(String s) {
//        String pattern = "(^\\d{15}$)|(^\\d{17}([0-9xX])$)";
//        return s.matches(pattern);
//    }
    public static boolean checkIdCard(String s) {
        String pattern15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String pattern18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9xX])$";
        return s.matches(pattern18) || s.matches(pattern15);
    }


    /**
     * 检测昵称是否符合规则
     *
     * @param nikeName
     * @return
     */

    public static boolean checkNickName(String nikeName) {
        char[] cTemp = nikeName.toCharArray();
        for (int i = 0; i < nikeName.length(); i++) {
            if (!isReg(cTemp[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判定输入汉字,英文，数字，下划线
     *
     * @param c
     * @return
     */

    public static boolean isReg(char c) {
        return String.valueOf(c).matches("[a-zA-Z0-9\u4E00-\u9FA5_]");
    }


    /**
     * 是否是url
     * @param s
     * @return
     */
    public static boolean isUrl(String s) {
        String p = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
        return s.matches(p);
    }
}
