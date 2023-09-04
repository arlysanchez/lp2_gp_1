/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.app.service;

import java.util.List;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.ProductEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.StockEntity;

/**
 *
 * @author LAB-2
 */
public class ValidateStock {
  
    private final StockService stockService;

    public ValidateStock(StockService stockService) {
        this.stockService = stockService;
    }
    
    private boolean existBalance(ProductEntity product){
        List<StockEntity> stockList =stockService.getStockByProduct(product);
        return stockList.isEmpty() ? false:true;
    }
}
