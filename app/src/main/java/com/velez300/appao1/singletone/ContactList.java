package com.velez300.appao1.singletone;

import com.velez300.appao1.Contact;
import com.velez300.appao1.R;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private static ContactList instance;

    private ArrayList<Contact> contacts;

    private ContactList() {
        contacts = new ArrayList<>();

        contacts.add(new Contact("Emergencias","","911","" , R.drawable.icon_male));
        contacts.add(new Contact("Bomberos","Voluntarios","100","Sarmiento 120" , R.drawable.icon_male));
        contacts.add(new Contact("Policia","","101","Av. Independencia 300" , R.drawable.icon_male));
        contacts.add(new Contact("Servicio","Médico","102","Av. Pelegrini 60" , R.drawable.icon_male));
    }

    public static synchronized ContactList getInstance() {
        if (instance == null) {
            instance = new ContactList();
        }
        return instance;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact newContact) {
        contacts.add(newContact);
    }
}