package Case;

import POJO.API;
import POJO.Case;
import POJO.WriteBackData;
import Utils.AssertUtils;
import Utils.Contants;
import Utils.ExcelUtils;
import Utils.HttpUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

public class BaseCase_keyou {
    @Test(dataProvider = "data")
    public void test(API apiData, Case caseData){
        String url = apiData.getUrl();
        String param = caseData.getParam();
        String contentType = apiData.getContentType();
        String type = apiData.getType();
        Map<String, String> resutl = HttpUtils.call(url, param, contentType, type,true);
        //实际响应结果
        String actual = resutl.get("body");
        //预期响应结果
        String expected = caseData.getResponse_expected();
        System.out.println(url);
        System.out.println(actual);
        //断言
        boolean flag = AssertUtils.asserts(actual, expected);
        String asserts = flag == true?"pass":"fail";
        //响应数据数据回写
        int caseId = Integer.parseInt(caseData.getCaseId());
        WriteBackData writeBackData = new WriteBackData(caseId, Contants.WRITE_BACK_ACTUAL_CELLNUM_RESPONSE,actual,Contants.WRITE_BACK_ACTUAL_CELLNUM_RESULT,asserts);
        ExcelUtils.wbd.add(writeBackData);
    }

    @AfterSuite
    public void finash(){
        ExcelUtils.writeBack();
    }
}
