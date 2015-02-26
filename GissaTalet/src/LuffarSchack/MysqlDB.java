package LuffarSchack;

import java.sql.*;

public class MysqlDB {
    public static Connection connection;
    public static Statement statement;
    
    public static void showResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        String res = "";
//        StringBuffer result = new StringBuffer();
        int colCount = meta.getColumnCount();
        for(int i=1; i<=colCount; i++)
            res += meta.getColumnLabel(i) + ", ";
//            result.append(meta.getColumnLabel(i)+"\t");
        res += "\n";
//        result.append("\n");
        while(resultSet.next()) {
            for(int i=1; i<=colCount; i++)
                res += resultSet.getObject(i).toString() + ", ";
//                result.append(resultSet.getObject(i)+"\t");
            res += "\n";
//            result.append("\n");
        }
        System.out.println(res);
//        System.out.println(result);
    }
    
    public static void kopplaUpp() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:3306/da211t10c2051370","DA211T10C2051370","2051370");
            statement = connection.createStatement();
        } catch(ClassNotFoundException e1) {
            System.out.println("Databas-driver hittades ej: "+e1);
        }
    }
    
    public static void kopplaNer() throws SQLException {
        statement.close();
        connection.close();
    }

    public void sendQuery(String query){
        try {
            kopplaUpp();

            boolean result = statement.execute(query);

            kopplaNer();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    public void executeQuery(String query){

        try {
            kopplaUpp();

            ResultSet result = statement.executeQuery(query);
            showResultSet(result);

            kopplaNer();
        } catch(SQLException e) {

        }


    }
    
    public static void main(String[] args) {
        String query = "";
        try {
            kopplaUpp();
            
            ResultSet result = statement.executeQuery(query);
            showResultSet(result);
            
            kopplaNer();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}