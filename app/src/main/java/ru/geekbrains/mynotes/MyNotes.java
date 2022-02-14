package ru.geekbrains.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MyNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_notes_activity);

        if(savedInstanceState==null){
            MyNotesFragment myNotesFragment = new MyNotesFragment();
        }
    }
}