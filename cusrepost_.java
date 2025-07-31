/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hsbank;

/**
 *
 * @author User
 */
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class cusrepost_ extends customer_{
    
    cusrepost_(String cust_id_)
    {
        customer_id=cust_id_;
    }
    public List<List<String>> accounts() {
    List<List<String>> accounts = new ArrayList<>();
    
    
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank", "root", "root");
         PreparedStatement stmt = conn.prepareStatement("SELECT acc_id, balance FROM account WHERE cust_id = ?")) {
        
        // Устанавливаем параметр запроса
        stmt.setString(1, customer_id);
        
        // Выполняем запрос
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                List<String> row = new ArrayList<>(2);
                row.add(rs.getString("acc_id"));
                row.add(rs.getString("balance"));
                accounts.add(row);
            }
        }
        
    } catch (SQLException ex) {
        
        Logger.getLogger(cusrepost_.class.getName()).log(Level.SEVERE, "Database error", ex);
        throw new RuntimeException("Failed to fetch accounts", ex);
    }
    
    return accounts;
}
    
    @Override
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

