package se.kth.iv1350.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Responsible for showing error messages to user.
 */
public class ErrorMessageHandler {

    /**
     * Displays the specified error message.
     *
     * @param msg The error message.
     */
    void showErrorMsg(String msg) {

        String errorMessage = createTime() +
                ", ERROR: " +
                msg;

        System.out.println(errorMessage);
    }

    private String createTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return LocalDateTime.now().format(formatter);
    }
}
