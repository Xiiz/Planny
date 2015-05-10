package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Yassine Doghri
 */
public class CalendarController {

    private Calendar calendar;

    /**
     * Méthode de création du calendrier grégorien avec la date du système
     *
     * @params void
     */
    public void initCalendar() {
        this.calendar = new GregorianCalendar(Locale.getDefault());
    }

    /**
     * Retourne une liste des jours de la semaine actuelle (de lundi à dimanche)
     *
     * @return ArrayList Date
     */
    public ArrayList<Date> getWeekDays() {
        return this.getArrayWeekDays();
    }

    /**
     * Retourne une liste des jours de la semaine pour une année, un mois et un
     * jour données
     *
     * @param year
     * @param month
     * @param day
     * @return ArrayList Date
     */
    public ArrayList<Date> getWeekDays(int year, int month, int day) {
        calendar.set(year, month, day);
        return this.getArrayWeekDays();
    }

    /**
     * Retourne la liste des jours de la semaine précédente
     *
     * @return ArrayList Date
     */
    public ArrayList<Date> getPreviousWeek() {
        calendar.add(Calendar.DATE, -7);
        return this.getArrayWeekDays();
    }

    /**
     * Retourne la liste des jours de la semaine prochaine
     *
     * @return ArrayList Date
     */
    public ArrayList<Date> getNextWeek() {
        calendar.add(Calendar.DATE, 7);
        return this.getArrayWeekDays();
    }

    /**
     * Retourne la liste des jours de la semaine
     *
     * @return ArrayList Date
     */
    private ArrayList<Date> getArrayWeekDays() {
        ArrayList<Date> days = new ArrayList();
        int delta;
        if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
            delta = -6;
        } else {
            delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + calendar.getFirstDayOfWeek();
        }
        calendar.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, i);
            days.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, -i);
        }
        return days;
    }

    /**
     * Retourne le libellé du jour de la date passée en paramètre
     * 
     * @param date
     * @return 
     */
    public static String sayDayName(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("EEEE");
        try {
            return f.format(date);
        } catch (Exception e) {
            return "";
        }
    }

}
