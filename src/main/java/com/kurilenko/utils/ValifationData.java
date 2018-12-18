package com.kurilenko.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValifationData {
    public static boolean validFio(String fio) {
        String REG_VALUE_FIO = "(^[A-Z]{1}[a-z]{1,14} [A-Z]{1}[a-z]{1,14} [A-ZА-Я]{1}[a-zа-я]{1}([a-z]{0,13})?$)|(^[А-Я]{1}[а-я]{1,14} [А-Я]{1}[а-я]{1,14} [А-Я]{1}[а-я]{1,14}$)";
        return fio.matches(REG_VALUE_FIO);
    }

    public static boolean validTelephon(String tel) {
        String REG_VALUE_TELEPHONE = "(^(?!\\+.*\\(.*\\).*\\-\\-.*$)(?!\\+.*\\(.*\\).*\\-$)(\\+[0-9]{3}\\([0-9]{2}\\)[0-9]{7})$)|(^[0-9]{1,4}$)";
        return tel.matches(REG_VALUE_TELEPHONE) || tel.contains("-");
    }

    public static boolean validDate(String date) {
        String DATE_PATTERN = "((19|20)\\d\\d)\\.(0?[1-9]|1[012])\\.(0?[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {

            matcher.reset();

            if (matcher.find()) {
                String day = matcher.group(3);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(1));

                if ("31".equals(day)) {
                    // 31 день может быть только в месяцах
                    // 1, 3, 5, 7, 8, 10, 12
                    return Arrays.asList(new String[]{"1", "01", "3", "03",
                            "5", "05", "7", "07", "8", "08", "10", "12"})
                            .contains(month);

                } else if ("2".equals(month) || "02".equals(month)) {
                    // Проверяем февраль
                    if (year % 4 == 0) {
                        if (year % 100 == 0) {
                            if (year % 400 == 0) {
                                // Високосный год
                                return Integer.parseInt(day) <= 29;
                            }
                            // Невисокосный год
                            return Integer.parseInt(day) <= 28;
                        }
                        // Високосный год
                        return Integer.parseInt(day) <= 29;
                    } else {
                        // Невисокосный год
                        return Integer.parseInt(day) <= 28;
                    }

                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean validGroup(String group) {
        String REG_VALUE_GROUP = "^(?!.*123$)(?!.*0.*$)([0-9]{2}[-]{1}[А-Я]{2}([-А-Яа-я]{0,1})?([-0-9а-я]{0,1})?([-0-9]{0,1})?([0-9]{0,1})?)$";
        return group.matches(REG_VALUE_GROUP);
    }

    public static boolean validAddress(String address) {
        return !address.isEmpty();
    }
}
