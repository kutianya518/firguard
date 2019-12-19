package com.unis.fire.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static String doGetJsoup(String url) {
        String result=null;
        Connection conn=Jsoup.connect(url).timeout(5000);
        try {
            Connection.Response response=conn.execute();
            if (response.statusCode()==200){
               result= response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    /*JDK自带httpUrlConnection*/

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param httpurl 请求参数用?拼接在url后边，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return result 所代表远程资源的响应结果
     */
    public static ArrayList<String> doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;

        ArrayList<String> resultList = new ArrayList<>();
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：5000毫秒
            connection.setConnectTimeout(5000);
            // 设置读取远程返回的数据时间：10000毫秒
            connection.setReadTimeout(10000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            getConnectionResult(connection, is, br, resultList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnectiong(connection, is, br, null);
        }
        return resultList;
    }

    /**
     * url请求获取数据
     *
     * @param connection 连接
     * @param is         读取流
     * @param br         buffer
     * @param resultList 结果集
     * @return result
     * @throws IOException
     */
    public static ArrayList<String> getConnectionResult(HttpURLConnection connection, InputStream is, BufferedReader br, ArrayList<String> resultList) throws IOException {
        if (connection.getResponseCode() == 200) {
            is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                resultList.add(temp);
            }
        }
        return resultList;
    }

    /**
     * 关闭资源
     *
     * @param connection url连接
     * @param is         读取流
     * @param br         buffer流
     */
    public static void closeConnectiong(HttpURLConnection connection, InputStream is, BufferedReader br, OutputStream os) {
        if (null != br) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != os) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        connection.disconnect();// 关闭远程连接
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param httpUrl 发送请求的 URL
     * @param param   请求参数应该是{"key":"==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d"}的形式。
     * @return 所代表远程资源的响应结果
     */
    public static ArrayList<String> doPost(String httpUrl, String param) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        ArrayList<String> resultList = new ArrayList<>();
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/json");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            //connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            getConnectionResult(connection, is, br, resultList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnectiong(connection, is, br, os);
        }
        return resultList;
    }

    /**
     * @param httpUrl 请求的url
     * @param param   form表单的参数（key,value形式）
     * @return
     */
    public static ArrayList<String> doPostForm(String httpUrl, Map param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        ArrayList<String> resultList = new ArrayList<>();
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            //connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的(form表单形式的参数实质也是key,value值的拼接，类似于get请求参数的拼接)
            os.write(createLinkString(param).getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            getConnectionResult(connection, is, br, resultList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnectiong(connection, is, br, os);
        }
        return resultList;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }

        return prestr.toString();
    }


    public static void main(String[] args) {
        String url = "http://192.168.1.155/status";
        //ArrayList getResult = HttpUtil.doGet(url);
        //System.out.println(getResult);
        StringBuffer sb = new StringBuffer();
        sb.append("a").append(System.getProperty("line.separator")).append("b").append(System.getProperty("line.separator")).append("c").append(System.getProperty("line.separator"))
                .append("d").append(System.getProperty("line.separator"));
        String str = sb.toString();
        String[] strarray = str.split(System.getProperty("line.separator"));
        System.err.println(sb.toString());


//        url = "http://localhost:8082/api/core/login";
//        JSONObject json = new JSONObject();
//        json.put("key", "==g43sEvsUcbcunFv3mHkIzlHO4iiUIT R7WwXuSVKTK0yugJnZSlr6qNbxsL8OqCUAFyCDCoRKQ882m6cTTi0q9uCJsq JJvxS+8mZVRP/7lWfEVt8/N9mKplUA68SWJEPSXyz4MDeFam766KEyvqZ99d");
//        String postResult = HttpUtil.doPost(url, json.toJSONString());
//        System.out.println(postResult);
//
//        url = "http://localhost:8082/api/test/testSendForm";
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "测试表单请求");
//        String formResult = HttpUtil.doPostForm(url, map);
//        System.out.println(formResult);

    }

}