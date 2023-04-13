package ru.netology.data;

import lombok.Data;

@Data
public class DataInfoUser {

    private final String city;
    private final String date;
    private final String name;
    private final String number;

    public DataInfoUser(String city, String date, String name, String number) {
        this.city = city;
        this.date = date;
        this.name = name;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
