package ru.geekbrains.mynotes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteTextFragment extends Fragment {

    public static final String ARG_NOTE = "noteArg";
    private Note note;

    public static NoteTextFragment newInstance(Note note) {
        NoteTextFragment fragment = new NoteTextFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = getArguments().getParcelable(ARG_NOTE);
        TextView textViewTwo = view.findViewById(R.id.textViewTwo);
        TypedArray noteBodyTwo = getResources().obtainTypedArray(R.array.note_name);
        textViewTwo.setText(noteBodyTwo.getResourceId(note.getIndex(), R.string.one));
        textViewTwo.setTextColor(getResources().getColor(R.color.black));
        textViewTwo.setTextSize(30f);
        textViewTwo.setGravity(1);
        TextView textView = view.findViewById(R.id.textView);
        textView.setTextSize(40f);
        textView.setGravity(1);
        TypedArray noteBody = getResources().obtainTypedArray(R.array.note_body);
        textView.setText(noteBody.getResourceId(note.getIndex(), R.string.monday));
    }
}