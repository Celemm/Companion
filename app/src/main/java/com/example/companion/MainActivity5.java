package com.example.companion;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.companion.databinding.ActivityMain5Binding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity5 extends BaseActivity{
    private ActivityMain5Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Afficher la date actuelle
        setCurrentDate();

        binding.bottomNavigationViews.setSelectedItemId(R.id.emploi);

        // Bottom Navigation
        binding.bottomNavigationViews.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startNewActivity(MainActivity4.class);
                return true;
            } else if (itemId == R.id.nav_code) {
                startNewActivity(MainActivity8.class);
                return true;
            } else if (itemId == R.id.emploi) {
                return true;
            } else if (itemId == R.id.nav_profil) {
                startNewActivity(MainActivity7.class);
                return true;
            }
            return false;
        });
    }

    private void setCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        binding.textView16.setText(currentDate);
    }

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        String pseudo = getIntent().getStringExtra("PSEUDO");
        if (pseudo != null) {
            intent.putExtra("PSEUDO", pseudo);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}