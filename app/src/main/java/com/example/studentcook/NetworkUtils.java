package com.example.studentcook;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String RECIPE_BASE_URL = "http://www.recipepuppy.com/api/";
    private static final String RECIPE_OPTIONS_URL = "i";
    private static final String RECIPE_NAME_URL = "q";
    private static final String RECIPE_END_URL = "p";

    // возвращаем URL
    public static URL generateURL (String component) {
        // используем Uri для построения html-строки запроса
        // Uri позволяет нам строить строку запроса, при это
        // подстановку управляющих симовлов типа =,?,& он берет на себя
        // http://www.recipepuppy.com/api/?i=onions,garlic&q=  omelet  &p=3
        Uri buildUri = Uri.parse(RECIPE_BASE_URL).buildUpon()
                .appendQueryParameter(RECIPE_OPTIONS_URL,"")
                .appendQueryParameter(RECIPE_NAME_URL, component)
                .appendQueryParameter(RECIPE_END_URL, "1")
                .build();

        // конвертируем Uri в URL
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    // возвращаем JSON-объект из нашего URL
    public static String getResponseFromUrl(URL url) throws IOException {
        // создаем подключение по URL - url.openConnection()
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // создаем входной поток нашего подключения по URL
            InputStream is = urlConnection.getInputStream();

            // ложим этот поток в сканнер потоков
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if(hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

