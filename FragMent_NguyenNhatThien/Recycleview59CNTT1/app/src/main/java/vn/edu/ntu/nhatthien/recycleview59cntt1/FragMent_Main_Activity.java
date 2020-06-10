package vn.edu.ntu.nhatthien.recycleview59cntt1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.edu.ntu.nhatthien.Model.Product;
import vn.edu.ntu.nhatthien.controller.ICartController;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragMent_Main_Activity extends Fragment {


    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;


    FloatingActionButton floatingActionButton;

    NavController controller;


    public FragMent_Main_Activity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frag_ment__main__activity, container, false);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        addView(view);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = NavHostFragment.findNavController(FragMent_Main_Activity.this);
                controller.navigate(R.id.action_fragMent_Main_Activity_to_productFragment);
            }
        });
    }

    private void addView(View view){

        LinearLayoutManager layoutManager = new  LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListProduct);
        recyclerView.setLayoutManager(layoutManager);
        ICartController controller =(ICartController) getActivity().getApplication();
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
            txtPrice.setText(fomatPrice(new Integer( p.getPrice()).toString()) + " vnđ");
            txtDesc.setText(p.getDesc());

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
            if (controller.addToCart(p)){
                Toast.makeText(getActivity(), "Đã thêm " + p.getName() + "vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), p.getName() + " đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
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



        @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        controller = NavHostFragment.findNavController(this);
            ((MainActivity)getActivity()).controller=controller;
    }
}
