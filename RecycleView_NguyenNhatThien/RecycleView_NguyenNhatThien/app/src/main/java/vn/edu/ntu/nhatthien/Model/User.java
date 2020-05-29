package vn.edu.ntu.nhatthien.Model;

import java.util.Date;

public class User {
    String name, phone, birthday, add;
    int id;

    public User() {
    }

    public User(int id, String name, String phone, String birthday, String add) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }
}
