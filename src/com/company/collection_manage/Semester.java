package com.company.collection_manage;

public enum Semester {
    FIRST,
    THIRD,
    SIXTH,
    EIGHTH;

    public static String GetStringValues() {
        String Result = "";
        for (int i = 0; i < values().length; i++ ) {
            Result += i + " - " + values()[i] + " | ";
        }
        return Result;
    }
}
