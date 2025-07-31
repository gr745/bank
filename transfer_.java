/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hsbank;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class transfer_ extends transaction{
    protected account_ recipent;
    
    transfer_(String account_id_1, String account_id_2, int amount_)
    {
        super(account_id_1);
        recipent=new account_(account_id_2);
        amount=amount_;
    }
    @Override
    public Boolean execute()
    {
        boolean t=true;
        if(debit(amount)){
        recipent.credit(amount);
        }
        else
        {
            t=false;
        }
        return t;
      
    }
    @Override
    public void Set_in_DB()
    {
        try { 

            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:8889/bank","root","root");
            con1.setAutoCommit(false);
            
                
            PreparedStatement st3=con1.prepareStatement("insert into transfer(f_account,t_account,amount) values(?,?,?)");
	    st3.setString(1,account_id);
	    st3.setString(2,recipent.account_id);
	    st3.setInt(3,amount);
	    st3.executeUpdate();     
            JOptionPane.showMessageDialog(null, "Amount Transfer...!!!!!!!");
                        

                  
            con1.commit();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(account.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
          
            try {
               con1.rollback();
                 JOptionPane.showMessageDialog(null, "Transanction Failed");
               
            } catch (SQLException ex1) {
                Logger.getLogger(transfer.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
