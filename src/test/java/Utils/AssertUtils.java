package Utils;

import POJO.Case;
import POJO.ResponseExpected;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.List;
import java.util.Map;

public class AssertUtils {

    public static boolean asserts(String actual, String expected){
        boolean flag = false;
        List<ResponseExpected> responseExpecteds = JSONObject.parseArray(expected, ResponseExpected.class);
        for (ResponseExpected responseExpected:responseExpecteds) {
            String expression = responseExpected.getExpression();
            //响应预期结果
            String expected_result = responseExpected.getValue();
            //响应实际结果
            String actual_result = JSONPath.read(actual, expression).toString();
            System.out.println("实际结果："+actual_result+",预期结果："+expected_result);
            if (expected_result.equals(actual_result)){
                flag = true;
            }else {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
