package ru.geekbrains.mynotes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.action_version):
                getSupportFragmentManager().beginTransaction().replace(R.id.listOfNotes,new VersionFragment()).addToBackStack("").commit();
                return true;
            case (R.id.action_exit):
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
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