package org.example.mexicanfood;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MexicanRepository {

    public static void main(String[] args) {
        try {
            var res = MexicanRepository.loadMexicanfood("5");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MexicanResultBean loadMexicanfood(String id) throws Exception {
        //Requete
        var json = sendGet("https://the-mexican-food-db.p.rapidapi.com/" + id);
        //Parsing avec GSON
        MexicanResultBean res = new Gson().fromJson(json, MexicanResultBean.class);
        //Retourner le resultat
        return res;
    }

    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url)
                .addHeader("x-rapidapi-key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
                .addHeader("x-rapidapi-host", "the-mexican-food-db.p.rapidapi.com")
                .build();

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
