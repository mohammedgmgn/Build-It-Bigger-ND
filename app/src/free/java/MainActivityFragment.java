import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.OnJokeReceivedListener;
import com.udacity.gradle.builditbigger.R;

import mohammed.jokedisplay.ActivityJoke;


public class MainActivityFragment extends Fragment implements OnJokeReceivedListener {
    private InterstitialAd mInterstitialAd;
    private ProgressBar progressBar;
    private String mJoke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });

        requestNewInterstitial();
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        Button button = (Button) root.findViewById(R.id.tell_joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });

        return root;

    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("AA37CAB351D645D2B79AC6AD2D158791")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }



    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(),ActivityJoke.class);
        intent.putExtra("myjoke", mJoke);
        startActivity(intent);
    }

    public void fetchJoke(){
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(getContext()).execute(this);
    }


    @Override
    public void onReceived(String joke) {
        progressBar.setVisibility(View.INVISIBLE);
        mJoke = joke;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            startJokeActivity();
        } else {
            startJokeActivity();
        }

    }
}

