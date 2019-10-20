/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
//import model.Snack;
import model.Soda;
//import model.SnackDao;
import model.SodaDao;

/**
 *
 * @author 7ravis
 */
public class SodaServiceImpl implements SodaService {
    private SodaDao sodaDao;
    private BigDecimal balance;
    private int selection;
    private Change myChange;
    private String message;
    
    @Inject
    public SodaServiceImpl(SodaDao sodaDao) {
        this.sodaDao = sodaDao;
        balance = new BigDecimal("0.00");
        selection = 0;
        myChange = null;
        message = null;
    }

    @Override
    public void addMoney(String amount) {
        switch (amount) {
            case "dollar":
                balance = balance.add(new BigDecimal("1.00"));
                break;
            case "quarter":
                balance = balance.add(new BigDecimal("0.25"));
                break;
            case "dime":
                balance = balance.add(new BigDecimal("0.10"));
                break;
            case "nickel":
                balance = balance.add(new BigDecimal("0.05"));
                break;
            default:
        }
    }
    @Override
    public void makePurchase() {
        if (selection != 0) {
//            BigDecimal balance = snackService.getBalance();
            Soda soda = sodaDao.getSodaById(selection);
            BigDecimal selectionPrice = soda.getPrice();
            if (soda.getQuantity() <= 0) {
                message = "SOLD OUT!!!";
            } else if (balance.compareTo(selectionPrice) < 0) {
                BigDecimal difference = selectionPrice.subtract(balance);
                message = "Please Deposit: $" + difference;
            } else {
                BigDecimal newBalance = balance.subtract(selectionPrice);
                Change change = new Change(newBalance);
                myChange = change;
                balance = new BigDecimal("0.00");
                int newSodaQuantity = soda.getQuantity() - 1;
                soda.setQuantity(newSodaQuantity);
                message = "Thank You!!!";
            }
        }
    }
    @Override
    public void changeReturn() {
        Change change = new Change(balance);
        myChange = change;
        balance = new BigDecimal("0.00");
        selection = 0;
        message = null;
    }
    
    @Override
    public List<Soda> getSodas() {
        return sodaDao.getSodas();
    }
    @Override
    public Soda getSodaById(int id) {
        return sodaDao.getSodaById(id);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    @Override
    public int getSelection() {
        return selection;
    }
    @Override
    public void setSelection(int Selection) {
        this.selection = Selection;
    }
    @Override
    public Change getMyChange() {
        return myChange;
    }
    @Override
    public void setMyChange(Change myChange) {
        this.myChange = myChange;
    }      
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void setMessage(String message) {
        this.message = message;
    }    
}
