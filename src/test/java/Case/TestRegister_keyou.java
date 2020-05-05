package Case;

import Utils.ExcelUtils;
import org.testng.annotations.DataProvider;

public class TestRegister_keyou extends BaseCase_keyou {

    @DataProvider(name = "data")
    public Object[][] data(){
        Object[][] data = ExcelUtils.readforApiId("1");
        return data;
    }

}
