package com.javatpoint.controller;  
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  
  
@RestController
@RequestMapping("/time")
public class HelloWorldController   
{  
    @GetMapping("/current")
    public String getCurrentTimeInWords() {
        LocalTime currentTime = LocalTime.now();
        return convertTimeToWords(currentTime);
    }

    @GetMapping("/{time}")
    public String getTimeInWords(@PathVariable("time") String time) {
        LocalTime parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        return convertTimeToWords(parsedTime);
    }

    private String convertTimeToWords(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour == 0 && minute == 0) {
            return "It's Midnight";
        } else if (hour == 12 && minute == 0) {
            return "It's Midday";
        }

        String hourInWords = convertNumberToWords(hour);
        String minuteInWords = convertNumberToWords(minute);

        StringBuilder result = new StringBuilder("It's ");

        if (hour <= 12) {
            result.append(hourInWords);
        } else {
            result.append(convertNumberToWords(hour - 12));
        }

        result.append(" ").append(hour == 1 || hour == 13 ? "hour" : "hours");

        if (minute > 0) {
            result.append(" ").append(minuteInWords).append(" minute");
            if (minute != 1) {
                result.append("s");
            }
        }

        return result.toString();
    }

    private String convertNumberToWords(int number) {
        String[] units = {
                "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen"
        };

        String[] tens = {
                "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        if (number < 20) {
            return units[number];
        }

        if (number < 100) {
            return tens[number / 10] + (number % 10 != 0 ? " " + units[number % 10] : "");
        }

        return "";
    }

}  
