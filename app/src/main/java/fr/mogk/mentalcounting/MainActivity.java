package fr.mogk.mentalcounting;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_play).setOnClickListener(view -> openPlay(view));
        findViewById(R.id.button_score).setOnClickListener(view -> openScore(view));
    }

    public void openPlay(View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void openScore(View view){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

}