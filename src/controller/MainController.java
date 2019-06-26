
package controller;

import dao.ProdusDao;
import dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Produs;
import model.User;


public class MainController {
    private String url = "jdbc:mysql://localhost/java1pcurs8";
    private Connection con;
    private MainController(){
        try {
            con = DriverManager.getConnection(url,"root","");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private static MainController SINGLETON = null;
    public static MainController getInstance(){
        if(SINGLETON == null){
            SINGLETON = new MainController();
        }
        return SINGLETON;
    }
    
    public boolean login (String username,String parola){
        boolean rez = false;
        UserDao userDao = new UserDao(con);
        try {
            Optional<User> optUser =userDao.gasesteUser(username);
            if(optUser.isPresent()){
                if(optUser.get().getParola().equals(parola)){
                    rez = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rez;
    }
     public boolean inregistrare (String username,String parola){
        boolean rez = false;
        UserDao userDao = new UserDao(con);
        try {
            Optional<User> optUser =userDao.gasesteUser(username);
            if(!optUser.isPresent()){
                userDao.adaugaUser(username, parola);
                rez = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rez;
    }
     
    public List<Produs> afiseazaToateProdusele (){
        ProdusDao prodDao = new ProdusDao(con);
        List<Produs> list = new ArrayList<>();
        try {
            list =  prodDao.afiseazaToateProdusele();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public void adaugaProdus(String nume,double pret){
        ProdusDao prodDao = new ProdusDao(con);
        try {
            prodDao.adaugaProdus(nume, pret);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    

    public void stergeProdus(int id){
        ProdusDao prodDao = new ProdusDao(con);
        try {
            prodDao.stergeProdus(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
