package de.Jan.Objects;

import de.Jan.CoronaAPIClient;
import org.json.JSONObject;

public class WorldWideStats {

    private JSONObject stats;

    public WorldWideStats() {
        stats = new JSONObject(CoronaAPIClient.getJSONResult("https://api.covid19api.com/world/total"));
    }

    public int getTotalRecovered() {
        return stats.getInt("TotalRecovered");
    }

    public int getTotalConfirmed() {
        return stats.getInt("TotalConfirmed");
    }

    public int getTotalDeaths() {
        return stats.getInt("TotalDeaths");
    }

}
