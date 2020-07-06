package ru.job4j.phonedictionary;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Store {
    private List<String> phones = new ArrayList<>();

    public Store(Context context) {
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER},
                null,
                null, null);
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phones.add(name + " " + phone);
            }
        } finally {
            cursor.close();
        }
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
