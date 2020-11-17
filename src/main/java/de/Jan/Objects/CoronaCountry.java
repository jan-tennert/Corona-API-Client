package de.Jan.Objects;

import de.Jan.CoronaAPIClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class CoronaCountry {

    private final JSONArray recoveredAll;
    private final JSONArray confirmedAll;
    private final JSONArray deathsAll;
    private final String isoCode;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public CoronaCountry(String isoCode) throws CoronaAPIClient.CoronaAPIClientError {
        this.isoCode = isoCode;
        try {
            recoveredAll = new JSONArray(CoronaAPIClient.getJSONResult(String.format("https://api.covid19api.com/total/country/%s/status/recovered", isoCode)));
            confirmedAll = new JSONArray(CoronaAPIClient.getJSONResult(String.format("https://api.covid19api.com/total/country/%s/status/confirmed", isoCode)));
            deathsAll = new JSONArray(CoronaAPIClient.getJSONResult(String.format("https://api.covid19api.com/total/country/%s/status/deaths", isoCode)));
            recoveredAll.get(0);
            confirmedAll.get(0);
            confirmedAll.get(0);
        } catch(JSONException e) {
            e.printStackTrace();
            throw new CoronaAPIClient.CoronaAPIClientError("Country not found");
        }
    }

    @NotNull
    public List<CoronaDay> getAllRecoveredDays() throws ParseException {
        List<CoronaDay> days = new ArrayList<>();
        for (Object o : recoveredAll) {
            JSONObject ob = ((JSONObject) o);
            CoronaDay coronaDay = new CoronaDay(format.parse(ob.getString("Date")), ob.getInt("Cases"), CoronaAPIClient.Type.RECOVERED);
            days.add(coronaDay);
        }
        return days;
    }

    @NotNull
    public List<CoronaDay> getAllConfirmedDays() throws ParseException {
        List<CoronaDay> days = new ArrayList<>();
        for (Object o : confirmedAll) {
            JSONObject ob = ((JSONObject) o);
            CoronaDay coronaDay = new CoronaDay(format.parse(ob.getString("Date")), ob.getInt("Cases"), CoronaAPIClient.Type.CONFIRMED);
            days.add(coronaDay);
        }
        return days;
    }

    @NotNull
    public List<CoronaDay> getAllDeathDays() throws ParseException {
        List<CoronaDay> days = new ArrayList<>();
        for (Object o : deathsAll) {
            JSONObject ob = ((JSONObject) o);
            CoronaDay coronaDay = new CoronaDay(format.parse(ob.getString("Date")), ob.getInt("Cases"), CoronaAPIClient.Type.DEATH);
            days.add(coronaDay);
        }
        return days;
    }

    public CoronaDay getTotalRecovered() throws ParseException {
        JSONObject o = recoveredAll.getJSONObject(recoveredAll.length() - 1);
        return new CoronaDay(format.parse(o.getString("Date")), o.getInt("Cases"), CoronaAPIClient.Type.RECOVERED);
    }

    public CoronaDay getTotalConfirmed() throws ParseException {
        JSONObject o = confirmedAll.getJSONObject(confirmedAll.length() - 1);
        return new CoronaDay(format.parse(o.getString("Date")), o.getInt("Cases"), CoronaAPIClient.Type.CONFIRMED);
    }

    public CoronaDay getTotalDeaths() throws ParseException {
        JSONObject o = deathsAll.getJSONObject(deathsAll.length() - 1);
        return new CoronaDay(format.parse(o.getString("Date")), o.getInt("Cases"), CoronaAPIClient.Type.DEATH);
    }

    public CoronaDay getNewRecoveredForToday() throws ParseException {
        JSONObject o = recoveredAll.getJSONObject(recoveredAll.length() - 1);
        JSONObject o2 = recoveredAll.getJSONObject(recoveredAll.length() - 2);
        return new CoronaDay(format.parse(o.getString("Date")), o.getInt("Cases") - o2.getInt("Cases"), CoronaAPIClient.Type.RECOVERED);
    }

    public CoronaDay getNewConfirmedForToday() throws ParseException {
        JSONObject o = confirmedAll.getJSONObject(confirmedAll.length() - 1);
        JSONObject o2 = confirmedAll.getJSONObject(confirmedAll.length() - 2);
        return new CoronaDay(format.parse(o.getString("Date")), o.getInt("Cases") - o2.getInt("Cases"), CoronaAPIClient.Type.CONFIRMED);
    }

    public CoronaDay getNewDeathsForToday() throws ParseException {
        JSONObject o = deathsAll.getJSONObject(deathsAll.length() - 1);
        JSONObject o2 = deathsAll.getJSONObject(deathsAll.length() - 2);
        return new CoronaDay(format.parse(o.getString("Date")), o.getInt("Cases") - o2.getInt("Cases"), CoronaAPIClient.Type.DEATH);
    }

    public CoronaDay getRecoveredForDate(Date date) throws ParseException {
        for (Object o : recoveredAll) {
            JSONObject ob = (JSONObject) o;
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date);
            cal2.setTime(format.parse(ob.getString("Date")));
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(sameDay) {
                return new CoronaDay(date, ob.getInt("Cases"), CoronaAPIClient.Type.RECOVERED);
            }
        }
        return null;
    }

    public CoronaDay getConfirmedForDate(Date date) throws ParseException {
        for (Object o : confirmedAll) {
            JSONObject ob = (JSONObject) o;
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date);
            cal2.setTime(format.parse(ob.getString("Date")));
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(sameDay) {
                return new CoronaDay(date, ob.getInt("Cases"), CoronaAPIClient.Type.CONFIRMED);
            }
        }
        return null;
    }

    public CoronaDay getDeathsForDate(Date date) throws ParseException {
        for (Object o : deathsAll) {
            JSONObject ob = (JSONObject) o;
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date);
            cal2.setTime(format.parse(ob.getString("Date")));
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            if(sameDay) {
                return new CoronaDay(date, ob.getInt("Cases"), CoronaAPIClient.Type.DEATH);
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "ISO Code: " + isoCode;
    }
}
