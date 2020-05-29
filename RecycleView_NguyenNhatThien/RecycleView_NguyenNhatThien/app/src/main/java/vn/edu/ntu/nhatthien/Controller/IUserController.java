package vn.edu.ntu.nhatthien.Controller;

import java.util.List;

import vn.edu.ntu.nhatthien.Model.User;

public interface IUserController {
    public List<User> getAllUser();
    public boolean addUser(User user);
    public boolean deleteUser(User user);
    public boolean updateUser(User user);
}
