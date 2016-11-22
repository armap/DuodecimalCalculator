package com.arnaumarti.calculator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arnaumarti.calculator.R;
import com.arnaumarti.calculator.helpers.MathUtil;

import static com.arnaumarti.calculator.helpers.MathUtil.convertIntB10toStringB12ChiEpsilon;
import static com.arnaumarti.calculator.helpers.MathUtil.randInt;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@ link HardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HardFragment extends Fragment {

    private static GridView gridViewDuodecimalKeyword;
    private static LinearLayout liLayout1stNumber;
    private static LinearLayout liLayout2ndNumber;
    private static LinearLayout liLayoutResultNumber;
    private static Button btnNew;

    private final static int BASE12 = 12;
    //max digit B10 = 9 -> max digit B12 = 11=b=\u03B5=ε=epsilon
    private static int maxDigitIntBase12;// = BASE12 - 1;// = 11
    private static String maxDigitStringBase12;// = MathUtil.convertIntB10toStringB12(maxDigitIntBase12);// = b
    private static int maxNumDigitsForNumbers;// = getResources().getInteger(R.integer.maxNumDigitsForNumbers);// = 7
    private static int maxNumDigitsForResult;// = maxNumDigitsForNumbers + 1;
    private static String maxNumStringB12;// = MathUtil.getMaxNumStringB12(maxDigitStringBase12, maxNumDigitsForNumbers);// = bbbbbbb
    private static int maxNumIntB12;// = MathUtil.convertStringB12ToIntB10(maxNumStringB12);// = 35831807

    private static String firstNumB12;
    private static String secondNumB12;
    private static String resultB12;

    private static TextView mCurrentViewInResult;
    private static String mCurrentDigitResultStr;
    private static int mCurrentViewPositionInResult;

    public HardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HardFragment newInstance() {
        HardFragment fragment = new HardFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_hard, container, false);

        findViewsByIds(rootView);

        maxDigitIntBase12 = BASE12 - 1;// = 11
        maxDigitStringBase12 = MathUtil.convertIntB10toStringB12(maxDigitIntBase12);// = b
        maxNumDigitsForNumbers = getResources().getInteger(R.integer.maxNumDigitsForNumbers);// = 7
        maxNumDigitsForResult = maxNumDigitsForNumbers + 1;
        maxNumStringB12 = MathUtil.getMaxNumStringB12(maxDigitStringBase12, maxNumDigitsForNumbers);// = bbbbbbb
        maxNumIntB12 = MathUtil.convertStringB12ToIntB10(maxNumStringB12);// = 35831807

        addDigitViewsToNumberLayout(liLayout1stNumber);
        addDigitViewsToNumberLayout(liLayout2ndNumber);

        addViewListenersToResultLayout(liLayoutResultNumber);

        setGridViewDuodecimalKeyboard();

        setListeners();

        generateNewOperation();

        return rootView;
    }

    private void setGridViewDuodecimalKeyboard() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.item_gridview, android.R.id.text1, createArrStrDigitsKeyword());
        gridViewDuodecimalKeyword.setAdapter(adapter);
        gridViewDuodecimalKeyword.setVisibility(View.GONE);
    }

    private void setListeners() {
        gridViewDuodecimalKeyword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String pressedDigit = (String) gridViewDuodecimalKeyword.getItemAtPosition(position);

                mCurrentViewInResult.setText(pressedDigit);


                validatePressedDigitIsCorrect(pressedDigit);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridViewDuodecimalKeyword.setVisibility(View.GONE);

                generateNewOperation();
            }
        });
    }

    private void validatePressedDigitIsCorrect(String pressedDigit) {
        int numDigitsCurrentResult = resultB12.length();
        int currentDigitSelectedPositionInResult;

        int difference = maxNumDigitsForResult - numDigitsCurrentResult;
        currentDigitSelectedPositionInResult = mCurrentViewPositionInResult - difference;

        mCurrentDigitResultStr = String.valueOf(resultB12.charAt(currentDigitSelectedPositionInResult));

        if (pressedDigit.equals(mCurrentDigitResultStr)) {
            gridViewDuodecimalKeyword.setVisibility(View.GONE);
        }
    }

    private void generateNewOperation() {
        int firstNum = randInt(0, maxNumIntB12);
        Log.i("firstNum: ", String.valueOf(firstNum));
        int secondNum = randInt(0, maxNumIntB12);
        Log.i("secondNum: ", String.valueOf(secondNum));
        int result = firstNum + secondNum;
        Log.i("result: ", String.valueOf(result));

        firstNumB12 = convertIntB10toStringB12ChiEpsilon(firstNum);
        Log.i("firstNumB12: ", firstNumB12);

        secondNumB12 = convertIntB10toStringB12ChiEpsilon(secondNum);
        Log.i("secondNumB12: ", secondNumB12);

        resultB12 = convertIntB10toStringB12ChiEpsilon(result);
        Log.i("resultB12: ", resultB12);

        updateNewNumber(liLayout1stNumber, firstNumB12);
        updateNewNumber(liLayout2ndNumber, secondNumB12);
        updateResultWithEmptyViews(liLayoutResultNumber, resultB12);
    }

    private void findViewsByIds(View rootView) {
        liLayout1stNumber = (LinearLayout) rootView.findViewById(R.id.liLayout1stNumber);
        liLayout2ndNumber = (LinearLayout) rootView.findViewById(R.id.liLayout2ndNumber);
        liLayoutResultNumber = (LinearLayout) rootView.findViewById(R.id.liLayoutResultNumber);
        gridViewDuodecimalKeyword = (GridView) rootView.findViewById(R.id.gridViewDuodecimalKeyword);
        btnNew = (Button) rootView.findViewById(R.id.btnNew);
    }


    @NonNull
    private String[] createArrStrDigitsKeyword() {
        // 0 1 2 3 4 5 6 7 8 9 10 11
        // replace 10-chi i 11-epsilon
        // move 0 to end of list
        String[] arrStrDigitsKeyword = new String[BASE12];
        int numToAdd = 0;
        int positionToAdd = 0;
        int finalPosition = arrStrDigitsKeyword.length - 1;
        arrStrDigitsKeyword[finalPosition] = convertIntB10toStringB12ChiEpsilon(numToAdd);
        numToAdd++;
        while (positionToAdd < finalPosition) {
            arrStrDigitsKeyword[positionToAdd] = convertIntB10toStringB12ChiEpsilon(numToAdd);
            numToAdd++;
            positionToAdd++;
        }
        return arrStrDigitsKeyword;
    }

    private void updateNewNumber(LinearLayout liLayoutNumber, String numB12) {
        int numDigitViewsInLayout = liLayoutNumber.getChildCount();
        int numDigitsCurrentNumber = numB12.length();
        int charIndex = 0;
        for (int i = 0; i < numDigitViewsInLayout; i++) {
            TextView txtViewDigit = (TextView) liLayoutNumber.getChildAt(i);
            if (numDigitsCurrentNumber < numDigitViewsInLayout) {
                txtViewDigit.setText("");
                numDigitsCurrentNumber++;
            } else {
                txtViewDigit.setText(String.valueOf(numB12.charAt(charIndex)));
                charIndex++;
            }
        }
    }

    private void updateResultWithEmptyViews(LinearLayout liLayoutResult, String resultB12) {
        int numDigitViewsInLayout = liLayoutResult.getChildCount();
        int numDigitsCurrentNumber = resultB12.length();
        for (int i = 0; i < numDigitViewsInLayout; i++) {
            TextView txtViewResult = (TextView) liLayoutResult.getChildAt(i);
            txtViewResult.setText(getString(R.string.emptyDigitResult));
            if (numDigitsCurrentNumber < numDigitViewsInLayout) {
                txtViewResult.setClickable(false);
                numDigitsCurrentNumber++;
            } else {
                txtViewResult.setClickable(true);
            }
        }
    }

    private void addViewListenersToResultLayout(LinearLayout liLayoutResultNumber) {
        for (int i = 0; i < maxNumDigitsForResult; i++) {
            TextView txtViewResult = createDigitTextView();
            txtViewResult.setPadding(0,20,0,20);
            txtViewResult.setTag(i);
            txtViewResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gridViewDuodecimalKeyword.setVisibility(View.VISIBLE);
                    mCurrentViewInResult = (TextView) view;
                    mCurrentViewPositionInResult = (int) view.getTag();
                }
            });
            liLayoutResultNumber.addView(txtViewResult);
        }
    }

    private void addDigitViewsToNumberLayout(LinearLayout liLayoutNumber) {
        for (int i = 0; i < maxNumDigitsForNumbers; i++) {
            TextView txtViewDigit = createDigitTextView();
            liLayoutNumber.addView(txtViewDigit);
        }
    }

    @NonNull
    private TextView createDigitTextView() {
//        TextView txtViewDigit = new TextView(getActivity(), null, R.attr.attrTxtViewDigitStyle);
        TextView txtViewDigit = new TextView(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        txtViewDigit.setLayoutParams(params);
        txtViewDigit.setGravity(Gravity.CENTER);
        txtViewDigit.setTextSize(17);
        return txtViewDigit;
    }
}
