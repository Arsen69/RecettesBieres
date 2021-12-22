package service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import exception.CheckDateException;
import exception.CheckLongException;

public class Check {
	public static void checkLong(Long id) {
		if (id == null || id < 0) {
			throw new CheckLongException();
		}
	}
	
	public static void checkDateTime(LocalDateTime dateTime) {
		if (dateTime == null) {
			throw new CheckDateException();
		}
	}
	
	public static void checkDate(LocalDate date) {
		if (date == null) {
			throw new CheckDateException();
		}
	}
	
	public static LocalDateTime checkDateTimeNow(LocalDateTime dateTime) {
		if (dateTime == null) {
			return LocalDateTime.now();
		}else {
			return dateTime;
		}
	}
	public static LocalDate checkDateNow(LocalDate date) {
		if (date == null) {
			return LocalDate.now();
		}else {
			return date;
		}
	}
}
