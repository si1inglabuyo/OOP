package Controller;

import Model.Database;
import Model.User;
import View.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreateUser {

    private User u;
    private  Database database;

    public CreateUser(User u, Database database) {
        this.u = u;
        this.database = database;
    }

    public void create(){
        String insert = "INSERT INTO `users`(`FirstName`, `LastName`, `Email`," +
                " `Password`) VALUES ('"+u.getFirstName()+"','"+u.getLastName()+"'," +
                "'"+u.getEmail()+"','"+u.getPassword()+"');";

        try {
            database.getStatement().execute(insert);
        } catch (SQLException e) {
            new Alert(e.getMessage(), null, "alert.png");
        }
    }

    public boolean isEmailUsed() {
        String select =  "SELECT `ID` FROM `users` WHERE `Email` = '" + u.getEmail() + "' AND `Password` = '" + u.getPassword() + "';";
        boolean isUsed = false;
        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            isUsed = rs.next();
        } catch (SQLException e) {
            new Alert(e.getMessage(), null, "alert.png");
        }

        return isUsed;
    }

    public User getUser() {
        u.setComments(new ArrayList<>());
        u.setFriends(new ArrayList<>());
        u.setLikes(new ArrayList<>());
        u.setPosts(new ArrayList<>());

        String select = "SELECT `ID` FROM `users` WHERE `Email` = '"+u.getEmail()+"' AND `Password` = '"+u.getPassword() + "';";
        boolean isUsed = false;
        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            rs.next();
            u.setID(rs.getInt("ID"));
        } catch (SQLException e) {
            new Alert(e.getMessage(), null, "alert.png");
        }

        return u;


    }

}
