package ru.geekbrains.mynotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showPopupMenu(view);
                return false;
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showToast("Что-то произошло");
                return false;
            }
        });
        popupMenu.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        menu.findItem(R.id.action_version).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_version):
                getSupportFragmentManager().beginTransaction().replace(R.id.listOfNotes, new VersionFragment()).addToBackStack("").commit();
                return true;
            case (R.id.action_exit):
                showAlertDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void showAlertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Выход из приложения")
                .setMessage("Вы уверены?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    showToast("Вышли из приложения");
                    finish();
                })
                .setNegativeButton("Нет", (dialogInterface, i) -> {
                    showToast("Закрыли диалоговое окно");
                })
                .setNeutralButton("Поставить оценку", (dialogInterface, i) -> {
                    showToast("Оценка - 5 ☆");
                })
                .show();
    }

    void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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