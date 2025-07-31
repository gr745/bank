/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hsbank;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class withdraw_ extends transaction{
    withdraw_(String account_id_, int amount_, String date_)
    {
        super(account_id_);
        amount=amount_;
        date=date_;
    }
    @Override
    public Boolean execute() {
        return debit(amount);
        
    }
    public void set_in_DB() 
    {
        
        account_ c=new account_(account_id);
        try{
        rs=c.get_from_DB();

        if(rs.next()==false)
        {
           //JOptionPane.showMessageDialog(null, "Account is not found");
        }
        else{
            customer_id=rs.getString("cust_id");
            balance=rs.getInt("balance");
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
            insert = con1.prepareStatement("insert into withdraw(acc_id,cust_id,date,balance, withdraw)values(?,?,?,?,?)");
            insert.setString(1,account_id);
            insert.setString(2,customer_id);
            insert.setString(3,date);
            insert.setInt(4,balance);
            insert.setString(5,""+amount);
            insert.executeUpdate();
       
        JOptionPane.showMessageDialog(null, "Withdraw is created");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
