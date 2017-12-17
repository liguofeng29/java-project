package org.lee.design.chainofresponsibility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class DateHandler {
    private DateHandler nextHandler;


    public DateHandler setNext(DateHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public final LocalDate process(String text) {
        try {
            System.out.println(this.getClass());
            return resolve(text);
        } catch (DateTimeParseException ex) {
            if (nextHandler != null) {
                return nextHandler.resolve(text);
            }
            return null;
        }
    }

    protected abstract LocalDate resolve(String text) throws DateTimeParseException;
}
