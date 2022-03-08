package fr.mogk.mentalcounting;

import android.app.ActionBar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayActivity extends AppCompatActivity {

    private Operation op;
    private Rank rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rank = Rank.BRONZE;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Button btn = findViewById(R.id.PlayToMenu);
        TextView tv1 = findViewById(R.id.msg_txt);
        //tv1.setVisibility(View.INVISIBLE);
        btn.setText(btn.getText()+" →");
        btn.setOnClickListener(view -> backToMenu(view));
        setTitle(R.string.button_play);
        generateCalcul();
        initBoutons();
        Button btnValid = findViewById(R.id.button_submit);
        btnValid.setOnClickListener(view -> verif(view));

    }

    public void generateCalcul(){
        System.out.println("je suis dans gen calc");
        TextView tv = findViewById(R.id.operation_txt);
        op = new Operation(rank);
        tv.setText(op.toString());
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

    public void initBoutons(){
        ArrayList<Button> listeBoutons = new ArrayList<>();
        listeBoutons.add(findViewById(R.id.button0));
        listeBoutons.add(findViewById(R.id.button1));
        listeBoutons.add(findViewById(R.id.button2));
        listeBoutons.add(findViewById(R.id.button3));
        listeBoutons.add(findViewById(R.id.button4));
        listeBoutons.add(findViewById(R.id.button5));
        listeBoutons.add(findViewById(R.id.button6));
        listeBoutons.add(findViewById(R.id.button7));
        listeBoutons.add(findViewById(R.id.button8));
        listeBoutons.add(findViewById(R.id.button9));
        listeBoutons.add(findViewById(R.id.button_comma));
        for(Button b:listeBoutons){
            b.setOnClickListener(view -> saisie(view));
        }
        Button btnDel = findViewById(R.id.button_del);
        btnDel.setOnClickListener(view -> deleteInput(view));


    }

    public void saisie(View view){
        TextView tv = findViewById(R.id.textviewInput);
        Button btn = (Button) view;
        tv.setText((String) tv.getText()+btn.getText());
        TextView tv1 = findViewById(R.id.msg_txt);
        tv1.setVisibility(View.INVISIBLE);
    }

    public void deleteInput(View view){
        TextView tv = findViewById(R.id.textviewInput);
        String txt = (String) tv.getText();
        int lengh = txt.length();
        if(lengh>0){
            String txt1 = txt.substring(0,lengh-1);
            tv.setText(txt1);
        }
    }

    public void verif(View view){
        TextView tv1 = findViewById(R.id.textviewInput);
        String txt = (String) tv1.getText();
        boolean result = (Double.parseDouble(txt)) == (op.getReponse());
        TextView tv = findViewById(R.id.msg_txt);
        if(result){
            tv.setTextColor(getResources().getColor(R.color.succes, this.getTheme()));
            tv.setText(getResources().getString(R.string.success));
            //set le score
        }
        else{
            tv.setTextColor(getResources().getColor(R.color.failure, this.getTheme()));
            tv.setText(getResources().getString(R.string.failure));
            //set le score
        }
        tv.setVisibility(View.VISIBLE);
        Toast.makeText(this, "R attendue :"+op.getReponse()+", R donnée : "+txt, Toast.LENGTH_SHORT).show();
        tv1.setText("");
        System.out.println("je sort de verif");
        generateCalcul();
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