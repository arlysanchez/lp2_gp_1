/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.infrastructure.adapter;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.OrderEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.OrderProductEntity;

/**
 *
 * @author LAB-2
 */
public interface OrderProductCrudRepository extends CrudRepository<OrderProductEntity, Integer>{
    public List<OrderProductEntity> findByOrderEntity(OrderEntity orderEntity); 
}
