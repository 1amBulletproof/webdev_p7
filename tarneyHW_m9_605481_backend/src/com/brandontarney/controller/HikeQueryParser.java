/** HikeQueryParser Class
 * <p>
 * Contains static methods to extract parameters from an HTTP form query</p>
 *
 * @author Brandon Tarney
 * @since 6/28/2017
 */
package com.brandontarney.controller;

import com.brandontarney.bookingrate.Rates.HIKE;

public class HikeQueryParser {

    public static HIKE getHike(String queryString) throws BadQueryStringException {
        int queryStringOrder = 3;
        HIKE hikeEnum;

        String[] queryValues = queryString.split(":");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for hike)");
        }

        int hikeEnumStr = Integer.parseInt(queryValues[queryStringOrder]);

        switch (hikeEnumStr) {
            case 0:
                hikeEnum = HIKE.HELLROARING;
                break;
            case 1:
                hikeEnum = HIKE.GARDINER;
                break;
            case 2:
                hikeEnum = HIKE.BEATEN;
                break;
            default:
                throw new BadQueryStringException("Invalid hike value");
        }
        return hikeEnum;
    }

    public static int getYear(String queryString) throws BadQueryStringException {
        int queryStringOrder = 0;
        int hikeYear;

        String[] queryValues = queryString.split(":");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for year)");
        }

        String hikeYearString = queryValues[queryStringOrder];

        try {
            hikeYear = Integer.parseInt(hikeYearString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid year value");
        }

        return hikeYear;
    }

    public static int getMonth(String queryString) throws BadQueryStringException {
        int queryStringOrder = 1;
        int hikeMonth;

        String[] queryValues = queryString.split(":");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for month)");
        }

        String hikeMonthString = queryValues[queryStringOrder];

        try {
            hikeMonth = Integer.parseInt(hikeMonthString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid month value");
        }

        return hikeMonth;
    }

    public static int getDay(String queryString) throws BadQueryStringException {
        int queryStringOrder = 2;
        int hikeDay;

        String[] queryValues = queryString.split(":");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for day)");
        }

        String hikeDayString = queryValues[queryStringOrder];

        try {
            hikeDay = Integer.parseInt(hikeDayString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid day value");
        }

        return hikeDay;
    }

    public static int getDuration(String queryString) throws BadQueryStringException {
        int queryStringOrder = 4;
        int hikeDuration;

        String[] queryValues = queryString.split(":");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for duration)");
        }

        String hikeDurationString = queryValues[queryStringOrder];

        try {
            hikeDuration = Integer.parseInt(hikeDurationString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid duration value");
        }

        return hikeDuration;
    }
}
