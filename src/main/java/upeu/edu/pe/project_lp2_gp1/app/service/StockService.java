/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.app.service;

import java.util.List;
import upeu.edu.pe.project_lp2_gp1.app.repository.StockRepository;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.ProductEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.StockEntity;

/**
 *
 * @author LAB-2
 */
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public StockEntity saveStock(StockEntity stockEntity) {
        return stockRepository.saveStock(stockEntity);
    }

    public List<StockEntity> getStockByProduct(ProductEntity productEntity) {
        return stockRepository.getStockByProductEntity(productEntity);

    }

}
