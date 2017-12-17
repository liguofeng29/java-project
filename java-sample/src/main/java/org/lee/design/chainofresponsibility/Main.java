package org.lee.design.chainofresponsibility;

import javax.activation.DataHandler;
import java.time.LocalDate;
import java.util.logging.Handler;

public class Main {

    public static void main(String[] args) {

        DateHandler dateHandlerConcrete1 = new DateHandlerConcrete1();
        DateHandler dateHandlerConcrete2 = new DateHandlerConcrete2();

        dateHandlerConcrete1.setNext(dateHandlerConcrete2);

        System.out.println(
            dateHandlerConcrete1.process("2011-10-10")
        );

        System.out.println(
            dateHandlerConcrete1.process("2011/10/10")
        );
    }
}
