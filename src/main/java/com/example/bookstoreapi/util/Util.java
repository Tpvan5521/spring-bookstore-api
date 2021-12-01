package com.example.bookstoreapi.util;

import java.text.Normalizer;
import java.util.Random;
import java.util.regex.Pattern;

public class Util {

    public static String nonAccentVietnamese(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    
    public static String parseStringToSlug(String str, int max, int min) {
        Random random = new Random();
        int randomId = random.nextInt(max - min) + min;
        String convertedName = str.toLowerCase().replace(" ", "-");
        return convertedName + "-" + Integer.toString(randomId);
    }
}
