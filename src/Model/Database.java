package Model;

import java.sql.*;

public class Database {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/socialmedia";
    private Statement statement;

    public Database() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Statement getStatement() {
        return statement;
    }

}