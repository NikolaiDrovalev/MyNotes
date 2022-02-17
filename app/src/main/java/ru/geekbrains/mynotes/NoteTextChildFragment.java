package ru.geekbrains.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class NoteTextChildFragment extends Fragment {

    public static final String ARG_CHILD = "childArg";
    private Note note;

    public static NoteTextChildFragment newInstance(Note note) {
        NoteTextChildFragment fragment = new NoteTextChildFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_CHILD, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_text_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = getArguments().getParcelable(ARG_CHILD);
        TextView textView = view.findViewById(R.id.textViewData);
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        textView.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        textView.setTextAppearance(R.style.textStyleOne);

        Button button = view.findViewById(R.id.doNotData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }
}