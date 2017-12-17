package org.lee.design.chainofresponsibility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateHandlerConcrete1 extends DateHandler {
    @Override
    protected LocalDate resolve(String text) throws DateTimeParseException {
        return LocalDate.parse(text);
    }
}
