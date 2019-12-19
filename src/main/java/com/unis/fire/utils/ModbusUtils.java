package com.unis.fire.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ModbusUtils {
    /**
     * 16进制字节数组转16进制表示的字符串
     *
     * @param bytes 需要转换的16进制byte数组
     * @return 转换后的Hex字符串
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex).append(" ");
        }
        return sb.toString().trim();
    }


    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 16进制字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * 生成对时指令，时间为当前系统时间。
     *
     * @return
     */
    public static String DateTimeToHexString() {
        StringBuffer sb = new StringBuffer();
        LocalDateTime localDateTime = LocalDateTime.now();
        String year = Integer.toHexString(localDateTime.getYear() % 2000);
        String month = Integer.toHexString(localDateTime.getMonth().getValue());
        String day = Integer.toHexString(localDateTime.getDayOfMonth());
        String week = Integer.toHexString(localDateTime.getDayOfWeek().getValue());
        String hour = Integer.toHexString(localDateTime.getHour());
        String minute = Integer.toHexString(localDateTime.getMinute());
        String second = Integer.toHexString(localDateTime.getSecond());
        sb.append("93 ");//ARCM对时标识
        sb.append(year.length() < 2 ? "0" + year : year).append(" ");
        sb.append(month.length() < 2 ? "0" + month : month).append(" ");
        sb.append(day.length() < 2 ? "0" + day : day).append(" ");
        sb.append(week.length() < 2 ? "0" + week : week).append(" ");
        sb.append(hour.length() < 2 ? "0" + hour : hour).append(" ");
        sb.append(minute.length() < 2 ? "0" + minute : minute).append(" ");
        sb.append(second.length() < 2 ? "0" + second : second).append(" ");
        String crc = byteArrayToHexString(CRC16M.get(hexStringToByteArray(sb.toString())));
        sb.append(crc).append(" ").append("7d 7d");
        sb.insert(0, "7b 7b ");
        return sb.toString();
    }

    public static String Analysis91HexString(String hex91String) {
        String ldwd=StringUtils.substring(hex91String,30,297);
        System.out.println("漏电温度类:"+ldwd);
        String dydl=StringUtils.substring(hex91String,330,501);
        System.out.println("电压电流类:"+dydl);
        String dn=StringUtils.substring(hex91String,534,561);
        System.out.println("电能类:"+dn);
        String gl=StringUtils.substring(hex91String,594,723);
        System.out.println("功率类:"+gl);
        String xbdy=StringUtils.substring(hex91String,756,831);
        System.out.println("谐波电压类:"+xbdy);
        return "";
    }


    public static void main(String[] args) {
        String byteTime = "93 12 04 10 01 0d 11 1a";
        String byteTime2 = "93120410010d111a";
        String aa = byteArrayToHexString(CRC16M.get(hexStringToByteArray(byteTime2)));
        System.err.println(aa);
        System.err.println(DateTimeToHexString());
    }


}

