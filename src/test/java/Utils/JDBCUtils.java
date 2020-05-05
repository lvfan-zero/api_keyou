package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {
    public static List<Map<String,Object>> jdbcConn(String sql){
        if (sql == null){
            return null;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        ResultSetMetaData remd = null;
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        try {
            //加载数据库驱动
            Class.forName(Contants.JDBC_MYSQL_DRIVER);
            //声明数据库连接
            conn = DriverManager.getConnection(Contants.JDBC_MYSQL_URL,Contants.JDBC_MYSQL_USER,Contants.JDBC_MYSQL_PWD);
            //声明预处理对象
            ps = conn.prepareStatement(sql);
            //执行SQL语句
            res = ps.executeQuery();
            //获取表结构
            remd = res.getMetaData();
            //遍历结果集
            int count = 0;
            while(res.next()){
                count++;
                if (count>100){
                    break;
                }
                Map<String,Object> map = new HashMap<String, Object>();
                for (int i = 0; i < remd.getColumnCount(); i++) {
                    map.put(remd.getColumnLabel(i),res.getObject(i)==null?"":res.getObject(i).toString());
                }
                result.add(map);

            }
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
