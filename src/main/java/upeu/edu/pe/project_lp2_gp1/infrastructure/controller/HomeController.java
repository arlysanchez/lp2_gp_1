/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.infrastructure.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import upeu.edu.pe.project_lp2_gp1.app.service.ProductService;
import upeu.edu.pe.project_lp2_gp1.app.service.StockService;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.StockEntity;

/**
 *
 * @author LAB-2
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final StockService stockService;

    public HomeController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
    }

    
    
    
    
    @GetMapping
    public String home(Model model){
        
        
        model.addAttribute("products", productService.getProducts());
        return "home";
        
    }
    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id, Model model){
        List<StockEntity> stocks = stockService.getStockByProduct(productService.getProductById(id));
        //log.info("Id product: {}", id);
        //log.info("stock size: {}", stocks.size());
        Integer lastBalance = stocks.get(stocks.size()-1).getBalance();

        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("stock", lastBalance);
        try {
            //usuario en sesion
            model.addAttribute("id", 1);
        }catch (Exception e){

        }
        return "user/productdetail";
    } 
    
    
}
