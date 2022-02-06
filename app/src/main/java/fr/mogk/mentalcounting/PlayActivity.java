package fr.mogk.mentalcounting;

import android.app.ActionBar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        //TextView tv = findViewById(R.id.msg_txt);
        //tv.setTextColor(getResources().getColor(R.color.succes)); // test changement couleur, sera utile pour plus tard
        Button btn = findViewById(R.id.PlayToMenu);
        btn.setText(btn.getText()+" →");
        btn.setOnClickListener(view -> backToMenu(view));
        setTitle(R.string.button_play);
    }

    public void backToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_scores:
                Intent intent = new Intent(this, ScoreActivity.class);
                startActivity(intent);
            case R.id.menu_scores_reset:
                //code pour reset les scores
            default:
                //rien
        }
        return super.onOptionsItemSelected(item);
    }
}