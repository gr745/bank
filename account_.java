/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hsbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 

/**
 *
 * @author User
 */
public class account_  extends customer_{
    protected String account_id;
    protected String account_type;
    protected int balance;
    
    public account_(String customer_id, String account_id, String account_type, int balance) {
        super(customer_id);
        this.account_id=account_id;
        this.account_type=account_type;
        this.balance=balance;
    }
    public account_(String account_id){
        this.account_id=account_id;
    }
    
    @Override
    public void Set_in_DB()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
        insert = con1.prepareStatement("insert into account(acc_id,cust_id,acc_type,balance)values(?,?,?,?)");
        insert.setString(1,account_id);
        insert.setString(2,customer_id);
        insert.setString(3,account_type);
        insert.setInt(4,balance);
        
        insert.executeUpdate();
        JOptionPane.showMessageDialog(null, "Sucsessfully Saved");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Некоректный ввод данных");
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void credit(int amount) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
            update=con1.prepareStatement("update account set balance=balance+? where acc_id=?");
            update.setInt(1, amount);
            update.setString(2, account_id);
            update.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(account_.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(account_.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
     public ResultSet get_from_DB()
   {
       try
       {
        Class.forName("com.mysql.jdbc.Driver");
            
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
        insert=con1.prepareStatement("SELECT c.cust_id, c.firstname, c.lastname, a.balance FROM customer c, account a where c.cust_id=a.cust_id and a.acc_id=?");
        insert.setString(1, account_id);
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

    public boolean debit(int amount) {
        account_ c=new account_(account_id);
        Boolean t=true;
        try{
        rs=c.get_from_DB();

        if(rs.next()==false)
        {
           JOptionPane.showMessageDialog(null, "Account is not found");
        }
        else{
            balance=rs.getInt("balance");
            if (balance >= amount) {
            credit(-amount);
           
            
        } else {
            JOptionPane.showMessageDialog(null, "Недостаточно средств для осуществления списания");
            t=false;
        }
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
}
