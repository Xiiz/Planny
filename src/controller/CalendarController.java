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
     * Méthode de création du calendrier grégorien avec par défaut la date du
     * système
     */
    public void initCalendar() {
        this.calendar = new GregorianCalendar(Locale.getDefault());
    }

    /**
     * Retourne une liste des jours de la semaine actuelle (de lundi à dimanche)
     *
     * @return liste de dates de la semaine
     */
    public ArrayList<Date> getWeekDays() {
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
     * Retourne une liste des jours de la semaine pour une année, un mois et un
     * jour données
     *
     * @param year année de la date
     * @param month mois de la date (allant de 0 à 11)
     * @param day jour de la date
     * @return ArrayList Date
     */
    public ArrayList<Date> getWeekDays(int year, int month, int day) {
        calendar.set(year, month, day);
        return this.getWeekDays();
    }

    /**
     * Retourne la liste des jours de la semaine précédant la date sélectionnée
     *
     * @return ArrayList Date
     */
    public ArrayList<Date> getPreviousWeek() {
        calendar.add(Calendar.DATE, -7);
        return this.getWeekDays();
    }

    /**
     * Retourne la liste des jours de la semaine suivant la date sélectionnée
     *
     * @return ArrayList Date
     */
    public ArrayList<Date> getNextWeek() {
        calendar.add(Calendar.DATE, 7);
        return this.getWeekDays();
    }

    /**
     * Retourne le libellé du jour de la date passée en paramètre
     *
     * @param date
     * @return String libellé du jour
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
