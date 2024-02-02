package com.example.aula_26_01_2024;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dois#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dois extends Fragment {
    Button btprev;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dois() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dois.
     */
    // TODO: Rename and change types and number of parameters
    public static Dois newInstance(String param1, String param2) {
        Dois fragment = new Dois();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dois, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btprev = view.findViewById(R.id.bt_prev_dois);
        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction t = getParentFragmentManager().beginTransaction();
                Um um = Um.newInstance("", "");
                t.replace(R.id.fragmentContainerView, um);
                t.addToBackStack(null);
                t.commit();
            }
        });
        txtpares = view.findViewById(R.id.txt_pares_dois);
        progressBar = view.findViewById(R.id.progressbar_pares_dois);
        btpares = view.findViewById(R.id.bt_btpares_dois);
        btpares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask = new MyAsyncTask(Dois.this);
                myAsyncTask.execute(100);
            }
        });
    }

    TextView txtpares;
    ProgressBar progressBar;
    Button btpares;

    class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
        Dois dois;

        public MyAsyncTask(Dois d) {
            this.dois = d;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dois.progressBar.setVisibility(View.VISIBLE);
            dois.progressBar.setProgress(0);
            dois.txtpares.setText("Inicio");
            dois.btpares.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dois.txtpares.setText(s);
            dois.progressBar.setVisibility(View.INVISIBLE);
            dois.btpares.setEnabled(true);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dois.progressBar.setProgress(values[1]);
            dois.txtpares.setText(String.valueOf(values[0]));
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int i, total, percentagem;
            for (i = 0, total = 0; total < integers[0]; i++) {
                if (Biblioteca.EPar(i)) {
                    total++;
                    percentagem = (total * 100) / integers[0];
                    publishProgress(i, percentagem);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return "Fim";
        }
    }
}