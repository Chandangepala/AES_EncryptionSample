package com.basic.aes_encryptionsample;

import static com.basic.aes_encryptionsample.AESExample.decrypt;
import static com.basic.aes_encryptionsample.AESExample.encrypt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.basic.aes_encryptionsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AESExample aesExample = new AESExample();

        mainBinding.btnEncrypt.setOnClickListener(view -> {


            if (mainBinding.edtMessage.getEditText().getText() != null
                    && !mainBinding.edtMessage.getEditText().getText().equals("")){

                mainBinding.progressBar.setVisibility(View.VISIBLE);

                new Thread(() -> {
                    String message =  mainBinding.edtMessage.getEditText().getText().toString();
                    String encryptedMessage = encrypt(message);
                    String decryptedMessage =  decrypt(encryptedMessage);

                    runOnUiThread(() -> {
                        mainBinding.txtOriginal.setText("Original string: \n\n" + message);
                        mainBinding.txtEncrypt.setText("Encrypted string: \n\n" + encryptedMessage);
                        mainBinding.txtDecrypt.setText("Decrypted string: \n\n" + decryptedMessage);
                        mainBinding.progressBar.setVisibility(View.GONE);
                    });

                }).start();


            }else {
                mainBinding.edtMessage.setError("Invalid message");
                Toast.makeText(this, "Type a valid message", Toast.LENGTH_SHORT).show();
            }
            mainBinding.edtMessage.getEditText().setText("");
        });
    }


}