package ru.geekbrains.mynotes;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_note_text_fragment,menu);
        menu.findItem(R.id.action_version).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.action_toast_add_file):
                Toast.makeText(requireContext(),"Добавляется любой файл", Toast.LENGTH_LONG).show();
                return true;
            case (R.id.action_toast_will_share):
                Toast.makeText(requireContext(),"Отправляем заметку в соцсети", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        note = getArguments().getParcelable(ARG_NOTE);
        getChildFragmentManager().beginTransaction().replace(R.id.container_child, NoteTextChildFragment.newInstance(note)).addToBackStack("").commit();
        TextView textViewTwo = view.findViewById(R.id.textViewTwo);
        TypedArray noteBodyTwo = getResources().obtainTypedArray(R.array.note_name);
        textViewTwo.setText(noteBodyTwo.getResourceId(note.getIndex(), R.string.one));
        textViewTwo.setTextAppearance(R.style.textStyleOne);
        TextView textView = view.findViewById(R.id.textView);
        textView.setTextAppearance(R.style.textStyleTwo);
        TypedArray noteBody = getResources().obtainTypedArray(R.array.note_body);
        textView.setText(noteBody.getResourceId(note.getIndex(), R.string.monday));
    }
}