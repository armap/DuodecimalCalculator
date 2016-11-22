package com.arnaumarti.calculator.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.arnaumarti.calculator.R;
import com.arnaumarti.calculator.adapters.ListRankingAdapter;
import com.arnaumarti.calculator.model.UserPoints;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@l ink RankingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {

    public RankingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RankingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);
        final ListView listViewRanking = (ListView) rootView.findViewById(R.id.listViewRanking);

        new AsyncTask<Void, Void, ArrayList<UserPoints>>() {
            @Override
            protected ArrayList<UserPoints> doInBackground(Void... voids) {
                return getListUserPoints();
            }

            @Override
            protected void onPostExecute(ArrayList<UserPoints> listTable) {
                listViewRanking.setAdapter(new ListRankingAdapter(getActivity(), listTable));
                return ;
            }
        }.execute();

        return rootView;
    }

    @Nullable
    private ArrayList<UserPoints> getListUserPoints() {
        ArrayList<UserPoints> listTable = null;
        try {
            JSONArray jsonArray = new JSONArray(extractJsonString());
            listTable = new ArrayList<>();
            UserPoints userRow;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String points = jsonObject.getString("points");

                //Add your values in your `ArrayList` as below:
                userRow = new UserPoints(name,points);
                listTable.add(userRow);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listTable;
    }

    private String extractJsonString() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.ranking);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        return writer.toString();
    }
}
