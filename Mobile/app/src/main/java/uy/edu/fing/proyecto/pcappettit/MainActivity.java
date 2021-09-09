package uy.edu.fing.proyecto.pcappettit;

import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.os.Build.VERSION_CODES.M;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISOS_REQUERIDOS = 1;
    SignInButton signInButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_button);
        loginButton = findViewById(R.id.login_button);

        signInButton.setColorScheme(signInButton.COLOR_DARK);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        TextView textView = (TextView) signInButton.getChildAt(0);
        textView.setText(getString(R.string.google_login));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(ilogin);
            }
        });

        if (Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                //String[] permisos = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
                //requestPermissions(permisos, PERMISOS_REQUERIDOS);
            }else{
            //    realizarConexion();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISOS_REQUERIDOS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //realizarConexion();
                }else{
                    AlertDialog dialog = new AlertDialog.Builder(this).create();
                    dialog.setTitle(R.string.access_title_err);
                    //dialog.setIcon(R.drawable.icon);
                    dialog.setMessage(getString(R.string.access_msg_err));
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.alert_btn_positive), new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(permissions, PERMISOS_REQUERIDOS);
                        }
                    });
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.alert_btn_negative), new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= JELLY_BEAN){
                                finishAffinity();
                            } else{
                                finish();
                            }
                        }
                    });
                    dialog.show();

                }

            }
        }
    }

}