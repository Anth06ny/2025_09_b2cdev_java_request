package org.example.repository;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.repository.entity.WeatherBean;

import java.io.IOException;

public class WeatherRepository {

    public static void main(String[] args) {
        try {
            var res =  WeatherRepository.loadWeather("Nice");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WeatherBean loadWeather(String city) throws Exception {
           //Requete
           //Parsing avec GSON
           //Retourner le resultat
        return null;
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
