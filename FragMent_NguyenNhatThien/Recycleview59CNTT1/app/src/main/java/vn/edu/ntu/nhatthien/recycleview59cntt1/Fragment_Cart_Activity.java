package vn.edu.ntu.nhatthien.recycleview59cntt1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;

import vn.edu.ntu.nhatthien.Model.Product;
import vn.edu.ntu.nhatthien.controller.ICartController;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Cart_Activity extends Fragment implements View.OnClickListener{

        NavController controller;

//    TextView txtCart;
    ImageView imgSubmit;
    ImageView imgDelete_cart;
    TextView txtListCart;

    RecyclerView recyclerView;
    List<Product> cartList;
    CartAdapter cartAdapter;


    public Fragment_Cart_Activity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__cart__activity, container, false);

//        txtCart = view.findViewById(R.id.txtCart);
        imgSubmit = view.findViewById(R.id.imgSubmit);
        imgDelete_cart = view.findViewById(R.id.imgDelete_cart);
        txtListCart = view.findViewById(R.id.txtListCart);
        recyclerView = view.findViewById(R.id.rcvCart);

        imgDelete_cart.setOnClickListener(this);
        imgSubmit.setOnClickListener(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        addView();
        return view;
    }



    private void addView(){

        ICartController controller = (ICartController) getActivity().getApplication();
        cartList = controller.getAllCart();
        cartAdapter = new CartAdapter(cartList);
        recyclerView.setAdapter(cartAdapter);
        if (controller.getAllCart().size() == 0){
            txtListCart.setText("Giỏ hàng trống! Vui lòng mua thêm hàng!");
        }else{
            txtListCart.setText("Danh sách sản phẩm");
        }

    }


    private class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtCartName, txtCartPrice, txtCartDesc;
        ImageView imgDeleteCart;

        Product p;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCartName = this.itemView.findViewById(R.id.textCartName);
            txtCartPrice = this.itemView.findViewById(R.id.textCartPrice);
            txtCartDesc = this.itemView.findViewById(R.id.textCartDesc);
            imgDeleteCart = this.itemView.findViewById(R.id.imgDeleteCart);
            imgDeleteCart.setOnClickListener(this);
        }

        public void bindProduct(Product p){
            this.p = p;
            txtCartName.setText(p.getName());
            txtCartPrice.setText(fomatPrice(new Integer( p.getPrice()).toString()) + " vnđ");
            txtCartDesc.setText(p.getDesc());

        }

        private String fomatPrice(String a){
            String n = new StringBuffer(a).reverse().toString();
            String string = "";
            for(int i = 0; i < n.length(); i++){
                if(i%3 == 0 && i!= 0){
                    string += '.';
                }
                string += n.charAt(i);
            }
            string = new StringBuffer(string).reverse().toString();
            return string;
        }

        @Override
        public void onClick(View v) {
            ICartController controller = (ICartController) getActivity().getApplication();
            if(controller.delItemCart(p)){
                Toast.makeText(getActivity(), "Đã xóa " + p.getName().toString(), Toast.LENGTH_SHORT).show();
                addView();
            }else{
                Toast.makeText(getActivity(), "Không xóa được", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

        List<Product> listCart;

        public CartAdapter(List<Product> listCart) {
            this.listCart = listCart;
        }

        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.cart_product, parent, false);
            return new CartViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
            holder.bindProduct(listCart.get(position));
        }

        @Override
        public int getItemCount() {
            return listCart.size();
        }
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imgDelete_cart:
                delete();
                addView();
                break;
            case R.id.imgSubmit:
                submit();
                break;
        }
    }

    private void delete(){
        ICartController controller = (ICartController) getActivity().getApplication();
        controller.delete_cart();
//        showCart();
    }

    private void submit(){
        Toast.makeText(getActivity(), "Đã submit", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        controller = NavHostFragment.findNavController(this);
        ((MainActivity)getActivity()).controller=controller;
    }



}
