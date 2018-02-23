package dscont.com.dscont.pe.appcryptocurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dscont.com.dscont.pe.appcryptocurrency.Clases_Adicionales.ApiService;
import dscont.com.dscont.pe.appcryptocurrency.Clases_Adicionales.RVCurrency_Adapter;
import dscont.com.dscont.pe.appcryptocurrency.Clases_Adicionales.cCurrency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    List<cCurrency> Currencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Currencies = new ArrayList<>();

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        ApiService api = retrofit.create(ApiService.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<cCurrency>> call = api.getcurrencydetails();

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<cCurrency>>() {
            @Override
            public void onResponse(Call<List<cCurrency>> call, Response<List<cCurrency>> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                List<cCurrency> currencyList = response.body();

                RecyclerView rv_currency = (RecyclerView)findViewById(R.id.rv_currency);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                rv_currency.setLayoutManager(layoutManager);
                rv_currency.setHasFixedSize(true);
                RVCurrency_Adapter adapter = new RVCurrency_Adapter(currencyList, MainActivity.this);
                rv_currency.setAdapter(adapter);

                //Currencies = currencyList;
                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<List<cCurrency>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



/*
        for(int i=0;i<Currencies.size();i++) {
            cCurrency c = new cCurrency();
        }
*/

    }
}
