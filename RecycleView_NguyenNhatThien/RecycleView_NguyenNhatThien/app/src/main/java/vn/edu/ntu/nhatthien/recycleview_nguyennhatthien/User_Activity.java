package vn.edu.ntu.nhatthien.recycleview_nguyennhatthien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.ntu.nhatthien.Controller.IUserController;
import vn.edu.ntu.nhatthien.Model.User;

public class User_Activity extends AppCompatActivity implements View.OnClickListener {
    String txtId,  txtName, txtPhone, txtBirthDay, txtAdd;
    TextView id, name, phone, birthDay, add;
    Button btnDelete, btnBack, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);

        addView();
    }

    private void addView(){
        Intent intent = getIntent();
        txtId = intent.getStringExtra("txtId");
        txtName = intent.getStringExtra("txtName");
        txtPhone = intent.getStringExtra("txtPhone");
        txtBirthDay = intent.getStringExtra("txtBirthDay");
        txtAdd = intent.getStringExtra("txtAdd");


        id = findViewById(R.id.txtId);
        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtPhone);
        birthDay = findViewById(R.id.txtBirthDay);
        add = findViewById(R.id.txtAdd);

        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);

        btnDelete.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        id.setText(txtId);
        name.setText(txtName);
        phone.setText(txtPhone);
        birthDay.setText(txtBirthDay);
        add.setText(txtAdd);
    }

    private void deleteUser(){
        IUserController controller = (IUserController) getApplication();
        User user = new User(new Integer(txtId), txtName, txtPhone, txtBirthDay, txtAdd);
        if(controller.deleteUser(user)){
            Toast.makeText(User_Activity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(User_Activity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser(){
        IUserController controller = (IUserController) getApplication();
        User user = new User(new Integer(id.getText().toString()), name.getText().toString(), phone.getText().toString(), birthDay.getText().toString(), add.getText().toString());
        if(controller.updateUser(user)){
            Toast.makeText(User_Activity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(User_Activity.this, "Sửa không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDelete:
                deleteUser();
                break;
            case R.id.btnEdit:
                updateUser();
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
