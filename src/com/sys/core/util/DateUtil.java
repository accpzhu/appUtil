package com.sys.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Date utils
 * @author fusq
 */
public abstract class DateUtil {
	
	public static final String EN_GB_DATE_PATTERN = "dd/MM/yyyy";

	private static String EN_GB_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

	private static String ZH_CN_DATE_PATTERN = "yyyy-MM-dd";
	 
    private static String ZH_CN_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Parse date string to uk date format
	 */
	public static Date parseEnDateTimeString(String dateString) {
		return parseDateString(dateString, EN_GB_DATETIME_PATTERN);
	}

	public static Date parseZhDateTimeString(String dateString){
		return parseDateString(dateString, ZH_CN_DATETIME_PATTERN);
	}
	
	
	public static Date parseEnDateString(String dateString) {
		return parseDateString(dateString, EN_GB_DATE_PATTERN);
	}

	public static Date parseZhDateString(String dateString){
		return parseDateString(dateString, ZH_CN_DATE_PATTERN);
	}	
	
	public static String formaEnDateTime(Object value){
		return formatDateTime(value,EN_GB_DATETIME_PATTERN);
	}
	
	public static String formaEnDate(Object value){
		return formatDateTime(value,EN_GB_DATE_PATTERN);
	}
	
	public static String formaZhDateTime(Object value){
		return formatDateTime(value,ZH_CN_DATETIME_PATTERN);
	}
	
	public static String formaZhDate(Object value){
		return formatDateTime(value,ZH_CN_DATE_PATTERN);
	}
	
	public static String formatDateTime(Object value,String pattern){
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		if (value == null) {
			return "";
		} else if (value instanceof String) {
			java.util.Date date = new java.util.Date();
			try {
				date = dateFormat.parse(StringUtil.getString(value));
			} catch (ParseException e) {
				return "";
			}
			return dateFormat.format(date);
		} else if (value instanceof java.util.Date) {
			return dateFormat.format(value);
		} else {
			return "";
		}
	}
	
	/**
	 * Parse date string to date format
	 */
	public static Date parseDateString(String dateString, String pattern) {
		if (!StringUtils.hasText(pattern)) {
			pattern = ZH_CN_DATETIME_PATTERN;
		}
		DateFormat formatter = createFormatter(pattern);
		try {
			return formatter.parse(dateString);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * Formats the given date.
	 */
	public static String formatDate(Date date) {
		Locale locale = LocaleContextHolder.getLocale();
		DateFormat formatter = createDateFormatter(locale);
		return formatter.format(date);
	}

	/**
	 * Formats the given time.
	 */
	public static String formatTime(Date date) {
		Locale locale = LocaleContextHolder.getLocale();
		DateFormat formatter = createTimeFormatter(locale);
		return formatter.format(date);
	}

	/**
	 * Formats the given date and time.
	 */
	public static String formatDateTime(Date date) {
		Locale locale = LocaleContextHolder.getLocale();
		DateFormat formatter = createDateTimeFormatter(locale);
		return formatter.format(date);
	}

	
	
	/**
	 * Format a datetime into a specific pattern.
	 */
	public static String formatDate(Date date, String pattern) {
		if (!StringUtils.hasText(pattern)) {
			pattern = ZH_CN_DATETIME_PATTERN;
		}
		DateFormat formatter = createFormatter(pattern);
		return formatter.format(date);
	}

	public static String formatDateDefault(Date date) {
		return formatDate(date, ZH_CN_DATETIME_PATTERN);
	}

	public static String formatDate(Date date, DateFormat formatter) {
		Assert.notNull(date);
		return formatter.format(date);
	}

	/**
	 * Adds a number of days to a date returning a new date.
	 */
	public static Date dayEnd(Date date) {
		Assert.notNull(date);
		Calendar calendar = dateToCalendar(date);
		adjustCalendar(calendar, Calendar.DAY_OF_MONTH, 1);
		adjustCalendar(calendar, Calendar.SECOND, -1);
		return calendarToDate(calendar);
	}

	public static Date adjustMonth(Date date, int amount) {
		Assert.notNull(date);
		Calendar calendar = dateToCalendar(date);
		adjustCalendar(calendar, Calendar.MONTH, amount);
		return calendarToDate(calendar);
	}

	public static Calendar dateToCalendar(Date date) {
		Assert.notNull(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}

	public static void adjustCalendar(Calendar calendar, int field, int amount) {
		calendar.add(field, amount);
	}

	public static DateFormat createFormatter(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	public static DateFormat createDateFormatter(Locale locale) {
		return DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
	}

	public static DateFormat createTimeFormatter(Locale locale) {
		return DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
	}

	public static DateFormat createDateTimeFormatter(Locale locale) {
		return DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
	}
}
