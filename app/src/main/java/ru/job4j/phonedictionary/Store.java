package ru.job4j.phonedictionary;

import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Store {
    private List<String> phones = new ArrayList<>();
    private static final Store INST = new Store();

    private Store() {
    }

    public static Store getInstance() {
        return INST;
    }

    public List<String> getPhones() {
        return phones;
    }

    public static List<String> filter(List<String> phones, String filter) {
        return phones.stream()
                .filter(phone -> phone.toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());
    }
}
