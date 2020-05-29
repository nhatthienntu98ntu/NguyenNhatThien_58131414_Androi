package vn.edu.ntu.nhatthien.recycleview59cntt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nhatthien.Model.Product;
import vn.edu.ntu.nhatthien.controller.ICartController;

public class Cart_Activity extends AppCompatActivity implements View.OnClickListener{

    TextView txtCart;
    Button btnSubmit;
    Button btnDelete_Cart;
    Button btnGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);
        addView();
    }

    private void addView(){
        txtCart = findViewById(R.id.txtCart);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnDelete_Cart = findViewById(R.id.btnDelete_Cart);
        btnGoBack = findViewById(R.id.btnGoBack);
        btnDelete_Cart.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);
        showCart();
    }

    private void showCart(){
        ICartController controller = (ICartController) getApplication();
        List<Product> cart = controller.getAllCart();
        StringBuilder builder = new StringBuilder();
        for (Product p:cart){
            builder.append(p.getName())
                    .append("\t\t\t")
                    .append(p.getPrice())
                    .append("\t\t\t")
                    .append(p.getDesc())
                    .append("\n");
        }
        if(builder.toString().length() > 0){
            txtCart.setText(builder.toString());
        }else{
            txtCart.setText("Giỏ hàng trống! Mời bạn mua thêm hàng!");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDelete_Cart:
                delete();
                break;
            case R.id.btnSubmit:
                submit();
                break;
//            case R.id.btnGoBack:
//                goBack();
//                break;
        }
    }

    private void delete(){
        ICartController controller = (ICartController) getApplication();
        controller.delete_cart();
        showCart();
    }

    private void submit(){
        Toast.makeText(Cart_Activity.this, "Đã submit", Toast.LENGTH_SHORT).show();
    }

//    private void goBack(){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}
