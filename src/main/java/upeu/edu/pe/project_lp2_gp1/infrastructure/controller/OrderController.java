/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upeu.edu.pe.project_lp2_gp1.infrastructure.controller;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import upeu.edu.pe.project_lp2_gp1.app.domain.ItemCart;
import upeu.edu.pe.project_lp2_gp1.app.service.CartService;
import upeu.edu.pe.project_lp2_gp1.app.service.OrderProductService;
import upeu.edu.pe.project_lp2_gp1.app.service.OrderService;
import upeu.edu.pe.project_lp2_gp1.app.service.ProductService;
import upeu.edu.pe.project_lp2_gp1.app.service.StockService;
import upeu.edu.pe.project_lp2_gp1.app.service.UserServices;
import upeu.edu.pe.project_lp2_gp1.app.service.ValidateStock;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.OrderEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.OrderProductEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.StockEntity;
import upeu.edu.pe.project_lp2_gp1.infrastructure.entity.UserEntity;

/**
 *
 * @author LAB-2
 *
 */
@Controller
@RequestMapping("/user/order")
public class OrderController {

    private final CartService cartService;
    private final UserServices userServices;
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderProductService orderProductService;
    private final Integer entradas = 0;
    private final ValidateStock validateStock;
    private final StockService stockService;
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(CartService cartService, UserServices userServices, OrderService orderService, ProductService productService, OrderProductService orderProductService, ValidateStock validateStock, StockService stockService) {
        this.cartService = cartService;
        this.userServices = userServices;
        this.orderService = orderService;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.validateStock = validateStock;
        this.stockService = stockService;
    }

    @GetMapping("/sumary-order")
    public String showSumaryOrder(Model model, HttpSession httpSession) {
        UserEntity user = userServices.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);
        model.addAttribute("id", httpSession.getAttribute("iduser").toString());
        return "user/sumaryorder";

    }

    @GetMapping("create-order")
    public String create(RedirectAttributes attributes, HttpSession httpSession) {
        UserEntity user = userServices.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        OrderEntity order = new OrderEntity();
        order.setDateCreated(LocalDateTime.now());
        order.setStatus("proceso");
        order.setUser(user);
        log.info("order {}", order);
        //creación de order
        orderService.createOrder(order);

        List<OrderProductEntity> orderProduct = new ArrayList<>();
        for (ItemCart itemCart : cartService.getItemCarts()) {
            orderProduct.add(new OrderProductEntity(productService.getProductById(itemCart.getIdProduct()),
                    itemCart.getQuantity(), order));
        }
        orderProduct.forEach(
                op -> {
                    orderProductService.create(op);
                    StockEntity stock = new StockEntity();
                    stock.setDescripcion("salidas");
                    stock.setEntradas(entradas);
                    stock.setProductEntity(op.getProductEntity());
                    stock.setSalidas(op.getQuantity());
                    stockService.saveStock(validateStock.calculateBalance(stock));

                }
        );

        cartService.removeAllItemsCart();
        attributes.addFlashAttribute("id", httpSession.getAttribute("iduser").toString());
        return "redirect:/home";
    }

}
