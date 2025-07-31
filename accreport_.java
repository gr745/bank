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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 

public class accreport_  extends account_{
   Connection con1;
   PreparedStatement insert;
   ResultSet rs;
   PreparedStatement update;
   
   accreport_(String account_id_)
   {
       super(account_id_);
   }
   public List<List<String>> deposits() {
    List<List<String>> accounts = new ArrayList<>();
    
    
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank", "root", "root");
         PreparedStatement stmt = conn.prepareStatement("SELECT date, deposit FROM deposit WHERE acc_id = ?")) {
        
        // Устанавливаем параметр запроса
        stmt.setString(1, account_id);
        
        // Выполняем запрос
        try (ResultSet rs = stmt.executeQuery()) {
            Boolean hd=false;
            while (rs.next()) {
                hd=true;
                List<String> row = new ArrayList<>(2);
                row.add(rs.getString("date"));
                row.add(rs.getString("deposit"));
                accounts.add(row);
            }
            if(!hd)
            {
                JOptionPane.showMessageDialog(null, "Account is not found");
            }
        }
        
    } catch (SQLException ex) {
        
        
        Logger.getLogger(cusrepost_.class.getName()).log(Level.SEVERE, "Database error", ex);
        throw new RuntimeException("Failed to fetch accounts", ex);
        
    }
    
    return accounts;
}
   public List<List<String>> withdraws() {
    List<List<String>> accounts = new ArrayList<>();
    
    
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank", "root", "root");
         PreparedStatement stmt = conn.prepareStatement("SELECT date, withdraw FROM withdraw WHERE acc_id = ?")) {
        
        // Устанавливаем параметр запроса
        stmt.setString(1, account_id);
        
        // Выполняем запрос
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                List<String> row = new ArrayList<>(2);
                row.add(rs.getString("date"));
                row.add(rs.getString("withdraw"));
                accounts.add(row);
            }
        }
        
    } catch (SQLException ex) {
        
        Logger.getLogger(cusrepost_.class.getName()).log(Level.SEVERE, "Database error", ex);
        throw new RuntimeException("Failed to fetch accounts", ex);
    }
    
    return accounts;
}
   public List<List<String>> transfers() {
    List<List<String>> accounts = new ArrayList<>();
    
    
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank", "root", "root");
         PreparedStatement stmt = conn.prepareStatement("SELECT t_account, amount FROM transfer WHERE f_account = ?")) {
        
        // Устанавливаем параметр запроса
        stmt.setString(1, account_id);
        
        // Выполняем запрос
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                List<String> row = new ArrayList<>(2);
                row.add(rs.getString("t_account"));
                row.add(rs.getString("amount"));
                accounts.add(row);
            }
        }
        
    } catch (SQLException ex) {
        
        Logger.getLogger(cusrepost_.class.getName()).log(Level.SEVERE, "Database error", ex);
        throw new RuntimeException("Failed to fetch accounts", ex);
    }
    
    return accounts;
}
    
}
