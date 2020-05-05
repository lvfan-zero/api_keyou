package Case;

import POJO.API;
import POJO.Case;
import POJO.WriteBackData;
import Utils.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRegister_api {

    @Test(dataProvider = "data")
    public void test(API apiData, Case caseData){
        String url = apiData.getUrl();
        String param = caseData.getParam();
        String contentType = apiData.getContentType();
        String type = apiData.getType();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put(Contants.HEADER_MEDIA_TYPE_NAME,Contants.HEADER_MEDIA_TYPE_VALUE_V2);

        //预期响应结果
        String expected = caseData.getResponse_expected();
        //数据库前置结果
        String sql = caseData.getSql();
        List<Map<String, Object>> sql_before = JDBCUtils.jdbcConn(sql);
        //http请求
        Map<String, String> resutl = HttpUtils.call(url, param, contentType, type,headers);

        //实际响应结果
        String actual = resutl.get("body");

        System.out.println(url);
        System.out.println(actual);
        //响应断言
        boolean response_assert = AssertUtils.asserts(actual, expected);
        //数据库后置校验结果
        List<Map<String, Object>> sql_after = JDBCUtils.jdbcConn(sql);
        Object beforeSqlResult = null;
        Object afterSqlResult = null;
        if (sql_after!=null){
            beforeSqlResult =  sql_before.get(0).get("totalNum");
            afterSqlResult =  sql_after.get(0).get("totalNum");
        }
        boolean sql_assert = (sql == null)?true:(beforeSqlResult.equals(afterSqlResult));

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
        Object[][] data = ExcelUtils.readforApiId("1");
        return data;
    }
}
