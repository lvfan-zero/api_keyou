package Utils;

public class Contants {
    //请求头常量
    public static final String HEADER_CONTENT_TYPE_NAME = "Content-type";
    public static final String HEADER_CONTENT_TYPE_VALUE_JSON = "application/json";
    public static final String HEADER_CONTENT_TYPE_VALUE_FORM = "application/x-www-form-urlencoded";

    public static final String HEADER_MEDIA_TYPE_NAME = "X-Lemonban-Media-Type";
    public static final String HEADER_MEDIA_TYPE_VALUE_V1 = "lemonban.v1";
    public static final String HEADER_MEDIA_TYPE_VALUE_V2 = "lemonban.v2";
    public static final String HEADER_MEDIA_TYPE_VALUE_V3 = "lemonban.v3";

    //请求参数常量
    public static final String excel_path = "src\\test\\resources\\测试用例.xls";

    //响应结果常量
    public static final String STATUS_CODE = "statusCode";
    public static final int WRITE_BACK_ACTUAL_CELLNUM_RESPONSE = 11;
    public static final int WRITE_BACK_ACTUAL_CELLNUM_RESULT = 12;

    //数据库常量
    public static final String JDBC_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_MYSQL_URL = "jdbc:mysql://api.lemonban.com:3306/";
    public static final String JDBC_MYSQL_USER = "future";
    public static final String JDBC_MYSQL_PWD = "123456";
}
