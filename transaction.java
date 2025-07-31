/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hsbank;

/**
 *
 * @author User
 */
public abstract class transaction extends account_{
    protected int amount;
    protected String date;
    transaction(String account_id_)
    {
        super(account_id_);
    }
      
         
    public abstract Boolean execute();
    
}
