package ru.geekbrains.mynotes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyNotesFragment extends Fragment {

    public static final String CURRENT_NOTE = "note_current";
    private Note currentNote;

    public static MyNotesFragment newInstance() {
        MyNotesFragment fragment = new MyNotesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_notes, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(CURRENT_NOTE, currentNote);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = new Note(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand();
        }
        initView(view);
    }

    private void initView(View view) {
        String[] notes = getResources().getStringArray(R.array.note_name);
        for (int i = 0; i < notes.length; i++) {
            String noteName = notes[i];
            TextView textView = new TextView(getContext());
            textView.setTextAppearance(R.style.textStyleOne);
            textView.setGravity(1);
            textView.setText(noteName);
            ((LinearLayout) view).addView(textView);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentNote = new Note(finalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showLand();
                    } else {
                        showPort();
                    }
                }
            });
        }
    }

    private void showLand() {
        NoteTextFragment noteTextFragment = NoteTextFragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.noteContent, noteTextFragment).commit();
    }

    private void showPort() {
        NoteTextFragment noteTextFragment = NoteTextFragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.listOfNotes, noteTextFragment).addToBackStack("").commit();

    }
}
