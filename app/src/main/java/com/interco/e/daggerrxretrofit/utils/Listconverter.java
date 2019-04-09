package com.interco.e.daggerrxretrofit.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by emine on 31/01/2019.
 */
public class Listconverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<String> someObjects) {
        return gson.toJson(someObjects);
    }

}
