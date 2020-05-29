package vn.edu.ntu.nhatthien.recycleview59cntt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.nhatthien.Model.Product;
import vn.edu.ntu.nhatthien.controller.ICartController;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }
    ///////////////// ADD VIEW LIST PRODUCT
    private void addView(){
        recyclerView = findViewById(R.id.rvListProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ICartController controller =(ICartController) getApplication();
        productList = controller.getAllProduct();
        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }
    /////////////////////// View HOLDER
     private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName, txtPrice, txtDesc;
        ImageView imageAddToCart;
        Product p;

         public ProductViewHolder(@NonNull View itemView) {
             super(itemView);
             txtName = this.itemView.findViewById(R.id.textName);
             txtPrice = this.itemView.findViewById(R.id.textPrice);
             txtDesc = this.itemView.findViewById(R.id.textDesc);
             imageAddToCart = this.itemView.findViewById(R.id.imageAddToCart);
             imageAddToCart.setOnClickListener(this);
         }

         public void bindProduct(Product p){
             this.p = p;
             txtName.setText(p.getName());
             txtPrice.setText(new Integer( p.getPrice()).toString());
             txtDesc.setText(p.getDesc());

         }

        @Override
        public void onClick(View v) {
            ICartController controller = (ICartController) getApplication();
            if (controller.addToCart(p)){
                Toast.makeText(MainActivity.this, "Đã thêm " + p.getName() + "vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, p.getName() + " đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    ////////////////////      ADAPTER
     private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

        List<Product> listProduct;

         public ProductAdapter(List<Product> listProduct) {
             this.listProduct = listProduct;
         }

         @NonNull
         @Override
         public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             LayoutInflater layoutInflater = getLayoutInflater();
             View view = layoutInflater.inflate(R.layout.product, parent, false);
             return new ProductViewHolder(view);
         }

         @Override
         public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
                holder.bindProduct(listProduct.get(position));
         }

         @Override
         public int getItemCount() {
             return listProduct.size();
         }
     }
    ///////////////////            MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //////////////// Sự kiên khi click vào item trên MENU
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnu_exit:
                finish();
                break;
            case R.id.mnu_cart:
                showCart();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    ///////////////// Chuyển sang Activity Cart
    private void showCart(){
        Intent intent = new Intent(this, Cart_Activity.class);
        startActivity(intent);
    }
}
