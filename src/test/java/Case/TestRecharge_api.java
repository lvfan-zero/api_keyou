package Case;

import POJO.API;
import POJO.Case;
import POJO.WriteBackData;
import Utils.*;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestRecharge_api {
    @Test(dataProvider = "data")
    public void test(API apiData, Case caseData){
        String url = apiData.getUrl();
        String param = caseData.getParam();
        String contentType = apiData.getContentType();
        String type = apiData.getType();
        //添加鉴权数据
        Map<String,String> headers = new HashMap<String, String>();
        headers.put(Contants.HEADER_MEDIA_TYPE_NAME,Contants.HEADER_MEDIA_TYPE_VALUE_V2);
        headers.put("Authorization",AuthorizationUtils.evn.get("token"));
        //数据库前置响应结果
        String sql = caseData.getSql();
        List<Map<String, Object>> sql_before = JDBCUtils.jdbcConn(sql);

        Map<String, String> resutl = HttpUtils.call(url, param, contentType, type,headers);
        //实际响应结果
        String actual = resutl.get("body");
        //预期响应结果
        String expected = caseData.getResponse_expected();
        System.out.println(url);
        System.out.println(actual);
        //响应断言
        boolean response_assert = AssertUtils.asserts(actual, expected);
        //数据库后置校验结果
        List<Map<String, Object>> sql_after = JDBCUtils.jdbcConn(sql);
        BigDecimal beforeSqlResult = null;
        BigDecimal afterSqlResult = null;
        if (sql_after!=null){
            beforeSqlResult =  (BigDecimal)sql_before.get(0).get("totalNum");
            afterSqlResult =  (BigDecimal)sql_after.get(0).get("totalNum");
        }
        beforeSqlResult = (BigDecimal)sql_before.get(0).get("leave_amount");
        afterSqlResult = (BigDecimal)sql_after.get(0).get("leave_amount");
        Map<String,Object> map = JSONObject.parseObject(param, Map.class);
        BigDecimal leave_amount = (BigDecimal)map.get("leave_amount");
        BigDecimal subtract = afterSqlResult.subtract(beforeSqlResult);
        boolean sql_assert = (sql == null)?true:(leave_amount==subtract);
        boolean flag = (response_assert == true && sql_assert == true)?true:false;
        String asserts = (flag == true)?"pass":"fail";
        System.out.println("断言结果："+asserts);
        //响应数据数据回写
        int caseId = Integer.parseInt(caseData.getCaseId());
        WriteBackData writeBackData = new WriteBackData(caseId, Contants.WRITE_BACK_ACTUAL_CELLNUM_RESPONSE,actual,Contants.WRITE_BACK_ACTUAL_CELLNUM_RESULT,asserts);
        ExcelUtils.wbd.add(writeBackData);

        if("fail".equals(asserts)){
            Assert.fail();
        }
    }

    @AfterSuite
    public void finash(){
        ExcelUtils.writeBack();
    }

    @DataProvider(name = "data")
    public Object[][] data(){
        Object[][] data = ExcelUtils.readforApiId("3");
        return data;
    }
}
