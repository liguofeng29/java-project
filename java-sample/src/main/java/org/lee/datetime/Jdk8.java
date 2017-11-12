package org.lee.datetime;

import java.time.*;

public class Jdk8 {


    public static void main(String[] args) throws InterruptedException {
        // testClock();

        // testDuration();

        // testInstant();

        // testLocalDate();

        // testLocalTime();

        // testLocalDateTime();
    }

    private static void testLocalDateTime() {

        System.out.println("LocalDateTime#now() : " +
            LocalDateTime.now() // return now(Clock.systemDefaultZone());

        );

        System.out.println("LocalDateTime#now(default) : " +
            LocalDateTime.now(Clock.systemDefaultZone())
        );

        System.out.println("LocalDateTime#now(Europe/Paris) : " +
            LocalDateTime.now(ZoneId.of("Europe/Paris"))
        );

        System.out.println("2012/2/2 2:2:2.222222222 - １年１月１日１時間１分１秒１ナノ秒 : " +
            LocalDateTime.of(2012, 2, 2, 2, 2, 2, 222222222)
            .minusYears(1)
            .minusMonths(1)
            .minusDays(1)
            .minusHours(1)
            .minusMinutes(1)
            .minusSeconds(1)
            .minusNanos(1)
        );
    }

    private static void testLocalDate() {
        System.out.println("LocalDate#now() : " +
            LocalDate.now() // return now(Clock.systemDefaultZone());

        );

        System.out.println("LocalDate#now(default) : " +
            LocalDate.now(Clock.systemDefaultZone())
        );

        System.out.println("LocalDate#now(Europe/Paris) : " +
            LocalDate.now(ZoneId.of("Europe/Paris"))
        );

        System.out.println("2015/10/30 - ４年３ヶ月２週間１日 : " +
            LocalDate.of(2015, 10, 30)
                .minusDays(1)
                .minusWeeks(2)
                .minusMonths(3)
                .minusYears(4)

        );
    }


    private static void testLocalTime() {
        System.out.println("LocalTime#now() : " +
            LocalTime.now().toString() // return now(Clock.systemDefaultZone());

        );

        System.out.println("LocalTime#now(default) : " +
            LocalTime.now(Clock.systemDefaultZone())
        );

        System.out.println("LocalTime#now(Europe/Paris) : " +
            LocalTime.now(ZoneId.of("Europe/Paris"))
        );

        // 同じくminus,plusができるが、割愛
    }

    private static void testInstant() {

        System.out.println("UTC : " +
            Instant.now() // return Clock.systemUTC().instant();
        );

        System.out.println("Defalut : " +
            Instant.now(Clock.systemDefaultZone())
        );

        System.out.println("文字列parse : " +
            Instant.parse("2016-10-15T10:15:30.234Z")
        );

        System.out.println("10秒減らす : " +
            Instant.now().minusSeconds(10)
        );

        System.out.println("10秒増やす : " +
            Instant.now().plusSeconds(10)
        );

        System.out.println("2016-01-01T00:00:00.000Z + １日２時間３分４秒５ミリ６ナノ : " +
            Instant.parse("2016-01-01T00:00:00.000Z")
                .plus(Duration.ofDays(1)
                    .plusHours(2)
                    .plusMinutes(3)
                    .plusSeconds(4)
                    .plusMillis(5)
                    .plusNanos(6)
                )
        );
    }

    private static void testDuration() {

        Duration threeDay = Duration.ofDays(3);

        // 換算
        System.out.println("三日は何時間 : " + threeDay.toHours());
        System.out.println("三日は何分 : " + threeDay.toMinutes());

        // 減らす
        Duration twoDay = threeDay.minusDays(1);
        System.out.println("二日は何時間 : " + twoDay.toHours());
        System.out.println("二日は何分 : " + twoDay.toMinutes());

        // 増やす
        Duration fourDay = threeDay.plusDays(1);
        System.out.println("四日は何時間 : " + fourDay.toHours());
        System.out.println("四日は何分 : " + fourDay.toMinutes());

    }


    private static void testClock() throws InterruptedException {


        // インスタンス生成
        Clock clockDefault = Clock.systemDefaultZone();
        Clock clockUTC = Clock.systemUTC();
        Clock clockParis = Clock.system(ZoneId.of("Europe/Paris"));

        // 時間は進む
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.instant());
        Thread.sleep(1000 * 2);
        System.out.println(clock.instant());

        // 特殊な時計
        // 1. 止まっている時計
        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.of("Asia/Tokyo"));
        System.out.println("fixed clock : " + fixedClock.instant());
        Thread.sleep(1000 * 2);
        System.out.println("fixed clock : " + fixedClock.instant());


        // 2. 分時計
        Clock minutesTickClock = Clock.tickMinutes(ZoneId.of("Asia/Tokyo"));
        System.out.println("minutes tick clock : " + minutesTickClock.instant());
        Thread.sleep(1000 * 2);
        System.out.println("minutes tick clock : " + minutesTickClock.instant());

        // 3. 秒時計
        Clock secondsTickClock = Clock.tickSeconds(ZoneId.of("Asia/Tokyo"));
        System.out.println("seconds tick clock : " + secondsTickClock.instant());
        Thread.sleep(1000 * 2);
        System.out.println("seconds tick clock : " + secondsTickClock.instant());


        // 4. 指定分進んだ時計
        Clock nowClock = Clock.systemDefaultZone();
        Clock offsetClock = Clock.offset(nowClock, Duration.ofDays(1));
        System.out.println("nowClock : " + nowClock.instant());
        System.out.println("offsetClock : " + offsetClock.instant());

    }

}
