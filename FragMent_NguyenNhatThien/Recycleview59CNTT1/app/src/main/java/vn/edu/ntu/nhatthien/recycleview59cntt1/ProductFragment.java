package vn.edu.ntu.nhatthien.recycleview59cntt1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import vn.edu.ntu.nhatthien.Model.Product;
import vn.edu.ntu.nhatthien.controller.ICartController;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    NavController controller;

    EditText edtAddName, edtAddGia, edtAddGioiThieu;
    Button btnAdd;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        edtAddName = view.findViewById(R.id.edtAddName);
        edtAddGia = view.findViewById(R.id.edtAddGia);
        edtAddGioiThieu = view.findViewById(R.id.edtAddChiTiet);
        btnAdd = view.findViewById(R.id.btnAdd);
        return view;
    }

    private void addProduct(){
        ICartController controller = (ICartController) getActivity().getApplication();
        if (edtAddName.getText().toString() != "" || edtAddGia.getText().toString() != "" || edtAddGioiThieu.getText().toString() != ""){
            if(controller.addProduct(new Product(edtAddName.getText().toString(), edtAddGioiThieu.getText().toString(), new Integer(edtAddGia.getText().toString())))){
                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(getActivity(), "Không thêm được", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        controller = NavHostFragment.findNavController(this);
        ((MainActivity)getActivity()).controller=controller;
    }
}
