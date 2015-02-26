import java.sql.*;

public class MysqlAccessor {
  Connection connection;
  Statement statement;

  public MysqlAccessor(String host, String database, String username, String password) throws
      ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database,username,password);
    statement = connection.createStatement();
  }

  public ResultSet execute(String sql) throws SQLException {
    if(statement.execute(sql))
      return statement.getResultSet();
    return null;
  }

  public ResultSet query(String sql) throws SQLException {
    return statement.executeQuery(sql);
  }

  public int update(String sql) throws SQLException {
    return statement.executeUpdate(sql);
  }
  
  public void close() throws SQLException {
      statement.close();
      connection.close();
  }
}
