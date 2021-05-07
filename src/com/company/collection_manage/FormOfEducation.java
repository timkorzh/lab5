package com.company.collection_manage;

public enum  FormOfEducation {
    DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES;
        public static String GetStringValues() {
            String Result = "";
            for (int i = 0; i < values().length; i++ ) {
                Result += i + " - " + values()[i] + " | ";
            }
            return Result;
        }
};