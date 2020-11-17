package de.Jan;

import de.Jan.Objects.WorldWideStats;
import de.Jan.Objects.CoronaCountry;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.*;

public class CoronaAPIClient {

    private static OkHttpClient client = new OkHttpClient();

    public static OkHttpClient getClient() {
        return client;
    }

    public CoronaCountry getCountry(String isoCode) throws CoronaAPIClientError {
        return new CoronaCountry(isoCode);
    }

    public List<CoronaCountry> findCountry(String query) {
        List<CoronaCountry> countries = new ArrayList<>();
        Map<String, String> isoCodes = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            isoCodes.put(l.getDisplayCountry(), iso);
        }
        isoCodes.forEach((name, iso) -> {
            name = name.toLowerCase();
            String search = query.toLowerCase();
            if(name.contains(search)) {
                CoronaCountry country = null;
                try {
                    country = new CoronaCountry(iso);
                    countries.add(country);
                } catch (CoronaAPIClientError coronaAPIClientError) {

                }
            }
        });
        return countries;
    }

    public WorldWideStats getWorldWideStats() { return new WorldWideStats(); }

    public static String getJSONResult(String url) {
        Request request = (new Request.Builder()).url(url).build();
        try {
            Response response = client.newCall(request).execute();
            try {
                String str = response.body().string();
                if (response != null)
                    response.close();
                return str;
            } catch (Throwable throwable) {
                if (response != null)
                    try {
                        response.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    public enum Type {
        CONFIRMED, DEATH, RECOVERED
    }

    public static class CoronaAPIClientError extends Exception {
        public CoronaAPIClientError(String error) {
            super(error);
        }
    }
}
