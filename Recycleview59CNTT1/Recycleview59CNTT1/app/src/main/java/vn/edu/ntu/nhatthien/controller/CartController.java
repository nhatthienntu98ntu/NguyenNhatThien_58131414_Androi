package vn.edu.ntu.nhatthien.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nhatthien.Model.Product;

public class CartController extends Application implements ICartController {

    List<Product> listProduct = new ArrayList<>();
    List<Product> listProductCart = new ArrayList<>();

    public CartController() {
        listProduct.add(new Product("Khoai lang", "Khoai lang Đà Lạt", 25000 ));
        listProduct.add(new Product("Kim cương", "Kim cương Xịn", 1000000000 ));
        listProduct.add(new Product("Vàng", "Vàng 99999999", 100000000 ));
        listProduct.add(new Product("Ruby", "Ruby hàng hiếm", 10000000 ));
    }

    @Override
    public List<Product> getAllProduct() {
        return listProduct;
    }

    @Override
    public boolean addToCart(Product p) {
        if (listProductCart.contains(p))
            return false;
        listProductCart.add(p);
        return true;
    }

    @Override
    public List<Product> getAllCart() {
        return listProductCart;
    }

    @Override
    public void delete_cart() {
        listProductCart.clear();
    }
}
