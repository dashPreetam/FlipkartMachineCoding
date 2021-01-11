package com.example.flipkartmachinecoding;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.example.flipkartmachinecoding.Constants.BUNDLE_ARG_ID;
import static com.example.flipkartmachinecoding.Constants.BUNDLE_ARG_IMAGE_URL;
import static com.example.flipkartmachinecoding.Constants.BUNDLE_ARG_NAME;

public class RoundFragment extends Fragment {

    ImageView imageView;
    EditText editText;
    Button submit;
    TextView textView;

    private String imageUrl;
    private String answer;
    private int id;
    private RoundFragmentListener listener;

    public interface RoundFragmentListener{
        void onResultSent(int result);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round, container, false);

        imageView = view.findViewById(R.id.fragment_imageView);
        editText = view.findViewById(R.id.fragment_editText);
        submit = view.findViewById(R.id.fragment_submit_button);
        textView = view.findViewById(R.id.nameField);

        if(getArguments() != null) {

            imageUrl = getArguments().getString(BUNDLE_ARG_IMAGE_URL);
            answer = getArguments().getString(BUNDLE_ARG_NAME);
            id = getArguments().getInt(BUNDLE_ARG_ID);
            Log.d("TAG", imageUrl);
            Picasso.get().load(imageUrl).into(imageView);
            textView.setText(answer);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndReturnValue();
            }
        });

        return view;
    }

    private void checkAndReturnValue() {
//        if("".equals(editText.getText().toString().trim())) {
//            Toast.makeText(getContext(), "Cannot submit empty answer", Toast.LENGTH_SHORT).show();
//            return;
//        }



        boolean isCorrect = editText.getText().toString().trim().equalsIgnoreCase(answer);

        listener.onResultSent(1);
        return;

//        if(isCorrect){
//            listener.onResultSent(1);
//        } else {
//            listener.onResultSent(-1);
//        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (RoundFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}