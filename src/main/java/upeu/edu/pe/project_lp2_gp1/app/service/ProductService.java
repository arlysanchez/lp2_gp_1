/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.app.service;

import java.io.IOException;
import java.time.LocalDateTime;
import org.slf4j.*;
import org.springframework.web.multipart.MultipartFile;
import upeu.edu.pe.project_lp2_gp1.app.repository.ProductRepository;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.ProductEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.UserEntity;

/**
 *
 * @author LAB-2
 */
public class ProductService {

    private final ProductRepository productRepository;
    private final UploadFile uploadFile;
    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    

    public ProductService(ProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
    }

    

    public Iterable<ProductEntity> getProducts() {
        return productRepository.getProducts();
    }
    public Iterable<ProductEntity> getProductsByUser(UserEntity user) {
        return productRepository.getProductsByUser(user);
    }
    public ProductEntity getProductById(Integer id){
        return productRepository.getProductById(id);
    }
    public ProductEntity saveProduct(ProductEntity product,MultipartFile multipartFile) throws IOException{
        if(product.getId()==null){
            UserEntity user = new UserEntity();
            user.setId(1);
            product.setDateCreate(LocalDateTime.now());
            product.setDateUpdate(LocalDateTime.now());
            product.setUserEntity(user);
            product.setImage(uploadFile.upload(multipartFile));
            return productRepository.saveProduct(product);

        }else{
         ProductEntity productDB = productRepository.getProductById(product.getId());
         //logger para ver en consola el producto a editar
          if(multipartFile.isEmpty()){
             product.setImage(productDB.getImage());
         }else{
             //se guardará la imagen que se envia actualmente
             //antes se elimina pero si no es por defecto
             if(!productDB.getImage().equals("default.jpg")){
                 uploadFile.delete(productDB.getImage());
             }
             product.setImage(uploadFile.upload(multipartFile));
         }
         log.info("product: {}",productDB);
         //sino se carga la imagen toma la que se le guardo al registro
        
         product.setCode(productDB.getCode());
         product.setUserEntity(productDB.getUserEntity());
         product.setDateCreate(productDB.getDateCreate());
         product.setDateUpdate(LocalDateTime.now());
         return productRepository.saveProduct(product);
        }
        
    }
    
    
    
    public void deleteProductById(Integer id){
          productRepository.deleteProductById(id);
    }
    //PARA EL API
    
       public ProductEntity saveProductApi(ProductEntity product) throws IOException{
        if(product.getId()==null){
            UserEntity user = new UserEntity();
            user.setId(1);
            product.setDateCreate(LocalDateTime.now());
            product.setDateUpdate(LocalDateTime.now());
            product.setUserEntity(user);
            return productRepository.saveProduct(product);

        }else{
         ProductEntity productDB = productRepository.getProductById(product.getId());
                 log.info("product: {}",productDB);
         //sino se carga la imagen toma la que se le guardo al registro
        
         product.setCode(productDB.getCode());
         product.setUserEntity(productDB.getUserEntity());
         product.setDateCreate(productDB.getDateCreate());
         product.setDateUpdate(LocalDateTime.now());
         return productRepository.saveProduct(product);
        }
        
    }
            
    
    
}
