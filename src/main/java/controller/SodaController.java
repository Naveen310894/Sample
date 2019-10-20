/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import service.SnackService;
import service.SodaService;

/**
 *
 * @author 7ravis
 */
@Controller
public class SodaController {
    private SodaService sodaService;
    
    @Inject
    public SodaController(SodaService sodaService) {
        this.sodaService = sodaService;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("balance", sodaService.getBalance());
        model.addAttribute("selection", sodaService.getSelection());
        model.addAttribute("myChange", sodaService.getMyChange());
        model.addAttribute("sodas", sodaService.getSodas());
        model.addAttribute("message", sodaService.getMessage());
        return "index";
    }
    
    @RequestMapping(value="/addMoney/{amount}", method=RequestMethod.GET)
    public String addMoney(@PathVariable String amount) {
        sodaService.addMoney(amount);
        return "redirect:/";
    }
    
    @RequestMapping(value="/makePurchase", method=RequestMethod.GET)
    public String makePurchase() {
        sodaService.makePurchase();
        return "redirect:/";
    }
    
    @RequestMapping(value="/changeReturn", method=RequestMethod.GET)
    public String changeReturn() {
        sodaService.changeReturn();
        return "redirect:/";
    }
    
    @RequestMapping(value="/makeSelection/{id}", method=RequestMethod.GET)
    public String makeSelection(@PathVariable("id") int id) {
        sodaService.setSelection(id);
        return "redirect:/";
    }
}
