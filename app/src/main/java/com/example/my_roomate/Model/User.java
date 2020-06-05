package com.example.my_roomate.Model;

public class User {
    private String id_user,names,last_name,address,ubication,phone,bio,curp,email,password,photo_profile;
    private String[] interestings;

    public User(String id_user, String names, String last_name, String address, String ubication, String phone, String bio, String curp, String email, String password, String photo_profile, String[] interestings) {
        this.id_user = id_user;
        this.names = names;
        this.last_name = last_name;
        this.address = address;
        this.ubication = ubication;
        this.phone = phone;
        this.bio = bio;
        this.curp = curp;
        this.email = email;
        this.password = password;
        this.photo_profile = photo_profile;
        this.interestings = interestings;
    }

    public User(String id_user, String email, String password) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
    }

    public String getId_user() {
        return id_user;
    }

    public String getNames() {
        return names;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getUbication() {
        return ubication;
    }

    public String getPhone() {
        return phone;
    }

    public String getBio() {
        return bio;
    }

    public String getCurp() {
        return curp;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public String[] getInterestings() {
        return interestings;
    }
}
