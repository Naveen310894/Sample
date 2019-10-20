/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.util.List;
// import model.Snack;
import model.Soda;

/**
 *
 * @author 7ravis
 */
public interface SodaService {
    
    public void addMoney(String amount);
    
    public void makePurchase();
    
    public void changeReturn();

    BigDecimal getBalance();

    String getMessage();

    Change getMyChange();

    int getSelection();

    Soda getSodaById(int id);

    List<Soda> getSodas();

    void setBalance(BigDecimal balance);

    void setMessage(String message);

    void setMyChange(Change myChange);

    void setSelection(int Selection);
    
}
