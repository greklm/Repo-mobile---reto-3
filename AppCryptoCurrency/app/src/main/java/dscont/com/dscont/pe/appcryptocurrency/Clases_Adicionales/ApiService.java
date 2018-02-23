package dscont.com.dscont.pe.appcryptocurrency.Clases_Adicionales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Carla-PC on 22/02/2018.
 */

public interface ApiService {
    String BASE_URL = "https://api.coinmarketcap.com/v1/";
    @GET("ticker/")
    Call<List<cCurrency>> getcurrencydetails();
}
