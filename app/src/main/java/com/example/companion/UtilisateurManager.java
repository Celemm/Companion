package com.example.companion;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager extends AppCompatActivity {

    private static List<Utilisateur> utilisateurs = new ArrayList<>();

    public static void ajouterUtilisateur(Utilisateur U) {
        utilisateurs.add(U);
    }

    public static Utilisateur trouverParIdentifiantEtMotDePasse(String ps, String id, String mdp) {
        for (Utilisateur U : utilisateurs) {
            if (U.getPseudo().equals(ps) && U.getIdentifiant().equals(id)&& U.getPassword().equals(mdp)) {
                return U;
            }
        }
        return null;
    }
}
