package com.velez300.appao1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.velez300.appao1.databinding.ActivityNuevoContactoBinding;
import com.velez300.appao1.singletone.ContactList;

public class NuevoContacto extends AppCompatActivity {
    ActivityNuevoContactoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityNuevoContactoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageButton btnBackToList = binding.btnBackToList;
        ImageButton btnSaveContact = binding.btnSaveContact;

        btnBackToList.setOnClickListener( v -> {
            backToList();
        });
        btnSaveContact.setOnClickListener(v -> {
            saveContact();
        });

    }

    public void saveContact(){
        String txtName = binding.txtName.getText().toString().trim();
        String txtSurname = binding.txtSurname.getText().toString().trim();
        String txtPhone = binding.txtPhone.getText().toString().trim();
        String txtAddress = binding.txtAddress.getText().toString().trim();

        int iconGender = getIconGender();

        if(!txtName.isEmpty() && !txtSurname.isEmpty() && txtPhone.length() > 3 && !txtAddress.isEmpty()){
            Contact nuevo = new Contact(txtName.toUpperCase(), txtSurname.toUpperCase(),"+549" + txtPhone,txtAddress,iconGender);

            ContactList.getInstance().addContact(nuevo);
            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();

            backToList();
        } else if (txtName.isEmpty()) {
            errorMsgOf("Nombre");
        } else if (txtSurname.isEmpty()){
            errorMsgOf("Apellido");
        } else if (txtPhone.length()<3) {
            errorMsgOf("Teléfono");
        } else if (txtAddress.isEmpty()) {
            errorMsgOf("Domicilio");
        }
    }

    private int getIconGender() {
        RadioGroup genderRadioGroup = binding.genderRadioGroup;
        RadioButton btnRadio1 = binding.btnRadio1;
        RadioButton btnRadio2 = binding.btnRadio2;

        int iconGender;

        int btnR = genderRadioGroup.getCheckedRadioButtonId();

        if(btnR == btnRadio1.getId()){
                iconGender = R.drawable.icon_male;
        } else if (btnR == btnRadio2.getId()) {
                iconGender = R.drawable.icon_female;
            } else{
                iconGender = R.drawable.icon_male; //por defecto
            }
        return iconGender;
    }

    public void errorMsgOf(String text){
        Toast.makeText(NuevoContacto.this, "El campo "+ text +" no puede estar vacío", Toast.LENGTH_SHORT).show();
    }

    public void backToList(){
        Intent intent = new Intent(NuevoContacto.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}