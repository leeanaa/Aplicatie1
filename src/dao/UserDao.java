
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import model.User;


public class UserDao {
    private Connection con;
    private PreparedStatement stmt1,stmt2;
    public UserDao(Connection con){
        try {
            this.con = con;
            this.stmt1 = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            this.stmt2 = con.prepareStatement("INSERT INTO users VALUES(NULL,?,?)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Optional<User> gasesteUser (String username) throws SQLException{
        stmt1.setString(1, username);
        User user = null;
        ResultSet rs = stmt1.executeQuery();
        while(rs.next()){
            int i = rs.getInt("id");
            String u = rs.getString("username");
            String p = rs.getString("parola");
            user = new User(i,u,p);
        }
        return Optional.ofNullable(user);
    }
    public void adaugaUser (String username,String parola) throws SQLException{
        stmt2.setString(1, username);
        stmt2.setString(2, parola);
        stmt2.executeUpdate();
    }
    
}
