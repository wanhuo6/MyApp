package com.kk.tool.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <font size="3" color="green"><b>.</b></font><p>
 * <p/>
 * <font size="2" color="green"><b>返回：.</b></font><p>
 * <p/>
 * <font size="1">Created on 2016-08-01.</font><p>
 * <p/>
 * <font size="1">@author LuoShuiquan.</font>
 */
public class StringUtils {

    private static final String CRLF_STR = "(\r\n|\r|\n|\n\r)";
    public static final int STRING_TYPE_START = 0;
    public static final int STRING_TYPE_MIDDLE = 1;
    public static final int STRING_TYPE_END = 2;

    /**
     * inputStream转String
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream in) {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * 局部文字变大变色
     *
     * @param textView
     * @param rm
     * @param color
     * @param size
     * @param style    0，无：1粗斜体
     */
    public static void formatString(TextView textView, String rm, String color, float size, int style) {
        Object str = parseString(rm, color, size, style);
        if (str instanceof String) {
            textView.setText((String) str);
        } else if (str instanceof SpannableString) {
            textView.setText((SpannableString) str);
        }
    }

    public static Object parseString(String s, String color, float size, int style) {
        if (!TextUtils.isEmpty(s)) {
            if (s.contains("%")) {
                int start = s.indexOf("%");
                int end = s.lastIndexOf("%");
                String tmp = s.replaceAll("%", "");
                return StringUtils.changeStyle(tmp, start, end - 1, color, size, style);
            } else {
                return s;
            }
        }
        return null;
    }

    public static SpannableString changeStyle(String s, int start, int end, String color, float size, int style) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        SpannableString msp = new SpannableString(s);
//设置字体大小（绝对值,单位：像素）
//        msp.setSpan(new AbsoluteSizeSpan(30), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        msp.setSpan(new RelativeSizeSpan(size), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍
        //设置字体前景色
        msp.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        设置字体背景色
//        msp.setSpan(new BackgroundColorSpan(Color.CYAN), 2, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置背景色为青色

        if (style == 1) {
            msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗斜体
        }

        return msp;
    }

    /**
     * 改变部分颜色two
     * @param text
     * @param sign
     * @return
     */
    public static Spanned changeColor(String text,String color,String sign){

        if (!text.startsWith(sign)&&!text.endsWith(sign)&&text.contains(sign)) {

            String[] textArr=text.split(sign);
            String newText="";

            if (textArr.length%2==0) {
                return Html.fromHtml(text);

            }

            for (int i = 0; i < textArr.length; i++) {
              /*  if (textArr[i].contains("%")){
                    String[] line= textArr[i].split("%");
                    textArr[i]="<font color=" + color + ">" + line[0] + "</font><br/>"+line[1];
                }*/
                 if(i%2!=0) {
                    textArr[i]="<font color=" + color + ">" + textArr[i] + "</font>";
                }
                newText=newText+textArr[i];
            }
            Log.e("====",newText);
            return Html.fromHtml(newText);

        }
        return Html.fromHtml(text);

    }

    /**
     * 改变部分字体颜色
     *
     * @param str
     * @param color
     * @return
     */
    public static Spanned ChangeColor(String str, String color, int type) {
        Spanned temp = null;
        if (str.contains("%")) {
            String[] a = str.split("%");
            switch (type) {
                case STRING_TYPE_START: {
                    String source = "<font color=" + color + ">" + a[0] + "</font>" + a[1];
                    temp = Html.fromHtml(source);
                    break;
                }
                case STRING_TYPE_MIDDLE: {
                    String source = a[0] + "<font color=" + color + ">" + a[1] + "</font>" + a[2];
                    temp = Html.fromHtml(source);
                    break;
                }
                case STRING_TYPE_END: {
                    String source = a[0] + "<font color=" + color + ">" + a[1] + "</font>";
                    temp = Html.fromHtml(source);
                    break;
                }
                default:
                    temp = Html.fromHtml(str);
                    break;
            }
        } else {
            temp = Html.fromHtml(str);
        }
        return temp;
    }

    /**
     * 筛选出所有数字
     *
     * @param str
     * @return
     */
    public static String filterUnNumber(String str) {
        return str.replaceAll("[^0-9]", "");
    }

    /**
     * 是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isContainsChineseChar(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile(".*[\u4e00-\u9fa5]+.*");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

    /**
     * 改变部分文字的颜色和大小
     *
     * @param str
     * @param color
     * @return
     */
    public static Spanned ChangeColorAndBigSize(String str, String color, int type) {
        Spanned temp = null;
        if (str.contains("%")) {
            String[] a = str.split("%");
            switch (type) {
                case STRING_TYPE_START: {
                    String source = "<big><big><font color=" + color + ">" + a[0] + "</font></big></big>" + a[1];
                    temp = Html.fromHtml(source);
                    break;
                }
                case STRING_TYPE_MIDDLE: {
                    String source = a[0] + "<big><big><font color=" + color + ">" + a[1] + "</font></big></big>" + a[2];
                    temp = Html.fromHtml(source);
                    break;
                }
                case STRING_TYPE_END: {
                    String source = a[0] + "<big><big><font color=" + color + ">" + a[1] + "</font></big></big>";
                    temp = Html.fromHtml(source);
                    break;
                }
                default:
                    temp = Html.fromHtml(str);
                    break;
            }
        } else {
            temp = Html.fromHtml(str);
        }
        return temp;
    }


    //格式化颜色
    public static SpannableStringBuilder formatColor(Context context, int stringId, String str, int colorId, int startIndex) {
        String s = String.format(context.getResources().getString(stringId), str);
        SpannableStringBuilder style = new SpannableStringBuilder(s);
        formatColor(context, colorId, startIndex, s.length(), style);
        return style;
    }

    public static SpannableStringBuilder formatColor(Context context, String str, int colorId, int start_index, int end_index) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        formatColor(context, colorId, start_index, end_index, style);
        return style;
    }

    public static void formatColor(Context context, int colorId, int start_index, int end_index, SpannableStringBuilder style) {
        style.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorId)), start_index, end_index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    /**
     * 计算字符串换行数
     * @param s
     * @return
     */
    public static int countCtrl(String s) {
        int c = 0;
        Pattern pattern = Pattern.compile(CRLF_STR);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            c++;
        }
        return c;
    }

    /**
     * 保留一位小数
     *
     * @param f
     * @return
     */
    public static String DFOnePont(double f) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(f);
    }

    /**
     * 保留一位小数得到double值
     * @param a
     * @return
     */
    public static double getDoubleOne(double a){
        DecimalFormat df=new DecimalFormat("0.0");
        return new Double(df.format(a).toString());
    }

    //小数点后面的数字变小
    public static  Spanned changePointSize(String number,String after){
         if (number==null){
             return null;
         }
        if (number.contains(".")){
            String a[]=number.split("\\.");
            if (a.length>1){
                String source = "<big><big>" + a[0] + "</big></big>" +"."+ a[1]+after;
                return Html.fromHtml(source);
            }

        }
        return Html.fromHtml(number);
    }



}
