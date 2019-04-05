package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edPeso, edAltura, edUrl;
    Button btCalcular, btNavegar;
    TextView txResposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPeso = findViewById(R.id.editPeso);
        edAltura = findViewById(R.id.editAltura);
        edUrl = findViewById(R.id.editUrl);

        txResposta = findViewById(R.id.textResposta);

        btCalcular = findViewById(R.id.btnCalcular);
        btNavegar = findViewById(R.id.btnNavegar);

        /*=========================================================================================================*/

        //Botão p/ calcular

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double peso = Double.parseDouble(String.valueOf(edPeso.getText()));
                    double altura = Double.parseDouble(String.valueOf(edAltura.getText())) * 2;
                    double imc = peso / altura;

                    txResposta.setText(String.valueOf(infoImc(imc)));
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Favor inserir um numero! :)", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*=========================================================================================================*/

        //Botão de navegar na web

        btNavegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent é um objeto que solicita uma ação de outro componente (uma pagina da web por exemplo)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+edUrl.getText().toString()));
                startActivity(intent);
            }
        });

        /*=========================================================================================================*/


    }

    public String infoImc(double imc){
        //O Metodo String.format() formata uma String, me permitindo alterar a quantidade de casas decimais
        if(imc < 17){
            return String.format("O imc %.2f é considerado muito abaixo do peso",imc);
        }else if(imc >= 17 && imc <= 18.49){
            return String.format("O imc %.2f é considerado abaixo do peso",imc);
        }else if(imc >= 18.5 && imc <= 24.99){
            return String.format("O imc %.2f é considerado Peso normal",imc);
        }else if(imc >= 25 && imc <= 29.99){
            return String.format("O imc %.2f é considerado Acima do peso",imc);
        }else if(imc >=30 && imc <= 34.99){
            return String.format("O imc %.2f é considerado Obesidade I",imc);
        }else if(imc >= 35 && imc <= 39.99){
            return String.format("O imc %.2f é considerado Obesidade II(severa)",imc);
        }else{
            //return "O imc "+ imc +" é considerado Obesidade III((mórbida))";
            return String.format("O imc %.2f é considerado Obesidade III (mórbida)",imc);
        }
    }


}
