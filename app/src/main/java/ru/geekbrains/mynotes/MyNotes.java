package ru.geekbrains.mynotes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MyNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_notes_activity);

        if (savedInstanceState == null) {
            MyNotesFragment myNotesFragment = MyNotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.listOfNotes, myNotesFragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.listOfNotes);
        if (backStackFragment != null && backStackFragment instanceof NoteTextFragment) {
            onBackPressed();
        }
    }
}