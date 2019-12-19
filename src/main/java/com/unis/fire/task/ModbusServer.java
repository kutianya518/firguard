package com.unis.fire.task;

import com.unis.fire.utils.ModbusUtils;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ModbusServer {
    public static void main(String[] args) throws Exception {
        // 监听指定的端口
        int port = 6879;
        ServerSocket server = new ServerSocket(port);

        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");
        Socket socket = server.accept();
        System.err.println("创建socket");
        // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
        InputStream inputStream = socket.getInputStream();
        System.out.println("获得socket流");
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            int byteslen = inputStream.available();
            if (byteslen != 0) {
                byte[] bytes = new byte[byteslen];
                int len = inputStream.read(bytes);
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                StringBuilder sb = new StringBuilder();
                if (len > -1) {
                    sb.append(ModbusUtils.byteArrayToHexString(bytes));
                }
                System.err.println("receive:" + sb.toString());

                if (sb.toString().split(" ")[2].equals("84")) {
                    byte[] bytes1 = ModbusUtils.hexStringToByteArray("7b 7b 84 bf 23 7d 7d");
                    outputStream.write(bytes1);
                    System.err.println("------------设备注册完毕-------------");
                }
                if (sb.toString().split(" ")[2].equals("89")) {
                    byte[] bytes1 = ModbusUtils.hexStringToByteArray("7b 7b 89 7e e6 7d 7d");
                    outputStream.write(bytes1);
                    System.err.println("--------设备报警设置完毕------------");
                }
                if (sb.toString().split(" ")[2].equals("93")) {
                    byte[] bytes1 = ModbusUtils.hexStringToByteArray(ModbusUtils.DateTimeToHexString());
                    outputStream.write(bytes1);
                    System.err.println("--------设备对时完毕------------");
                }
                if (sb.toString().split(" ")[2].equals("91")) {
                    byte[] bytes1 = ModbusUtils.hexStringToByteArray("7b 7b 91 7e ec 7d 7d");
                    outputStream.write(bytes1);
                    System.err.println("--------收到91数据------------");
                    ModbusUtils.Analysis91HexString(sb.toString());
                    System.err.println("-----------分项数据----------------");
                    //查询5个参数漏电、温度报警值
                    //byte[] alarm = ModbusUtils.hexStringToByteArray("7b 7b 90 01 03 10 33 00 05 71 06 E6 FD 7d 7d");
                    //outputStream.write(alarm);
                }
                if (sb.toString().split(" ")[2].equals("90")) {
                    System.err.println("-------------收到报警参数数据---------------");
                    //设定5个参数漏电、温度报警值
                    //byte[] alarm =ModbusUtils.hexStringToByteArray("7b 7b 90 01 10 10 33 00 05 0a 02 58 00 50 00 5a 00 64 00 78 51 3B DD 72 7d 7d");
                    //outputStream.write(alarm);
                    //设定91数据为5类
                    //byte[] five91 = ModbusUtils.hexStringToByteArray("7b 7b 31 01 05 10 00 2a 12 04 1a 13 00 02 12 1e 13 19 95 0a 10 01 10 02 10 03 12 0d 12 1a 39 c7 7d 7d");
                    //outputStream.write(five91);
                }
            }


        }
        //inputStream.close();
        //socket.close();
        //server.close();
    }
}
