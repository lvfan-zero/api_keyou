package Utils;

import POJO.API;
import POJO.Case;
import POJO.WriteBackData;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<API> apiList = readExcel(0, API.class);
    public static List<Case> caseList = readExcel(1, Case.class);

    public static List<WriteBackData> wbd = new ArrayList<WriteBackData>();


    /**
     * 将获取的Excel用例文件中的数据按照传入的APIId进行分类存储
     * @param apiId
     * @return
     */
    public static Object[][] readforApiId(String apiId){

        API api = null;
        List<Case> cases = new ArrayList<Case>();

        //遍历获取到的api数据信息，获取与传入的apiID一致的接口信息数据
        for (API apiData:apiList) {
            if (apiData.getApiId().equals(apiId)){
                api = apiData;
                break;
            }
        }
        //遍历获取到的Case用例信息，获取与传入的apiId一致的用例信息存入list集合中
        for (Case ca:caseList) {
            if (ca.getApiId().equals(apiId)){
                cases.add(ca);
            }
        }
        //将获取到的与传入APIID一致的数据整合到二维数组中
        Object[][] data = new Object[cases.size()][2];
        for (int i = 0; i < cases.size(); i++) {
            data[i][0] = api;
            data[i][1] = cases.get(i);
        }

        return data;
    }

    /**
     * 读取Excel文件，并解析为指定类对象
     * @param sheetIndex    指定读取Excel文件的sheet页
     * @param clazz         指定要解析的类
     * @param <T>
     * @return
     */
    public static <T> List<T> readExcel(int sheetIndex, Class<T> clazz){
        FileInputStream fis = null;
        List<T> list = null;
        try {
            //加载Excel文件
            fis = new FileInputStream(Contants.excel_path);
            //导入配置,创建空对象，相当于默认配置
            ImportParams params = new ImportParams();
            //设置读取的exce文件中的第几个sheet页数据，一般默认读取第0个
            params.setStartSheetIndex(sheetIndex);
            //判断Excel文件中列数据是否为空(与封装类Case/API类中的@NotNull注解一起使用)，判断指定单元格数据为空时，则跳过整行数据继续读取下一行
            //一般只判断必须数据是否为空，不需要每列数据都判断
            params.setNeedVerify(true);
            //执行导入
            //默认读取Excel文件的第0个sheet页
            list = ExcelImportUtil.importExcel(fis, clazz, params);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void writeBack(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(Contants.excel_path);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet("用例");

            //Excel内容回写
            System.out.println(wbd);
            for (WriteBackData writeBackData:wbd) {
                int rowNum = writeBackData.getRowNum();
                int cellNum_response = writeBackData.getCellNum_response();
                int cellNum_result = writeBackData.getCellNum_result();
                String response = writeBackData.getResponse();
                String result = writeBackData.getResult();
                Row row = sheet.getRow(rowNum);
                Cell cell_response = row.getCell(cellNum_response, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell_response.setCellValue(response);
                Cell cell_result = row.getCell(cellNum_result, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell_result.setCellValue(result);
            }
            fos = new FileOutputStream(Contants.excel_path);
            workbook.write(fos);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
