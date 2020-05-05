package Case;

import Utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class TestLogin_keyou extends BaseCase_keyou {

    @DataProvider(name = "data")
    public Object[][] data(){
        Object[][] data = ExcelUtils.readforApiId("2");
        return data;
    }


}
