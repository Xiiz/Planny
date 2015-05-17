package helper;

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
public class CalendarHelper {

    /**
     * Retourne une liste des jours de la semaine actuelle (de lundi à dimanche)
     *
     * @return liste de dates de la semaine
     */
    public static ArrayList<Date> getWeekDays(Calendar calendar) {
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
     * Retourne une liste des jours de la semaine actuelle (de lundi à dimanche)
     * d'une date donnée
     *
     * @param date
     * @return
     */
    public static ArrayList<Date> getWeekDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getWeekDays(cal);
    }

    /**
     * Retourne les jours de la semaine en cours
     *
     * @return
     */
    public static ArrayList<Date> getCurrentWeekDays() {
        Calendar cal = Calendar.getInstance();
        return getWeekDays(cal);
    }

    /**
     * Retourne le lundi de la semaine suivante
     *
     * @param calendar
     * @return
     */
    public static Date getMondayOfNextWeek(Calendar calendar) {
        calendar.add(Calendar.DATE, 7);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return calendar.getTime();
    }

    /**
     * Retourne le lundi de la semaine précédente
     *
     * @param calendar
     * @return Date Lundi de la semaine précédente
     */
    public static Date getMondayOfPrevWeek(Calendar calendar) {
        calendar.add(Calendar.DATE, -7);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return calendar.getTime();
    }

    /**
     * Retourne la liste des jours de la semaine précédant la date sélectionnée
     *
     * @param date
     * @return ArrayList Date
     */
    public static ArrayList<Date> getPreviousWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            cal.add(Calendar.DATE, -1);
        }
        return getWeekDays(cal);
    }

    /**
     * Retourne la liste des jours de la semaine suivant la date sélectionnée
     *
     * @param date
     * @return ArrayList Date
     */
    public static ArrayList<Date> getNextWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            cal.add(Calendar.DATE, -1);
        }
        return getWeekDays(cal);
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

    /**
     * Retourne le nom du jour pour une date donnée
     *
     * @param date
     * @return String
     */
    public static String getDayColumnLabel(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM");
        return sayDayName(date) + ' ' + f.format(date);
    }

    /**
     * Retourne l'année scolaire du planning
     *
     * @param date
     * @return
     */
    public static String getPlanningYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if (month < Calendar.SEPTEMBER) {
            return (year - 1) + "-" + year;
        } else {
            return year + "-" + (year + 1);
        }
    }

    /**
     * Retourne le numéro du week end depuis le 1er septembre de l'année
     * scolaire
     *
     * @param date
     * @return
     */
    public static int getWeekNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        int diff = 0;
        if (month < Calendar.SEPTEMBER) {
            cal2.set(year - 1, Calendar.SEPTEMBER, 1); // set to 1 sep of prev year
            Calendar cal3 = Calendar.getInstance();
            cal3.set(year, Calendar.DECEMBER, 31); // set to 31 Dec
            while (cal2.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                cal2.add(Calendar.DATE, -1);
            }
            diff = cal3.get(Calendar.WEEK_OF_YEAR) - cal2.get(Calendar.WEEK_OF_YEAR); // get diff of weeks to Dec
            return (cal.get(Calendar.WEEK_OF_YEAR) + diff) + 1;
        } else {
            cal2.set(year, Calendar.SEPTEMBER, 1); // set to 1 sep of this year
            return cal.get(Calendar.WEEK_OF_YEAR) - cal2.get(Calendar.WEEK_OF_YEAR) + 1;
        }
    }

    public static String getWeekInterval(Date date) {
        ArrayList<Date> weekDays = getWeekDays(date);
        SimpleDateFormat f = new SimpleDateFormat("dd/MM");
        return "Semaine du " + f.format(weekDays.get(0))
                + " au "
                + f.format(weekDays.get(6));
    }

    public static int getWeekNumber(Calendar cal) {
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            return 6;
        }
        return cal.get(Calendar.DAY_OF_WEEK) - 2;
    }
}
