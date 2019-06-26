
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produs;

public class ProdusDao {
     private Connection con;
    private PreparedStatement stmt1,stmt2,stmt3;
    public ProdusDao(Connection con){
        try {
            this.con = con;
            this.stmt1 = con.prepareStatement("SELECT * FROM produse");
            this.stmt2 = con.prepareStatement("INSERT INTO produse VALUES(NULL,?,?)");
            this.stmt3 = con.prepareStatement("DELETE FROM produse WHERE id = ?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    public List<Produs> afiseazaToateProdusele() throws SQLException{
        List<Produs> list = new ArrayList<>();
        ResultSet rs = stmt1.executeQuery();
        while(rs.next()){
            int i = rs.getInt("id");
            String n = rs.getString("nume");
            double p = rs.getDouble("pret");
            list.add(new Produs(i,n,p));
        }
        return list;
    }
    public void adaugaProdus(String nume, double pret) throws SQLException{
        stmt2.setString(1, nume);
        stmt2.setDouble(2, pret);
        stmt2.executeUpdate();
    }
    public void stergeProdus(int id) throws SQLException{
        stmt3.setInt(1, id);
        stmt3.executeUpdate();
    }
    
}
