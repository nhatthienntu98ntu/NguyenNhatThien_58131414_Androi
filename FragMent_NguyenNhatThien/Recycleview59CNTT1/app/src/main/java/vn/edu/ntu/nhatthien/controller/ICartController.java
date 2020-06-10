package vn.edu.ntu.nhatthien.controller;

import java.util.List;

import vn.edu.ntu.nhatthien.Model.Product;

public interface ICartController {
    public List<Product> getAllProduct();
    public boolean addToCart(Product p);
    public List<Product> getAllCart();
    public boolean delItemCart(Product p);
    public void delete_cart();
    public boolean addProduct(Product p);
}
