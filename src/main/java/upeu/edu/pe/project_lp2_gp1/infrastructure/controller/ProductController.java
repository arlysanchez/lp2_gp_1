/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import upeu.edu.pe.project_lp2_gp1.app.service.ProductService;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.ProductEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.UserEntity;

/**
 *
 * @author LAB-2
 */
@RestController
@RequestMapping("/admin/products/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //guardar product
    @PostMapping("/save-product")
    public String saveProduct(@RequestBody ProductEntity productEntity){
      return productService.saveProduct(productEntity).toString();
    }
    //ver productos
    @GetMapping("/show")
    public Iterable <ProductEntity> showProduct(){
        UserEntity user = new UserEntity();
        user.setId(1);
        return productService.getProductsByUser(user);
    }
     
     @GetMapping("/show/{id}")
    public ProductEntity show(@PathVariable Integer id){
       return productService.getProductById(id);
    }
    
    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity editProduct(@RequestBody ProductEntity product, @PathVariable Integer id) {
        ProductEntity productActual = productService.getProductById(id);
        productActual.setDescription(product.getDescription());
        productActual.setName(product.getName());
        productActual.setPrice(product.getPrice());
        productActual.setUserEntity(product.getUserEntity());
        return productService.saveProduct(productActual);
       // log.info("Product obtenido: {}", product);
        //model.addAttribute("product", product);
        //return "admin/products/edit";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
       // return "redirect:/admin/products/show";
    }
    

    
}
