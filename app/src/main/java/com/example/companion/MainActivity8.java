package com.example.companion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.companion.databinding.ActivityMain7Binding;
import com.example.companion.databinding.ActivityMain8Binding;

public class MainActivity8 extends  BaseActivity {
private ActivityMain8Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain8Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setSelectedItemId(R.id.nav_code);
        // Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startNewActivity(MainActivity4.class);
                return true;
            } else if (itemId == R.id.nav_code) {

                return true;
            } else if (itemId == R.id.emploi) {
                startNewActivity(MainActivity5.class);
                return true;
            } else if (itemId == R.id.nav_profil) {
                startNewActivity(MainActivity7.class);
                return true;
            }
            return false;
        });
    }

    private void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        // Transférer le pseudo à la nouvelle activité
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        ImageButton backButton = findViewById(R.id.button);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}