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
import java.util.Map;

public class TestLogin_api {
    @Test(dataProvider = "data")
    public void test(API apiData, Case caseData){
        String url = apiData.getUrl();
        String param = caseData.getParam();
        String contentType = apiData.getContentType();
        String type = apiData.getType();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put(Contants.HEADER_MEDIA_TYPE_NAME,Contants.HEADER_MEDIA_TYPE_VALUE_V2);
        Map<String, String> resutl = HttpUtils.call(url, param, contentType, type,headers);
        //实际响应结果
        String actual = resutl.get("body");
        //预期响应结果
        String expected = caseData.getResponse_expected();
        System.out.println(url);
        System.out.println(actual);
        //断言
        boolean flag = AssertUtils.asserts(actual, expected);
        String asserts = (flag == true)?"pass":"fail";
        System.out.println("断言结果："+asserts);
        //响应数据数据回写
        int caseId = Integer.parseInt(caseData.getCaseId());
        WriteBackData writeBackData = new WriteBackData(caseId, Contants.WRITE_BACK_ACTUAL_CELLNUM_RESPONSE,actual,Contants.WRITE_BACK_ACTUAL_CELLNUM_RESULT,asserts);
        ExcelUtils.wbd.add(writeBackData);
        //获取token
        AuthorizationUtils.storeResult(actual);
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
        Object[][] data = ExcelUtils.readforApiId("2");
        return data;
    }
}
