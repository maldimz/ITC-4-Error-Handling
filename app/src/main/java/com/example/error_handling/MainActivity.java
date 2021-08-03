package com.example.error_handling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    String nama;
    int angka1=0, angka2=0;
    int hasil=0;
    char[] ch;

    ArrayList<TextView> textView = new ArrayList<>(); //deklarasi arraylist
    ArrayList<EditText> editText = new ArrayList<>();
    ArrayList<Button> button = new ArrayList<>();
    private final int[] editTextId = { //deklarasi read by id using array
            R.id.et_angka_1,   //0
            R.id.et_angka_2,   //1
            R.id.et_array      //2
    };
    private final int textViewId[]={
            R.id.tv_angka_1,   //0
            R.id.tv_angka_2,   //1
            R.id.tv_hasil,     //2
            R.id.tv_judul,     //3
            R.id.tv_nama,      //4
            R.id.tv_nama_array //5
    };
    private final int buttonId[]={
            R.id.btn_cek,      //0
            R.id.btn_tambah,   //1
            R.id.btn_kurang,   //2
            R.id.btn_bagi,     //3
            R.id.btn_kali      //4
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<buttonId.length; i++){        //deklarasi fb dengan arraylist
            button.add(findViewById(buttonId[i]));
            button.get(i).setOnClickListener(this);
        }

        for(int i=0; i<textViewId.length; i++)
            textView.add(findViewById(textViewId[i]));
        for(int i=0; i<editTextId.length; i++)
            editText.add(findViewById(editTextId[i]));
    }
    private void cekAngka(){ //fungsi cek angka format
        try{
            angka1 = Integer.parseInt(editText.get(0).getText().toString()); // mengambil int dari edit text
            angka2 = Integer.parseInt(editText.get(1).getText().toString()); // dengan konversi string to int
            Log.d("INPUT ANGKA", "onCreate: Angka Terinput"); // kirim pesan debbug input
        }catch (NumberFormatException nfe){
            angka1 = 0; angka2=0;
            Log.d("Cek Format Angka", "onCreate: Harus Integer");
            Toast.makeText(MainActivity.this, "Harus Integer!!", Toast.LENGTH_SHORT).show(); //menampilkan pesan
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_cek){
            ch =  new char[6];
            nama = editText.get(2).getText().toString();
            Log.d("INPUT NAMA", "onCreate: " + nama); // kirim pesan debug input
            try {
                for(int i=0; i<nama.length(); i++){ //converter string to char
                    ch[i]=nama.charAt(i);
                }
            }catch (IndexOutOfBoundsException ioobe){ //index berlebih
                Log.d("Cek Index Array", "onCreate: Array melebihi batas dengan panjang " + nama.length()); //kirim pesan debug
                Toast.makeText(MainActivity.this, "Karakter Terlalu Banyak!!", Toast.LENGTH_SHORT).show(); //menampilkan pesan
            }
            textView.get(4).setText(String.valueOf(ch)); //set text dengan konversi char ke string
        }else{
            if(v.getId()==R.id.btn_tambah){
                cekAngka();
                textView.get(2).setText("Hasil = " + (angka1+angka2)); //tampilkan hasil
            }else if(v.getId()==R.id.btn_kurang){
                cekAngka();
                textView.get(2).setText("Hasil = " + (angka1-angka2)); //tampilkan hasil
            }else if(v.getId()==R.id.btn_kali){
                cekAngka();
                textView.get(2).setText("Hasil = " + (angka1*angka2)); //tampilkan hasil
            }else if(v.getId()==R.id.btn_bagi){
                try{
                    hasil = 0;
                    angka1 = Integer.parseInt(editText.get(0).getText().toString()); // mengambil int dari edit text
                    angka2 = Integer.parseInt(editText.get(1).getText().toString()); // dengan konversi string to int
                    Log.d("INPUT ANGKA", "onCreate: Angka Terinput"); // kirim pesan debbug input
                    hasil =  angka1/angka2;
                }catch (NumberFormatException nfe){
                    Log.d("Cek Format Angka", "onCreate: Harus Integer"); //menampilkan debug
                    Toast.makeText(MainActivity.this, "Harus Integer!!", Toast.LENGTH_SHORT).show(); //menampilkan pesan
                }catch (ArithmeticException ae){
                    Log.d("Cek Format Angka", "onCreate: PEMBAGI TIDAK BOLEH 0");
                    Toast.makeText(MainActivity.this, "Angka 2 Tidak Boleh 0!!", Toast.LENGTH_SHORT).show(); //menampilkan pesan
                }
                textView.get(2).setText("Hasil = " + hasil);
            }
        }
    }
}