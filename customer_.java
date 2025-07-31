/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hsbank;

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 

public class customer_ {
   Connection con1;
   PreparedStatement insert;
   ResultSet rs;
   PreparedStatement update;
   public String customer_id;
   private String firstname;
   private String lastname;
   private String street;
   private String city;
   private String branch;
   private String phone;
   customer_(String customer_id_, String firsname_, String lastname_, String street_, String city_, String branch_, String phone_)
   {
        customer_id=customer_id_;
        firstname=firsname_;
        lastname=lastname_;
        street=street_;
        city=city_;
        branch=branch_;
        phone=phone_;
   }
   customer_(){}
   customer_(String customer_id_)
   {
       customer_id=customer_id_;
   }
   public void Set_in_DB()
   {
       try{
        
        Class.forName("com.mysql.jdbc.Driver");
        
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
        insert = con1.prepareStatement("insert into customer(cust_id,firstname,lastname,street,city,branch,phone)values(?,?,?,?,?,?,?)");
        insert.setString(1,customer_id);
        insert.setString(2,firstname);
        insert.setString(3,lastname);
        insert.setString(4,street);
        insert.setString(5,city);
        insert.setString(6,branch);
        insert.setString(7,phone);
        insert.executeUpdate();
        JOptionPane.showMessageDialog(null, "Sucsessfully Saved");
        
            
      } catch (ClassNotFoundException ex) {
          
           Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Некоректный ввод данных");
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public ResultSet get_from_DB()
   {
       try
       {
        Class.forName("com.mysql.jdbc.Driver");
            
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
        insert=con1.prepareStatement("SELECT * FROM customer WHERE cust_id=?");
        insert.setString(1, customer_id);
        rs=insert.executeQuery();
        
        } 
       catch (ClassNotFoundException ex) 
       {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        } 
       catch (SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
       return rs;
         
   }
    
    
}
