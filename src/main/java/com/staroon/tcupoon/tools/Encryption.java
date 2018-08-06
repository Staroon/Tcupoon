package com.staroon.tcupoon.tools;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018-08-06
 * Time: 15:43:59
 * To change this template use File | Settings | File Templates.
 */
public class Encryption {

    public static String enStr(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        return base64Encode(str2Hex(reverse(str)));
    }

    public static String deStr(String enStr) {
        return reverse(hex2Str(base64Decode(enStr)));
    }

    /**
     * 加密第三步: base64加密
     *
     * @param origStr 输入字符串
     * @return 加密字符串
     */
    private static String base64Encode(String origStr) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(origStr.getBytes());
    }

    /**
     * 用于base64解密
     *
     * @param origStr base64加密字符串
     * @return 字符串
     */
    private static String base64Decode(String origStr) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return new String(decoder.decodeBuffer(origStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用于十六进制字符串解密
     *
     * @param hexStr 输入字符串
     * @return 字符串
     */
    private static String hex2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 加密第二步: 转为十六进制字符串
     *
     * @param reverseStr 输入字符串
     * @return 十六进制字符串
     */
    private static String str2Hex(String reverseStr) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = reverseStr.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 加密第一步: 字符串反转
     *
     * @param str 输入字符串
     * @return 反转字符串
     */
    private static String reverse(String str) {
        StringBuilder strbuf = new StringBuilder(str);
        return strbuf.reverse().toString();
    }

    public static void main(String[] args) {
        String enStr = enStr("12地球D+++-saff");
        System.out.println("加密: " + enStr);
        System.out.println();
        System.out.println("解密: " + deStr(enStr));
    }
}
