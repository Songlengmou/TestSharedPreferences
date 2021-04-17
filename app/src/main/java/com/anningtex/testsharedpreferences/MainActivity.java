package com.anningtex.testsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Administrator
 * desc:SharedPreferences
 */
public class MainActivity extends AppCompatActivity {
    private EditText etName, etPass;
    private CheckBox cbIsRememberPass;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etName = findViewById(R.id.et_Name);
        etPass = findViewById(R.id.et_Pass);
        cbIsRememberPass = findViewById(R.id.cbIsRememberPass);

        sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("remember", false);
        if (isRemember) {
            String name = sharedPreferences.getString("name", "");
            String pass = sharedPreferences.getString("pass", "");
            etName.setText(name);
            etPass.setText(pass);
            cbIsRememberPass.setChecked(true);
        }
    }

    public void login(View view) {
        String name = etName.getText().toString();
        String pass = etPass.getText().toString();
        if ("123".equals(name) && "123".equals(pass)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (cbIsRememberPass.isChecked()) {
                editor.putBoolean("remember", true);
                editor.putString("name", name);
                editor.putString("pass", pass);
            } else {
                editor.clear();
            }
            editor.commit();
            startActivity(new Intent(this, LoginSuccessfulActivity.class));
            finish();
        } else {
            Toast.makeText(this, "账号或密码有误", Toast.LENGTH_LONG).show();
        }
    }
}