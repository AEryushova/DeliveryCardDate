package ru.netology.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {
    private DataGenerator() {
    }

    private final static Faker faker = new Faker(new Locale("ru"));

    public static String generateCity() {
        return faker.address().cityName();
    }

    public static String generateDate(int day) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName() {
        return faker.name().fullName();

    }

    public static String generateNumber() {
        return faker.phoneNumber().phoneNumber();
    }
}