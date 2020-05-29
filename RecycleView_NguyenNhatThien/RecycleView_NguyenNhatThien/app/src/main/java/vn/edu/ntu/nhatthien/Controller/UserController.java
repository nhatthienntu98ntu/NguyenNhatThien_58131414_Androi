package vn.edu.ntu.nhatthien.Controller;

import android.app.Application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nhatthien.Model.User;

public class UserController extends Application implements IUserController{
    List<User> listUser = new ArrayList<>();
    public UserController() {
        listUser.add(new User(0, "Nguyễn Nhật Thiên", "012345678", "4/3/1998", "Phú Yên"));
        listUser.add(new User(1, "Nguyễn Nhật Thanh", "0165445678", "5/6/1998", "Đà Nẵng"));
        listUser.add(new User(2, "Nguyễn Nhật Thi", "012887678", "3/4/1998", "Sài Gòn"));
        listUser.add(new User(3, "Nguyễn Nhật Thiện", "012345678", "1/2/1998", "Nha Trang"));
    }

    @Override
    public boolean addUser(User user) {
        if(listUser.contains(user)){
            return false;
        }
        listUser.add(user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        for (int i = 0; i < listUser.size(); i++){
            if(user.getId() == listUser.get(i).getId()) {
                listUser.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        for (int i = 0; i < listUser.size(); i++){
            if(user.getId() == listUser.get(i).getId()) {
                listUser.get(i).setName(user.getName());
                listUser.get(i).setPhone(user.getPhone());
                listUser.get(i).setBirthday(user.getBirthday());
                listUser.get(i).setAdd(user.getAdd());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        return listUser;
    }
}
