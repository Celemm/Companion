package com.example.companion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.companion.BaseActivity;
import com.example.companion.MainActivity4;
import com.example.companion.R;

public class MainActivity9 extends BaseActivity {
    private TextView tvPseud;
    private static final String KEY_PSEUDO = "PSEUDO";
    private static final String PREFS_NAME = "MesPrefs"; // Ajoutez cette ligne

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9); // Doit être appelé en premier

        initViews();
        displayUserInfo();

        ImageButton backButton = findViewById(R.id.button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity9.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        tvPseud = findViewById(R.id.textView29);
    }

    private void displayUserInfo() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String pseudo = prefs.getString(KEY_PSEUDO, "");

        // Optionnel: vérifier aussi l'intent
        if (pseudo.isEmpty() && getIntent() != null) {
            pseudo = getIntent().getStringExtra(KEY_PSEUDO);
        }

        if (tvPseud != null) {
            tvPseud.setText(pseudo != null ? pseudo : "");
        }
    }
}