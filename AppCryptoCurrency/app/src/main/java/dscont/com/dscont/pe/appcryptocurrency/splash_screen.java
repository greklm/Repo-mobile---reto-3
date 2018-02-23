package dscont.com.dscont.pe.appcryptocurrency;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Picasso.with(splash_screen.this)
                .load("https://cdn.pixabay.com/photo/2017/01/20/21/00/background-1995912_960_720.jpg")
                .into((ImageView) findViewById(R.id.imageView));
        //Mostrar el splash screen con un delay de 4 segundos
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
