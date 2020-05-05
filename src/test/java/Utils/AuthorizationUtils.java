package Utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationUtils {
    public static Map<String,String> evn = new HashMap<String, String>();

//    public static void storeResult(String body){
//        String token = null;
//        if (body != null){
//            Object read = JSONPath.read(body, "$.token");
//            token = "JWT "+read;
//        }
//        evn.put("token",token);
//    }

    public static void storeResult(String body){
        String token = null;
        if (body != null){
            Object read = JSONPath.read(body, "$.token");
            token = "Bearer "+read;
        }
        evn.put("token",token);
    }

    public static void getCookies(){

    }
}
