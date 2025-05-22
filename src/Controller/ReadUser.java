package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.ResultSet;

public class ReadUser {

    private boolean loggenIn;
    private User user;

    public ReadUser(String email, String password, Database database) {
        String select = "SELECT * FROM `users` WHERE `Email` = '"+email+"' AND `Password` = '"+password+"';";

        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            loggenIn = rs.next();
            if(loggenIn) {
                user = new User();
                user.setID(rs.getInt("ID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));

            }
        } catch (Exception e) {
            new Alert("Error: " + e.getMessage(), null, "alert.png");

        }
    }

    public boolean loggedIn() {
        return loggenIn;
    }

    public User getUser() {
        return user;
    }
}
