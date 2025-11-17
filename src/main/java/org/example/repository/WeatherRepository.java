package org.example.repository;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.repository.entity.WeatherBean;

import java.io.IOException;

public class WeatherRepository {


    public static void main(String[] args) {
        try {
            var res = WeatherRepository.loadWeather("Nice");
            System.out.printf("Il fait %s° à %s avec un vent de %skm/h%n",
                    res.getMain().getTemp(), res.getName(), res.getWind().getSpeed()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WeatherBean loadWeather(String city) throws Exception {
        //Requete
        Thread.sleep(2000);
        var json = sendGet("https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q=" + city);
        //Parsing avec GSON
        WeatherBean res = new Gson().fromJson(json, WeatherBean.class);
        //Retourner le resultat
        return res;
    }

    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
