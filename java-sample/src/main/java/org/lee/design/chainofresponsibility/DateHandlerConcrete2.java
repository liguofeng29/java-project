package org.lee.design.chainofresponsibility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHandlerConcrete2 extends DateHandler {
    @Override
    protected LocalDate resolve(String text) throws DateTimeParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
