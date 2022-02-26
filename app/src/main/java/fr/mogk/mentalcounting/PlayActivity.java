package fr.mogk.mentalcounting;

import android.app.ActionBar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Rank rank = Rank.GOLD;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Button btn = findViewById(R.id.PlayToMenu);
        btn.setText(btn.getText()+" →");
        btn.setOnClickListener(view -> backToMenu(view));
        setTitle(R.string.button_play);
        TextView tv = findViewById(R.id.operation_txt);
        Operation op = new Operation(rank);
        tv.setText(op.toString());
        reponse(false);
    }

    public void backToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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
                finish();
            case R.id.menu_scores_reset:
                //code pour reset les scores
            default:
                //rien
        }
        return super.onOptionsItemSelected(item);
    }

    //Gère le texte et la couleur en fonction de la réponse (Correct ou Non)
    public void reponse(boolean isGood){
        TextView tv = findViewById(R.id.msg_txt);
        if(isGood){
            tv.setTextColor(getResources().getColor(R.color.succes, this.getTheme()));
            tv.setText(getResources().getString(R.string.success));
            //set le score
        }
        else{
            tv.setTextColor(getResources().getColor(R.color.failure, this.getTheme()));
            tv.setText(getResources().getString(R.string.failure));
            //set le score
        }
    }
}