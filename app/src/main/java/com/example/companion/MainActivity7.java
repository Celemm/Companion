package com.example.companion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.companion.databinding.ActivityMain7Binding;

public class MainActivity7 extends BaseActivity {
    private ActivityMain7Binding binding;
    private Button btnChangePseudo, btnLogout, btnConditions;
    private TextView tvPseudo, tvIdentifiant;
    private Switch darkModeSwitch;

    // Clés SharedPreferences
    private static final String PREFS_NAME = "CompanionPrefs";
    private static final String KEY_IDENTIFIANT = "IDENTIFIANT";
    private static final String KEY_PSEUDO = "PSEUDO";
    private static final String KEY_DARK_MODE = "DARK_MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        displayUserInfo();
        setupDarkModeSwitch();
        setupButtons();
        setupNavigation();
    }

    private void initViews() {
        tvPseudo = findViewById(R.id.textView15);
        tvIdentifiant = findViewById(R.id.textView14);
        btnChangePseudo = findViewById(R.id.button2);
        btnLogout = findViewById(R.id.button5);
        btnConditions = findViewById(R.id.button3);
        darkModeSwitch = findViewById(R.id.switch1);
    }

    private void displayUserInfo() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String pseudo = prefs.getString(KEY_PSEUDO, getIntent().getStringExtra(KEY_PSEUDO));
        String identifiant = prefs.getString(KEY_IDENTIFIANT, getIntent().getStringExtra(KEY_IDENTIFIANT));

        tvPseudo.setText(pseudo != null ? pseudo : "");
        tvIdentifiant.setText(identifiant != null ? identifiant : "");
    }

    private void setupDarkModeSwitch() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean(KEY_DARK_MODE, false);

        darkModeSwitch.setChecked(isDarkMode);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 1. Désactiver temporairement le Switch pour éviter les déclenchements multiples
            darkModeSwitch.setEnabled(false);

            // 2. Sauvegarder la préférence
            prefs.edit().putBoolean(KEY_DARK_MODE, isChecked).apply();

            // 3. Appliquer le thème avec un délai minimal
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                AppCompatDelegate.setDefaultNightMode(
                        isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
                );

                // 4. Recréer sans animation
                recreate();

                // 5. Réactiver le Switch après un court délai
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    darkModeSwitch.setEnabled(true);
                }, 500);
            }, 100);
        });
    }

    private void setupButtons() {
        btnChangePseudo.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra(KEY_PSEUDO, tvPseudo.getText().toString());
            startActivity(intent);
        });

        btnConditions.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity6.class));
        });

        btnLogout.setOnClickListener(v -> performLogout());
    }

    private void performLogout() {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit().clear().apply();

        Intent intent = new Intent(this, MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }

    private void setupNavigation() {
        binding.bottomNavigationView.setSelectedItemId(R.id.nav_profil);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Class<?> destination = null;

            if (itemId == R.id.nav_home) {
                destination = MainActivity4.class;
            } else if (itemId == R.id.nav_code) {
                destination = MainActivity8.class;
            } else if (itemId == R.id.emploi) {
                destination = MainActivity5.class;
            }

            if (destination != null) {
                navigateTo(destination);
                return true;
            }
            return false;
        });
    }

    private void navigateTo(Class<?> destination) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Intent intent = new Intent(this, destination)
                .putExtra(KEY_PSEUDO, prefs.getString(KEY_PSEUDO, ""))
                .putExtra(KEY_IDENTIFIANT, prefs.getString(KEY_IDENTIFIANT, ""))
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserInfo(); // Rafraîchit les infos utilisateur
    }
}