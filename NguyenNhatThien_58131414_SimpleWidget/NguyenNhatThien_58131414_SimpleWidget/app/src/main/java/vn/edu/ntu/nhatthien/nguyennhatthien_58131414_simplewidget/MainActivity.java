package vn.edu.ntu.nhatthien.nguyennhatthien_58131414_simplewidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtName;
    EditText edtNgaySinh;
    RadioGroup rdgGioiTinh;
    RadioButton radioButton;
    CheckBox cbXemPhim;
    CheckBox cbNgheNhac;
    CheckBox cbCaPhe;
    CheckBox cbONha;
    CheckBox cbNauAn;
    EditText edtSoThichKhac;
    Button btnXacNhan;
    String soThich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addContent();
        addEvent();
    }

    private void addContent(){
        edtName = findViewById(R.id.edtName);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtSoThichKhac = findViewById(R.id.edtSoThichKhac);
        rdgGioiTinh = findViewById(R.id.rdgGioiTinh);
        cbXemPhim = findViewById(R.id.cbXemPhim);
        cbNgheNhac = findViewById(R.id.cbNgheNhac);
        cbCaPhe = findViewById(R.id.cbCaPhe);
        cbONha = findViewById(R.id.cbONha);
        cbNauAn = findViewById(R.id.cbNauAn);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        soThich = "";
    }

    private void addEvent(){
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThi();
            }
        });
    }

    private void hienThi(){
        if(cbXemPhim.isChecked()){
            soThich+=cbXemPhim.getText().toString();
            soThich+="; ";
        }

        if(cbNgheNhac.isChecked()){
            soThich+=cbNgheNhac.getText().toString();
            soThich+="; ";
        }

        if(cbCaPhe.isChecked()){
            soThich+=cbCaPhe.getText().toString();
            soThich+="; ";
        }

        if(cbONha.isChecked()){
            soThich+=cbONha.getText().toString();
            soThich+="; ";
        }

        if(cbNauAn.isChecked()){
            soThich+=cbNauAn.getText().toString();
            soThich+="; ";
        }

        if(edtSoThichKhac.getText().toString() != null){
            soThich += edtSoThichKhac.getText().toString();
        }

        int radio = rdgGioiTinh.getCheckedRadioButtonId();
        radioButton = findViewById(radio);

        Toast.makeText(this, edtName.getText().toString()+"\nNgày sinh: "+edtNgaySinh.getText().toString()+"\nGiới tính: "+
                radioButton.getText().toString()+"\nSở thích: "+soThich.trim(), Toast.LENGTH_SHORT).show();
        soThich="";

    }

}
