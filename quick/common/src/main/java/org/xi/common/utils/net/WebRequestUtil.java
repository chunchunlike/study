package org.xi.common.utils.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.xi.common.utils.StringUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebRequestUtil {
    public enum RequestType {
        GET, POST
    }

    //region 使用java默认

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param type       请求类型GET/POST
     * @param parameters 请求参数集合
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse(String url, RequestType type, Map<String, String> parameters, String postBody) throws IOException {
        return getResponse(url, type, parameters, null, postBody, null);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse(String url, Map<String, String> parameters, Map<String, String> headers) throws IOException {
        return getResponse(url, RequestType.GET, parameters, headers, null, null);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param type       请求类型GET/POST
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param postBody   POST提交的数据
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse(String url, RequestType type, Map<String, String> parameters, Map<String, String> headers, String postBody) throws IOException {
        return getResponse(url, type, parameters, headers, postBody, null);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param type       请求类型GET/POST
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param postBody   POST提交的数据
     * @param encoding   编码方式
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse(String url, RequestType type, Map<String, String> parameters, Map<String, String> headers, String postBody, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(url)) return "";
        if (StringUtil.isNullOrWhiteSpace(encoding)) encoding = "utf-8";
        url = getUrl(url, parameters);

        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        setHeaders(conn, headers);

        if (type == RequestType.POST) {
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            if (!StringUtil.isNullOrWhiteSpace(postBody)) {
                byte[] postBodyBytes = postBody.getBytes(encoding);

                try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                    out.write(postBodyBytes, 0, postBodyBytes.length);
                }
            }
        } else {
            conn.setRequestMethod("GET");
        }
        String html = getHtml(conn, encoding);
        conn.disconnect();

        return html;
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param encoding   编码方式
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String get(String url, Map<String, String> parameters, Map<String, String> headers, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(url)) return "";
        if (StringUtil.isNullOrWhiteSpace(encoding)) encoding = "utf-8";
        url = getUrl(url, parameters);

        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        conn.setRequestMethod("GET");
        setHeaders(conn, headers);

        String html = getHtml(conn, encoding);
        conn.disconnect();

        return html;
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param postBody   POST提交的数据
     * @param encoding   编码方式
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String post(String url, Map<String, String> parameters, Map<String, String> headers, String postBody, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(url)) return "";
        if (StringUtil.isNullOrWhiteSpace(encoding)) encoding = "utf-8";
        url = getUrl(url, parameters);

        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        setHeaders(conn, headers);

        if (!StringUtil.isNullOrWhiteSpace(postBody)) {
            byte[] postBodyBytes = postBody.getBytes(encoding);

            try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                out.write(postBodyBytes, 0, postBodyBytes.length);
            }
        }

        String html = getHtml(conn, encoding);
        conn.disconnect();

        return html;
    }

    /**
     * 设置Header
     *
     * @param conn
     * @param headers
     */
    private static void setHeaders(HttpURLConnection conn, Map<String, String> headers) {

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 获取响应字符串
     *
     * @param conn
     * @param encoding
     * @return
     * @throws IOException
     */
    private static String getHtml(HttpURLConnection conn, String encoding) throws IOException {

        String html = null;
        try (InputStream inputStream = conn.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream, encoding);
             BufferedReader bufferedReader = new BufferedReader(reader);) {

            String line = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(new String(line.getBytes(), encoding));
            }
            html = stringBuffer.toString();
        }
        return html;
    }

    //endregion

    //region 使用apache httpclient

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param type       请求类型GET/POST
     * @param parameters 请求参数集合
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse2(String url, RequestType type, Map<String, String> parameters, String postBody) throws IOException {
        return getResponse(url, type, parameters, null, postBody, null);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse2(String url, Map<String, String> parameters, Map<String, String> headers) throws IOException {
        return getResponse(url, RequestType.GET, parameters, headers, null, null);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param type       请求类型GET/POST
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param postBody   POST提交的数据
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse2(String url, RequestType type, Map<String, String> parameters, Map<String, String> headers, String postBody) throws IOException {
        return getResponse(url, type, parameters, headers, postBody, null);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param type       请求类型GET/POST
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param postBody   POST提交的数据
     * @param encoding   编码方式
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getResponse2(String url, RequestType type, Map<String, String> parameters, Map<String, String> headers, String postBody, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(url)) return "";
        url = getUrl(url, parameters);

        HttpUriRequest request;

        if (type == RequestType.POST) {
            HttpPost httpPost = new HttpPost(url);
            if (!StringUtil.isNullOrWhiteSpace(postBody)) {
                httpPost.setEntity(new StringEntity(postBody, encoding));
            }
            request = httpPost;
        } else {
            HttpGet httpGet = new HttpGet(url);
            request = httpGet;
        }
        setHeaders(request, headers);
        return getHtml(request, encoding);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param encoding   编码方式
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String get2(String url, Map<String, String> parameters, Map<String, String> headers, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(url)) return "";
        url = getUrl(url, parameters);

        HttpGet httpGet = new HttpGet(url);
        setHeaders(httpGet, headers);

        return getHtml(httpGet, encoding);
    }

    /**
     * 返回web请求的数据
     *
     * @param url        请求地址
     * @param parameters 请求参数集合
     * @param headers    请求头集合
     * @param postBody   POST提交的数据
     * @param encoding   编码方式
     * @return 返回的数据
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String post2(String url, Map<String, String> parameters, Map<String, String> headers, String postBody, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(url)) return "";
        url = getUrl(url, parameters);

        HttpPost httpPost = new HttpPost(url);
        setHeaders(httpPost, headers);

        if (!StringUtil.isNullOrWhiteSpace(postBody)) {
            httpPost.setEntity(new StringEntity(postBody, encoding));
        }

        return getHtml(httpPost, encoding);
    }

    /**
     * 设置Header
     *
     * @param request
     * @param headers
     */
    private static void setHeaders(HttpUriRequest request, Map<String, String> headers) {

        if (null != headers && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 获取响应字符串
     *
     * @param request
     * @param encoding
     * @return
     * @throws IOException
     */
    private static String getHtml(HttpUriRequest request, String encoding) throws IOException {

        if (StringUtil.isNullOrWhiteSpace(encoding)) encoding = "utf-8";

        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(request);
        String html = EntityUtils.toString(response.getEntity(), encoding);
        return html;
    }

    //endregion

    /**
     * 获取路径
     *
     * @param url        文件夹路径
     * @param parameters 路径参数
     * @return
     */
    private static String getUrl(String url, Map<String, String> parameters) {

        if (url == null || url.isEmpty()) return "";

        if (parameters != null && parameters.size() > 0) {
            List<String> parameterList = new ArrayList<>();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                parameterList.add(entry.getKey() + "=" + entry.getValue());
            }
            url += (url.contains("?") ? "&" : "?") + String.join("&", parameterList);
        }
        return url;
    }

}
