package fr.mogk.mentalcounting;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
        btn.setText(btn.getText()+" →");
        btn.setOnClickListener(view -> backToMenu(view));
        setTitle(R.string.button_play);
        generateCalcul();
        initBoutons();
        Button btnValid = findViewById(R.id.button_submit);
        btnValid.setOnClickListener(view -> verif(view));

    }

    public void generateCalcul(){
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
                finish();
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
        TextView tv1 = findViewById(R.id.msg_txt);
        tv1.setVisibility(View.INVISIBLE);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saisie(View view){
        TextView tv = findViewById(R.id.textviewInput);
        Button btn = (Button) view;
        tv.setText((String) tv.getText()+btn.getText());
        TextView tv1 = findViewById(R.id.msg_txt);
        tv1.setVisibility(View.INVISIBLE);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect vbEffect = VibrationEffect.createOneShot(100,1);
        vibrator.vibrate(vbEffect);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void verif(View view){
        TextView tv1 = findViewById(R.id.textviewInput);
        String txt = (String) tv1.getText();
        boolean result;
        try {
            result = (Double.parseDouble(txt)) == (op.getReponse());
        }
        catch (Exception e){
            result = false;
        }
        TextView tv = findViewById(R.id.msg_txt);
        final MediaPlayer mpFail = MediaPlayer.create(this, R.raw.failure);
        final MediaPlayer mpSuccess = MediaPlayer.create(this, R.raw.success);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect vbEffect;
        if(result){
            mpSuccess.start();
            tv.setTextColor(getResources().getColor(R.color.succes, this.getTheme()));
            tv.setText(getResources().getString(R.string.success));
            vbEffect = VibrationEffect.createOneShot(200,2);
            //set le score
        }
        else{
            mpFail.start();
            tv.setTextColor(getResources().getColor(R.color.failure, this.getTheme()));
            tv.setText(getResources().getString(R.string.failure));
            vbEffect = VibrationEffect.createOneShot(500,5);
            //set le score
        }
        vibrator.vibrate(vbEffect);
        tv.setVisibility(View.VISIBLE);
        //Toast.makeText(this, "R attendue :"+op.getReponse()+", R donnée : "+txt, Toast.LENGTH_SHORT).show();
        tv1.setText("");
        generateCalcul();
    }
}