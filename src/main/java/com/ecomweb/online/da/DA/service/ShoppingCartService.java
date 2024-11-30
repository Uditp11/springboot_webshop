package com.ecomweb.online.da.DA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.model.ShoppingCart;

@Service
public class ShoppingCartService {

    private final ShoppingCart cart = new ShoppingCart();

    @Autowired
    private ProductService productService;

    public ShoppingCart getCart() {
        return cart;
    }

    public void addProductToCart(int productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            cart.addProduct(product, 1); 
        }
    }
}
