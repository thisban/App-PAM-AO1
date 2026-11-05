package com.velez300.appao1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.velez300.appao1.databinding.ActivityMainBinding;
import com.velez300.appao1.singletone.ContactList;
import com.velez300.appao1.singletone.SessionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //SESSION MANAGER
        SessionManager session = SessionManager.getInstance(getApplicationContext());
        SplashScreen login = new SplashScreen();
        login.loginValidation(session);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText txtSearchBar = binding.txtSearchBar;
        ImageButton btnSearch = binding.btnSearch;
        RecyclerView contactRList = binding.contactRList;
        TextView txtEmpty = binding.txtEmpty;
        ImageView imgEmpty = binding.imgEmpty;
        TextView txtAddContact = binding.txtAddContact;
        FloatingActionButton btnAddContact = binding.btnAddContact;
        Button btnLogOut = binding.btnLogOut;

        //CARGA LOS DATOS EN EL RECYCLER VIEW
        ArrayList<Contact> numbersList = ContactList.getInstance().getContacts();
        Adapt adapt = new Adapt(numbersList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        contactRList.setLayoutManager(linearLayoutManager);
        contactRList.setAdapter(adapt);

        //MUESTRA UNA VISTA CUANDO NO HAY CONTACTOS CARGADOS.
        if(numbersList.isEmpty()){
            txtEmpty.setVisibility(View.VISIBLE);
            imgEmpty.setVisibility(View.VISIBLE);
            txtAddContact.setVisibility(View.VISIBLE);
        } else{
            txtEmpty.setVisibility(View.GONE);
            imgEmpty.setVisibility(View.GONE);
            txtAddContact.setVisibility(View.GONE);
        }

        //FUNCIONALIDAD DEL BOTON FLOTANTE
        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NuevoContacto.class);
            startActivity(intent);
            finish();
        });

        //FUNCIONALIDAD DE LA BARRA DE BUSQUEDA
        txtSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                filterInput(s,numbersList,adapt);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        //FUNCIONALIDAD DEL BOTON DE BUSQUEDA (LUPA)
        btnSearch.setOnClickListener(v -> filterInput(txtSearchBar.getText(),numbersList,adapt));

        //FUNCIONALIDAD DEL BOTON DE CERRAR SESIÓN
        btnLogOut.setOnClickListener( v -> session.logOut());
    }
    public void filterInput(CharSequence word, ArrayList<Contact> list, Adapt a){
        String input = word.toString().toLowerCase().trim();
        ArrayList<Contact> filteredList = new ArrayList<>();

        for (Contact contact : list) {
            if (contact.getName().toLowerCase().contains(input) || contact.getSurname().toLowerCase().contains(input)) {
                filteredList.add(contact);
            }
        }

        a.updateList(filteredList);
    }
}