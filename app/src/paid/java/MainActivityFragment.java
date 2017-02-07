import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.OnJokeReceivedListener;
import com.udacity.gradle.builditbigger.R;

import mohammed.jokedisplay.ActivityJoke;

public class MainActivityFragment extends Fragment implements OnJokeReceivedListener {

    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.tell_joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startJokeActivity();
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        return root;
    }

    @Override
    public void onReceived(String joke) {
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), ActivityJoke.class);
        intent.putExtra("myjoke", joke);
        startActivity(intent);
    }

    public void startJokeActivity(){
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(getContext()).execute(this);
    }
}
