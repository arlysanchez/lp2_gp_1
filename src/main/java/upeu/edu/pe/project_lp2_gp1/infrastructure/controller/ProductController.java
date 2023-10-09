/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.infrastructure.controller;

import java.io.IOException;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import upeu.edu.pe.project_lp2_gp1.app.service.ProductService;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.ProductEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.UserEntity;
/**
 *
 * @author LAB-2
 */
@Controller
@RequestMapping("admin/products")
public class ProductController {
    private final ProductService productService;
private final Logger log = LoggerFactory.getLogger(ProductController.class);    

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    //crear nuevo producto
    @GetMapping("/create")
    public String create(){
        return "admin/products/create";
    }
    //guardar producto
     @PostMapping("/save-product")
    public String saveProduct(ProductEntity product,@RequestParam("img")MultipartFile multipartFile) throws IOException {
        log.info("Nombre de producto: {}", product);
        productService.saveProduct(product, multipartFile);
        //return "admin/products/create";
         return "redirect:/admin";
    }
    
    @GetMapping("/show")
    public String showProduct(Model model){
        //log.info("id user desde la variable de session: {}");
        UserEntity user = new UserEntity();
        user.setId(1);
        Iterable<ProductEntity> products = productService.getProductsByUser(user);
        model.addAttribute("products", products);
        return "admin/products/show";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model){
        ProductEntity product = productService.getProductById(id);
        log.info("Product obtenido: {}", product);
        model.addAttribute("product",product);
        return "admin/products/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProductById(id);
        return "redirect:/admin";
    }
        
    
    
}
