package vn.edu.ntu.nhatthien.recycleview_nguyennhatthien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.ntu.nhatthien.Controller.IUserController;
import vn.edu.ntu.nhatthien.Model.User;

public class Create_User extends AppCompatActivity implements View.OnClickListener {
    String name, birthday, phone, add;
    TextView txtId, txtName, txtPhone, txtBirthDay, txtAdd;
    Button btnSubmit, btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__user);


        IUserController controller = (IUserController) getApplication();
        int size = controller.getAllUser().size() ;

        txtId = findViewById(R.id.txtId);
        txtId.setText(String.valueOf(size));

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

    }

    private void addSubmit(){

        IUserController controller = (IUserController) getApplication();
        int size = controller.getAllUser().size() ;

        txtName = findViewById(R.id.txtName);
        txtBirthDay = findViewById(R.id.txtBirthDay);
        txtPhone = findViewById(R.id.txtPhone);
        txtAdd = findViewById(R.id.txtAdd);


        User user = new User(size, txtName.getText().toString(), txtPhone.getText().toString(), txtBirthDay.getText().toString(), txtAdd.getText().toString());

        if(controller.addUser(user)){
            Toast.makeText(Create_User.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(Create_User.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnSubmit:
                addSubmit();
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }


}
