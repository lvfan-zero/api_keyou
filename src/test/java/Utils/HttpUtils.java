package Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    public static Map<String, String> call(String url, String params, String contentType, String type,boolean flag){
        try {
            if ("json".equals(type)){
                if("post".equals(contentType)){
                    return HttpUtils.jsonPost(url,params,flag);
                }else if ("patch".equals(contentType)){
                    return HttpUtils.jsonPatch(url,params,flag);
                }else if ("put".equals(contentType)){
                    return HttpUtils.jsonPut(url,params,flag);
                }
            }else if (type.equals("form")){
                if ("get".equals(contentType)){
                    return HttpUtils.formget(url,params,flag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> call(String url, String params, String contentType, String type,Map<String,String> headers){
        try {
            if ("json".equals(type)){
                if("post".equals(contentType)){
                    return HttpUtils.jsonPost(url,params,headers);
                }else if ("patch".equals(contentType)){
                    return HttpUtils.jsonPatch(url,params,headers);
                }else if ("put".equals(contentType)){
                    return HttpUtils.jsonPut(url,params,headers);
                }
            }else if (type.equals("form")){
                if ("get".equals(contentType)){
                    return HttpUtils.formget(url,params);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Map<String, String> jsonPost(String url, String param,Map<String,String> headers) throws IOException {
        //1、声明post请求对象
        HttpPost post = new HttpPost(url);
        //2、设置请求头
        //2.1、设置请求参数格式为json格式
        post.setHeader(Contants.HEADER_CONTENT_TYPE_NAME,Contants.HEADER_CONTENT_TYPE_VALUE_JSON);
        Set<String> keySet = headers.keySet();
        for (String key:keySet) {
            post.setHeader(key,headers.get(key));
        }
        //3、设置请求参数
        post.setEntity(new StringEntity(param, Charsets.UTF_8));
        //声明HttpClient对象
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        Map<String, String> result = responseFormat(response);
        return result;
    }


    private static Map<String, String> jsonPatch(String url, String param,Map<String,String> headers) throws IOException {
        HttpPatch patch = new HttpPatch(url);
        patch.setHeader(Contants.HEADER_CONTENT_TYPE_NAME,Contants.HEADER_CONTENT_TYPE_VALUE_JSON);
        Set<String> keySet = headers.keySet();
        for (String key:keySet) {
            patch.setHeader(key,headers.get(key));
        }
        patch.setEntity(new StringEntity(param,Charsets.UTF_8));
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(patch);
        Map<String, String> result = responseFormat(response);
        return result;
    }

    private static Map<String, String> jsonPut(String url, String param,Map<String,String> headers) throws IOException {
        HttpPut put = new HttpPut(url);
        put.setHeader(Contants.HEADER_CONTENT_TYPE_NAME,Contants.HEADER_CONTENT_TYPE_VALUE_JSON);
        Set<String> keySet = headers.keySet();
        for (String key:keySet) {
            put.setHeader(key,headers.get(key));
        }
        put.setEntity(new StringEntity(param,Charsets.UTF_8));
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(put);
        Map<String, String> result = responseFormat(response);
        return result;
    }

    private static Map<String, String> formget(String url, String param) throws IOException {
        Map<String,String> map = JSONObject.parseObject(param,Map.class);
        String params = "";
        Set<String> keySet = map.keySet();
        for (String key:keySet) {
            if ("".equals(params)){
                params += key+"="+map.get(key);
            }else{
                params += "&" + key + "=" + map.get(key);
            }
        }
        url += "?" + params;

        HttpGet get = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        Map<String, String> result = responseFormat(response);
        return result;
    }

    private static Map<String, String> jsonPost(String url, String param,boolean flag) throws IOException {
        //1、声明post请求对象
        HttpPost post = new HttpPost(url);
        //2、设置请求头
        //2.1、设置请求参数格式为json格式
        post.setHeader(Contants.HEADER_CONTENT_TYPE_NAME,Contants.HEADER_CONTENT_TYPE_VALUE_JSON);
        //2.2、是否鉴权
        if (flag){
            String token = AuthorizationUtils.evn.get("token");
            post.setHeader("Authorization",token);
        }
        //3、设置请求参数
        post.setEntity(new StringEntity(param, Charsets.UTF_8));
        //声明HttpClient对象
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(post);
        Map<String, String> result = responseFormat(response);
        return result;
    }


    private static Map<String, String> jsonPatch(String url, String param,boolean flag) throws IOException {
        HttpPatch patch = new HttpPatch(url);
        patch.setHeader(Contants.HEADER_CONTENT_TYPE_NAME,Contants.HEADER_CONTENT_TYPE_VALUE_JSON);
        //2.2、是否鉴权
        if (flag){
            String token = AuthorizationUtils.evn.get("token");
            patch.setHeader("Authorization",token);
        }
        patch.setEntity(new StringEntity(param,Charsets.UTF_8));
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(patch);
        Map<String, String> result = responseFormat(response);
        return result;
    }

    private static Map<String, String> jsonPut(String url, String param,boolean flag) throws IOException {
        HttpPut put = new HttpPut(url);
        put.setHeader(Contants.HEADER_CONTENT_TYPE_NAME,Contants.HEADER_CONTENT_TYPE_VALUE_JSON);
        //2.2、是否鉴权
        if (flag){
            String token = AuthorizationUtils.evn.get("token");
            put.setHeader("Authorization",token);
        }
        put.setEntity(new StringEntity(param,Charsets.UTF_8));
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(put);
        Map<String, String> result = responseFormat(response);
        return result;
    }

    private static Map<String, String> formget(String url, String param,boolean flag) throws IOException {
        Map<String,String> map = JSONObject.parseObject(param,Map.class);
        String params = "";
        Set<String> keySet = map.keySet();
        for (String key:keySet) {
            if ("".equals(params)){
                params += key+"="+map.get(key);
            }else{
                params += "&" + key + "=" + map.get(key);
            }
        }
        url += "?" + params;

        HttpGet get = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);
        Map<String, String> result = responseFormat(response);
        return result;
    }

    private static Map<String,String> responseFormat(HttpResponse response) throws IOException {
        Map<String,String> responseMap = new HashMap<String, String>();
        String entity = EntityUtils.toString(response.getEntity());
        String body = JSONObject.parseObject(entity).toJSONString();
        responseMap.put("body",body);
        return responseMap;
    }
}
