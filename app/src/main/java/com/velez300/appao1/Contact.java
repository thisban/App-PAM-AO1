package com.velez300.appao1;

public class Contact {
    private String name;
    private String surname;
    private String phone;
    private String address;

    private int iconPath;

    public Contact(String name, String surname, String phone, String address, int iconPath) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }
    public String getAddress() {
        return address;
    }
    public int getIconPath() {
        return iconPath;
    }
}