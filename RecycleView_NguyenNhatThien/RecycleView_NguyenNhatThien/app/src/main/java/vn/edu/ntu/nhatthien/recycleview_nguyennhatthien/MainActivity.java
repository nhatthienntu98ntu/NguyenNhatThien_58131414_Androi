package vn.edu.ntu.nhatthien.recycleview_nguyennhatthien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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

import vn.edu.ntu.nhatthien.Controller.IUserController;
import vn.edu.ntu.nhatthien.Model.User;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<User> listUser;
    AdapterUser adapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }

    //////////ADD VIEW FOR RECYCLEVIEW
    private void addView(){
        recyclerView = findViewById(R.id.rvUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        IUserController controller = (IUserController) getApplication();
        listUser = controller.getAllUser();
        adapterUser = new AdapterUser(listUser);
        recyclerView.setAdapter(adapterUser);
    }


    //////////VIEW HOlDER
    private class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtBirthday, txtPhone, txtId;
        ImageView imgCreate;
        User user;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPhone = this.itemView.findViewById(R.id.txtPhone);
            txtBirthday = this.itemView.findViewById(R.id.txtBirthDay);
            imgCreate = this.itemView.findViewById(R.id.imgCreate);
            imgCreate.setOnClickListener(this);

        }

        public void bindUser(User user){
            this.user = user;
            txtName.setText(user.getName());
            txtPhone.setText(user.getPhone());
            txtBirthday.setText(user.getBirthday());
        }



        @Override
        public void onClick(View v) {
            showUser(user);
        }
    }


    /////////////////ADAPTER
    private class AdapterUser extends RecyclerView.Adapter<UserViewHolder>{
        List<User> listUser;

        public AdapterUser(List<User> listUser) {
            this.listUser = listUser;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.contact, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
                holder.bindUser(listUser.get(position));
        }

        @Override
        public int getItemCount() {
            return listUser.size();
        }
    }


    private void showUser(User user){
        Intent intent = new Intent(this, User_Activity.class);
        intent.putExtra("txtId",String.valueOf(user.getId()));
        intent.putExtra("txtName",user.getName());
        intent.putExtra("txtPhone",user.getPhone());
        intent.putExtra("txtBirthDay",user.getBirthday());
        intent.putExtra("txtAdd",user.getAdd());
        startActivity(intent);
    }

    private void showCreateUser(){
        Intent intent = new Intent(this, Create_User.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnu_Create:
                showCreateUser();
                break;
            case R.id.mnu_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addView();
    }
}
