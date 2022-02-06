package fr.mogk.mentalcounting;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Button btn = findViewById(R.id.ScoreToMenu);
        btn.setText("← "+btn.getText());
        btn.setOnClickListener(view -> backToMenu(view));
        setTitle(R.string.button_score);
    }

    public void backToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}