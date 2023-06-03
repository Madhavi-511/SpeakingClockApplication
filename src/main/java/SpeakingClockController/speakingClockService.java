package SpeakingClockController;



import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class speakingClockService {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.format(TIME_FORMATTER);
    }

    public String convertTimeToWords(String time) {
        LocalTime parsedTime = LocalTime.parse(time, TIME_FORMATTER);
        int hours = parsedTime.getHour();
        int minutes = parsedTime.getMinute();

        if (!isValidTime(hours, minutes)) {
            throw new IllegalArgumentException("Invalid time format");
        }

        String timeInWords;

        if (hours == 0 && minutes == 0) {
            timeInWords = "It's Midnight";
        } else if (hours == 12 && minutes == 0) {
            timeInWords = "It's Midday";
        } else {
            String hoursInWords = convertNumberToWords(hours);
            String minutesInWords = convertNumberToWords(minutes);

            timeInWords = "It's " + hoursInWords + " " + minutesInWords;
        }

        return timeInWords;
    }

    private String convertNumberToWords(int number) {
        String[] numbers = {
                "twelve", "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen",
                "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
                "nineteen"
        };

        String[] tens = {
                "", "", "twenty", "thirty", "forty", "fifty", "sixty",
                "seventy", "eighty", "ninety"
        };

        if (number == 0) {
            return "twelve";
        } else if (number < 20) {
            return numbers[number];
        } else {
            int tensDigit = number / 10;
            int onesDigit = number % 10;
            if (onesDigit == 0) {
                return tens[tensDigit];
            } else {
                return tens[tensDigit] + " " + numbers[onesDigit];
            }
        }
    }

    private boolean isValidTime(int hours, int minutes) {
        return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
    }
}

