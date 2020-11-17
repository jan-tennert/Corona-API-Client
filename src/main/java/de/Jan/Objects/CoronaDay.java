package de.Jan.Objects;

import de.Jan.CoronaAPIClient;

import java.util.Date;

public class CoronaDay {

    private Date date;
    private int cases;
    private CoronaAPIClient.Type type;

    public CoronaDay(Date date, int cases, CoronaAPIClient.Type type) {
        this.date = date;
        this.cases = cases;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public int getCases() {
        return cases;
    }

    public CoronaAPIClient.Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Date: " + date + "\nCases: " + cases + "\nType: " + type;
    }
}
