package com.arnaumarti.calculator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arnaumarti.calculator.R;
import com.arnaumarti.calculator.helpers.MathUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@ link EasyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EasyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EasyFragment extends Fragment {

    private String resultB12;
    private TextView txtViewOperation;
    private TextView txtViewResult;
    private Button btnShowReset;

    private boolean isInShowSum = true;

    public EasyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EasyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EasyFragment newInstance() {
        EasyFragment fragment = new EasyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_easy, container, false);

        txtViewOperation = (TextView) rootView.findViewById(R.id.txtViewOperation);
        txtViewResult = (TextView) rootView.findViewById(R.id.txtViewResult);
        btnShowReset = (Button) rootView.findViewById(R.id.btnShowReset);



        btnShowReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInShowSum){

                    showSum();
                }else {

                    reset();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        txtViewResult.setText(R.string.hidden_result);
        txtViewOperation.setText(generateNewOperation());
    }

    private void showSum(){
        isInShowSum = false;
        txtViewResult.setText(R.string.hidden_result);
        txtViewOperation.setText(generateNewOperation());
        btnShowReset.setText(R.string.btn_show_result);
    }

    private void reset(){
        isInShowSum = true;
        txtViewResult.setText(resultB12);
        btnShowReset.setText(R.string.btn_reset);
    }

    private String generateNewOperation() {
        int firstNum = MathUtil.randInt(0,11);
        int secondNum = MathUtil.randInt(0,11);
        int result = firstNum + secondNum;

        String firstNumB12 = MathUtil.convertIntB10toStringB12ChiEpsilon(firstNum);
        String secondNumB12 = MathUtil.convertIntB10toStringB12ChiEpsilon(secondNum);
        resultB12 = MathUtil.convertIntB10toStringB12ChiEpsilon(result);

        return firstNumB12+" + "+secondNumB12;
    }

}
