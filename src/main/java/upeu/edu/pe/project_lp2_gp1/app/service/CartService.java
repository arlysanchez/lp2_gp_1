/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import upeu.edu.pe.project_lp2_gp1.app.domain.ItemCart;

/**
 *
 * @author LAB-2
 */
public class CartService {

    private List<ItemCart> itemCarts;
    private HashMap<Integer, ItemCart> itemCartHashMap;

    public CartService() {
        this.itemCartHashMap = new HashMap<>();
        this.itemCarts = new ArrayList<>();
    }

    public void addItemCart(Integer idProduct, String nameProduct, Integer quantity,
            BigDecimal price) {
        ItemCart itemCart = new ItemCart(idProduct, nameProduct, quantity, price);
        itemCartHashMap.put(itemCart.getIdProduct(), itemCart);
        fillList();
    }

    public BigDecimal getTotalCart() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCart itemCart : itemCarts) {
            total = total.add(itemCart.getTotalPriceItem());
        }
        return total;
    }
    //metodo que me ayuda eliminar los productos añadidos al carrito
    public void removeItemCart(Integer idProduct){
        itemCartHashMap.remove(idProduct);
        fillList();
    }
    
    public void removeAllItemsCart(){
        itemCartHashMap.clear();
        itemCarts.clear();
    }
    private void fillList(){
        itemCarts.clear();
        itemCartHashMap.forEach(
                (integer, ItemCart)-> itemCarts.add(ItemCart)
        );
    }
    
 //para observar en consola el proceso de añadir un producto al carrito
    public List<ItemCart> getItemCarts(){
        return itemCarts;
    }

}
