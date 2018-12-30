package cz.utb.fai.translator.api;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    public static final String BASE_URL = "http://api.mymemory.translated.net/";
    private static Retrofit retrofit = null;
    private static OkHttpClient.Builder getHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// nastavi pozadovanou uroven levelu
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// nastavi dalsi interceptory …
        httpClient.addInterceptor(logging); // <-- this is the important line!
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        return httpClient;
    }
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient().build())
                    .build();
        }
        return retrofit;
    }
}

