package mohammed.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityJoke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_joke,new JokeActivityFragment()).commit();

    }
}
