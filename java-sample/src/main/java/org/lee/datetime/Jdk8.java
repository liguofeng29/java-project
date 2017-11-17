package utils;

import java.time.*;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Calendar;
import java.util.Locale;

public class Jdk8 {


    public static void main(String[] args) throws InterruptedException {

//        coreAPI();
//
//        testTemporalAccessor();
//
//        testTemporal();
//
//        testTemporalAmount();
//
//        testTemporalField();
//
//        testTemporalUnit();
//
//        testTemporalAdjuster();
//
//        testTemporalQuery();
//
//        testClock();
//
        testDuration();
//
        //testPeriod();

//        testInstant();
//
//        testLocalDate();
//
//        testLocalTime();
//
//        testLocalDateTime();
//
//        testZoneDateTime();
//
//        testYear();
//
//        testMonth();
//
//        testYearMonth();
//
//        testMonthDay();
//
//        testZoneId();
//
//        testDayOfWeek();
//
//        testTranslate();
//
//        testOffsetDateTime();
//
//        testJapaneseDate();

    }

    private static void testTemporalQuery() {


        TemporalQuery<Long> leftDays = temporal -> {
            LocalDate dayOfTarget = LocalDate.from(temporal);
            LocalDate lastDayOfYear = dayOfTarget.with(TemporalAdjusters.lastDayOfYear());
            Period period = dayOfTarget.until(lastDayOfYear);
            return ChronoUnit.DAYS.between(dayOfTarget, lastDayOfYear);
        };


        System.out.println(
            LocalDate.of(2015, 10, 3).query(leftDays)
        );


        System.out.println(
            LocalDate.of(2015, 10, 3).query(TemporalQueries.precision())
        );


    }

    private static void testTemporalAdjuster() {

        TemporalAdjuster adjuster = LocalDate.of(2011, 1, 1);


        System.out.println(
            new StringBuilder()
                .append("| TemporalAdjusterメソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| TemporalAdjuster adjuster = LocalDate.of(2011, 1, 1) |" + adjuster + "|").append(System.lineSeparator())
                .append("| adjuster.adjustInto(LocalDate.of(9999, 10, 1)) |" + adjuster.adjustInto(LocalDate.of(9999, 10, 1)) + "|").append(System.lineSeparator())
                .toString()
        );


        System.out.println(
            new StringBuilder()
                .append("| TemporalAdjusters| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| LocalDate.of(2011, 1, 1).with(TemporalAdjusters.lastDayOfYear()) |" + LocalDate.of(2011, 1, 1).with(TemporalAdjusters.lastDayOfYear()) + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2011, 1, 1).with(TemporalAdjusters.lastDayOfMonth()) |" + LocalDate.of(2011, 1, 1).with(TemporalAdjusters.lastDayOfMonth()) + "|").append(System.lineSeparator())
                .toString()
        );


    }

    private static void testTemporalUnit() {

        TemporalUnit days = ChronoUnit.DAYS;

        System.out.println(
            new StringBuilder()
                .append("| TemporalUnitメソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| TemporalUnit days = ChronoUnit.DAYS |" + ChronoUnit.DAYS + "|").append(System.lineSeparator())
                .append("| days.addTo(LocalDate.of(2011, 1, 1), 2) |" + days.addTo(LocalDate.of(2011, 1, 1), 2) + "|").append(System.lineSeparator())


                .append("| days.getDuration() |" + days.getDuration() + "|").append(System.lineSeparator())
                .append("| days.isSupportedBy(LocalDate.now()) |" + days.isSupportedBy(LocalDate.now()) + "|").append(System.lineSeparator())
                .append("| days.isDurationEstimated() |" + days.isDurationEstimated() + "|").append(System.lineSeparator())
                .append("| days.isDateBased() |" + days.isDateBased() + "|").append(System.lineSeparator())
                .append("| days.isTimeBased() |" + days.isTimeBased() + "|").append(System.lineSeparator())
                .toString()
        );
    }


    private static void testTemporalField() {

        TemporalField year = ChronoField.YEAR;

        System.out.println(
            new StringBuilder()
                .append("| TemporalFieldメソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| TemporalField year = ChronoField.YEAR |" + year + "|").append(System.lineSeparator())
                .append("| year.range()|" + year.range() + "|").append(System.lineSeparator())
                .append("| year.getBaseUnit()|" + year.getBaseUnit() + "|").append(System.lineSeparator())
                .append("| year.getDisplayName(Locale.JAPAN)|" + year.getDisplayName(Locale.JAPAN) + "|").append(System.lineSeparator())
                .append("| year.getFrom(LocalDate.of(2011, 1, 10))|" + year.getFrom(LocalDate.of(2011, 1, 10)) + "|").append(System.lineSeparator())
                .append("| year.getRangeUnit() |" + year.getRangeUnit() + "|").append(System.lineSeparator())
                .append("| year.isDateBased()|" + year.isDateBased() + "|").append(System.lineSeparator())
                .append("| year.isTimeBased()|" + year.isTimeBased() + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testTemporalAmount() {


        TemporalAmount temporalAmount = Period.ofDays(61);

        System.out.println(
            new StringBuilder()
                .append("| TemporalAmountメソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| TemporalAmount temporalAmount = Duration.ofDays(61) |" + temporalAmount + "|").append(System.lineSeparator())
                .append("| temporalAmount.getUnits() |" + temporalAmount.getUnits() + "|").append(System.lineSeparator())
                .append("| temporalAmount.get(ChronoUnit.DAYS) |" + temporalAmount.get(ChronoUnit.DAYS) + "|").append(System.lineSeparator())
                .append("| temporalAmount.get(ChronoUnit.MONTHS) |" + temporalAmount.get(ChronoUnit.MONTHS) + "|").append(System.lineSeparator())
                .append("| temporalAmount.subtractFrom(LocalDate.of(2010, 10, 1)) |" + temporalAmount.subtractFrom(LocalDate.of(2010, 10, 1)) + "|").append(System.lineSeparator())
                .append("| temporalAmount.addTo(LocalDate.of(2010, 10, 1)) |" + temporalAmount.addTo(LocalDate.of(2010, 10, 1)) + "|").append(System.lineSeparator())
                .toString()
        );

    }

    private static void testTemporal() {
        Temporal temporal = LocalDate.of(2016, 10, 1);


        System.out.println(
            new StringBuilder()
                .append("| Temporalメソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Temporal temporal = LocalDate.of(2016, 10, 1) |" + temporal + "|").append(System.lineSeparator())
                .append("| temporal.plus(10, ChronoUnit.MONTHS) |" + temporal.plus(10, ChronoUnit.MONTHS) + "|").append(System.lineSeparator())
                .append("| temporal.minus(Period.ofYears(1)) |" + temporal.minus(Period.ofYears(1)) + "|").append(System.lineSeparator())
                .append("| temporal.minus(2, ChronoUnit.YEARS) |" + temporal.minus(2, ChronoUnit.YEARS) + "|").append(System.lineSeparator())
                .append("| temporal.with(ChronoField.YEAR, 2010) |" + temporal.with(ChronoField.YEAR, 2010) + "|").append(System.lineSeparator())
                .append("| temporal.with(LocalDate.of(2010, 1, 10)) |" + temporal.with(LocalDate.of(2010, 1, 10)) + "|").append(System.lineSeparator())
                .append("| temporal.until(LocalDate.of(2016, 1, 1), ChronoUnit.DAYS) |" + temporal.until(LocalDate.of(2016, 1, 1), ChronoUnit.DAYS) + "|").append(System.lineSeparator())
                // .append("|  |" +      + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testTemporalAccessor() {
        TemporalAccessor temporalAccessor = LocalDate.of(2016, 2, 9);

        System.out.println(
            new StringBuilder()
                .append("| TemporalAccessorメソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())

                .append("| TemporalAccessor temporalAccessor =  LocalDate.of(2016, 2, 9) |" + temporalAccessor).append("|" + System.lineSeparator())

                .append("| temporalAccessor.get(ChronoField.YEAR) |" + temporalAccessor.get(ChronoField.YEAR)).append("|" + System.lineSeparator())
                .append("| temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR) |" + temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR)).append("|" + System.lineSeparator())
                .append("| temporalAccessor.isSupported(ChronoField.HOUR_OF_DAY) |" + temporalAccessor.isSupported(ChronoField.HOUR_OF_DAY)).append("|" + System.lineSeparator())
                .append("| temporalAccessor.range(ChronoField.DAY_OF_MONTH) |" + temporalAccessor.range(ChronoField.DAY_OF_MONTH)).append("|" + System.lineSeparator())
                .append("| temporalAccessor.query(TemporalQueries.precision()) |" + temporalAccessor.query(TemporalQueries.precision())).append("|" + System.lineSeparator())
                // .append("|  |" +      + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void coreAPI() {
        System.out.println(
            new StringBuilder()
                .append("| JDK1.8日付、時刻のコアAPI| 概要 |").append(System.lineSeparator())
                .append("|:---|:---|").append(System.lineSeparator())
                .append("|").append("TemporalAccessor").append("|").append("日付、時間、オフセット、またはそれらのなんらかの組合せなどへの<b>読取り専用</b>アクセスを定義したインタフェースです。").append("|").append(System.lineSeparator())
                .append("|").append("Temporal").append("|").append("日付、時間、オフセット、またはそれらのなんらかの組合せなどへの<b>読取/書込</b>アクセスを定義したインタフェースです。").append("|").append(System.lineSeparator())
                .append("|").append("TemporalAmount").append("|").append("時間の量を定義したインタフェースです。").append("|").append(System.lineSeparator())
                .append("|").append("TemporalField").append("|").append("日付、時間のフィールドを定義したインタフェースです。").append("|").append(System.lineSeparator())
                .append("|").append("TemporalUnit").append("|").append("日付、時間の単位を定義したFunctionalインタフェースです。").append("|").append(System.lineSeparator())
                .append("|").append("TemporalAdjuster").append("|").append("日付、時間調整戦略を提供するためのFunctionalインタフェースです。").append("|").append(System.lineSeparator())
                .append("|").append("TemporalQuery").append("|").append("日付、時間にアクセスする戦略を提供するためのFunctionalインタフェースです。").append("|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testJapaneseDate() {

        System.out.println(
            JapaneseDate.now().format(DateTimeFormatter.ofPattern("Gyy年MM月dd日"))
        );

        System.out.println(
            JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).plus(11, ChronoUnit.DAYS)

        );


        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| JapaneseDate.now() |" + JapaneseDate.now() + "|").append(System.lineSeparator())
                .append("| JapaneseDate.now(Clock.systemUTC()) |" + JapaneseDate.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(2015, 10, 1) |" + JapaneseDate.of(2015, 10, 1) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA,59,10,1) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1) + "|").append(System.lineSeparator())

                .toString()
        );


        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA,59,10,1).getEra() |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).getEra() + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA,59,10,1).get(ChronoField.YEAR_OF_ERA) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).get(ChronoField.YEAR_OF_ERA) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA,59,10,1).get(ChronoField.YEAR) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).get(ChronoField.YEAR) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA,59,10,1).get(ChronoField.MONTH_OF_YEAR) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).get(ChronoField.MONTH_OF_YEAR) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA,59,10,1).get(ChronoField.DAY_OF_MONTH) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).get(ChronoField.DAY_OF_MONTH) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).plus(1, ChronoUnit.DAYS).plus(2, ChronoUnit.YEARS) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).plus(1, ChronoUnit.DAYS).plus(2, ChronoUnit.YEARS) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).minus(1, ChronoUnit.DAYS).minus(2, ChronoUnit.YEARS) |" + JapaneseDate.of(JapaneseEra.SHOWA, 59, 10, 1).minus(1, ChronoUnit.DAYS).minus(2, ChronoUnit.YEARS) + "|").append(System.lineSeparator())
                .append("| JapaneseDate.now().format(DateTimeFormatter.ofPattern(\"Gyy年MM月dd日\")) |" + JapaneseDate.now().format(DateTimeFormatter.ofPattern("Gyy年MM月dd日")) + "|").append(System.lineSeparator())


        );


    }

    private static void testOffsetDateTime() {

        System.out.println(
            OffsetDateTime.now()
        );

        System.out.println(
            ZonedDateTime.now().getMinute()
        );

        System.out.println(
            OffsetDateTime.now().getMinute()
        );
    }

    private static void testTranslate() {
        System.out.println(
            new StringBuilder()
                .append("| MonthDay.of(10, 1).atYear(2015) |" + MonthDay.of(10, 1).atYear(2015).getClass().getName() + "|").append(System.lineSeparator())

                .append("| YearMonth.of(2015, 10).atDay(30) |" + YearMonth.of(2015, 10).atDay(30).getClass().getName() + "|").append(System.lineSeparator())
                .append("| YearMonth.of(2015, 10).atEndOfMonth() |" + YearMonth.of(2015, 10).atEndOfMonth().getClass().getName() + "|").append(System.lineSeparator())


                .append("| LocalTime.now().atDate(LocalDate.now()) |" + LocalTime.now().atDate(LocalDate.now()) + "|").append(System.lineSeparator())

                .append("| LocalDate.now().atTime(10, 20) |" + LocalDate.now().atTime(10, 20).getClass().getName() + "|").append(System.lineSeparator())
                .append("| LocalDate.now().atStartOfDay() |" + LocalDate.now().atStartOfDay().getClass().getName() + "|").append(System.lineSeparator())
                .append("| LocalDate.now().atStartOfDay(ZoneId.of(\"Asia/Shanghai\")) |" + LocalDate.now().atStartOfDay(ZoneId.of("Asia/Shanghai")).getClass().getName() + "|").append(System.lineSeparator())

                .append("| LocalDateTime.now().atZone(ZoneId.of(\"Asia/Shanghai\")) |" + LocalDateTime.now().atZone(ZoneId.of("Asia/Shanghai")).getClass().getName() + "|").append(System.lineSeparator())
                .append("| LocalDateTime.now().toLocalDate() |" + LocalDateTime.now().toLocalDate().getClass().getName() + "|").append(System.lineSeparator())
                .append("| LocalDateTime.now().toLocalTime() |" + LocalDateTime.now().toLocalTime().getClass().getName() + "|").append(System.lineSeparator())
                .append("| LocalDateTime.now().toInstant(ZoneOffset.UTC) |" + LocalDateTime.now().toInstant(ZoneOffset.UTC).getClass().getName() + "|").append(System.lineSeparator())


                .append("| ZonedDateTime.now().toLocalDateTime() |" + ZonedDateTime.now().toLocalDateTime().getClass().getName() + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.now().toLocalDate() |" + ZonedDateTime.now().toLocalDate().getClass().getName() + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.now().toInstant() |" + ZonedDateTime.now().toInstant().getClass().getName() + "|").append(System.lineSeparator())


                .append("| Instant.now().atZone(ZoneId.of(\"Asia/Shanghai\")) |" + Instant.now().atZone(ZoneId.of("Asia/Shanghai")).getClass().getName() + "|").append(System.lineSeparator())
                .append("| Instant.now().atOffset(ZoneOffset.UTC)|" + Instant.now().atOffset(ZoneOffset.UTC).getClass().getName() + "|").append(System.lineSeparator())

        );
    }

    private static void testZoneId() {
        ZoneId.systemDefault();

        ZoneId.getAvailableZoneIds();

        ZoneId.of("Asia/Tokyo");
    }

    private static void testYear() {


        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Year.now() |" + Year.now() + "|").append(System.lineSeparator())
                .append("| Year.now(Clock.systemUTC()) |" + Year.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| Year.now(ZoneId.of(\"Europe/Paris\")) |" + Year.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| Year.of(2011)  |" + Year.of(2011) + "|").append(System.lineSeparator())
                .append("| Year.parse(\"2012\")|" + Year.parse("2012") + "|").append(System.lineSeparator())
                .toString()
        );


        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Year.of(2011).getValue() |" + Year.of(2011).getValue() + "|").append(System.lineSeparator())
                .append("| Year.of(2011).plusYears(3) |" + Year.of(2011).plusYears(3) + "|").append(System.lineSeparator())
                .append("| Year.of(2011).minusYears(10) |" + Year.of(2011).minusYears(10) + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testMonth() {
        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Month.of(10)  |" + Month.of(10) + "|").append(System.lineSeparator())
                .append("| Month.valueOf(\"JANUARY\") |" + Month.valueOf("JANUARY") + "|").append(System.lineSeparator())
                .append("| Month.FEBRUARY | " + Month.FEBRUARY + "|").append(System.lineSeparator())
                .toString()
        );
        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Month.of(1).getValue() |" + Month.of(1).getValue() + "|").append(System.lineSeparator())
                .append("| Month.of(12).plus(3) |" + Month.of(12).plus(3) + "|").append(System.lineSeparator())
                .append("| Month.of(3).minus(4)  |" + Month.of(3).minus(4) + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testDayOfWeek() {
        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| DayOfWeek.of(1)  |" + DayOfWeek.of(1) + "|").append(System.lineSeparator())
                .append("| DayOfWeek.valueOf(\"FRIDAY\") |" + DayOfWeek.valueOf("FRIDAY") + "|").append(System.lineSeparator())
                .append("| DayOfWeek.FRIDAY | " + DayOfWeek.FRIDAY + "|").append(System.lineSeparator())
                .toString()
        );
        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| DayOfWeek.of(1).getValue() |" + DayOfWeek.of(1).getValue() + "|").append(System.lineSeparator())
                .append("| DayOfWeek.of(1).plus(7) |" + DayOfWeek.of(1).plus(7) + "|").append(System.lineSeparator())
                .append("| DayOfWeek.of(1).minus(7) |" + DayOfWeek.of(1).minus(7) + "|").append(System.lineSeparator())
                .toString()
        );
    }


    private static void testYearMonth() {

        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| YearMonth.now() |" + YearMonth.now() + "|").append(System.lineSeparator())
                .append("| YearMonth.now(Clock.systemUTC()) |" + YearMonth.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| YearMonth.now(ZoneId.of(\"Europe/Paris\")) |" + YearMonth.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| YearMonth.of(2011, 10)  |" + YearMonth.of(2011, 10) + "|").append(System.lineSeparator())
                .append("| YearMonth.parse(\"2012-10\")|" + YearMonth.parse("2012-10") + "|").append(System.lineSeparator())
                .toString()
        );


        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| YearMonth.of(2011, 10).getYear() |" + YearMonth.of(2011, 10).getYear() + "|").append(System.lineSeparator())
                .append("| YearMonth.of(2011, 10).getMonth() |" + YearMonth.of(2011, 10).getMonth() + "|").append(System.lineSeparator())
                .append("| YearMonth.of(2011, 10).plusMonths(2) |" + YearMonth.of(2011, 10).plusMonths(2) + "|").append(System.lineSeparator())
                .append("| YearMonth.of(2011, 10).minusYears(3) |" + YearMonth.of(2011, 10).minusYears(3) + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testMonthDay() {

        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| MonthDay.now() |" + MonthDay.now() + "|").append(System.lineSeparator())
                .append("| MonthDay.now(Clock.systemUTC()) |" + MonthDay.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| MonthDay.now(ZoneId.of(\"Europe/Paris\")) |" + MonthDay.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| MonthDay.of(5, 10)  |" + MonthDay.of(5, 10) + "|").append(System.lineSeparator())
                .append("| MonthDay.parse(\"--05-10\") |" + MonthDay.parse("--05-10") + "|").append(System.lineSeparator())
                .toString()
        );


        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| toString()| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| MonthDay.of(5, 10).getMonth() |" + MonthDay.of(5, 10).getMonth() + "|").append(System.lineSeparator())
                .append("| MonthDay.of(5, 10).getDayOfMonth()  |" + MonthDay.of(5, 10).getDayOfMonth() + "|").append(System.lineSeparator())
                .append("| MonthDay.of(5, 10).with(Month.JANUARY).withDayOfMonth(20) |" + MonthDay.of(5, 10).with(Month.JANUARY).withDayOfMonth(20) + "|").append(System.lineSeparator())
                .toString()
        );
    }

    private static void testZoneDateTime() {
        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| ZonedDateTime.now() |" + ZonedDateTime.now() + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.now(Clock.systemUTC()) |" + ZonedDateTime.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.now(ZoneId.of(\"Europe/Paris\")) |" + ZonedDateTime.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(\"Asia/Shanghai\")) |" + ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai")) + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.of(2010, 10, 1, 10, 20,30, 999, ZoneId.of(\"Asia/Tokyo\")) |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")) + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.parse(\"2010-10-01T10:20:30.000000999+09:00[Asia/Tokyo]\") |" + ZonedDateTime.parse("2010-10-01T10:20:30.000000999+09:00[Asia/Tokyo]") + "|").append(System.lineSeparator())
                .toString()
        );

        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getYear() |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getYear() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getMonth() |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getMonth() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getDayOfMonth() |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getDayOfMonth() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getDayOfYear() |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getDayOfYear() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getHour()  |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getHour() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getMinute() |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getMinute() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).getSecond() |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).getSecond() + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).plusYears(1)  |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).plusYears(1) + "|").append(System.lineSeparator())
                .append("|ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of(\"Asia/Tokyo\")).minusYears(1) |" + ZonedDateTime.of(2010, 10, 1, 10, 20, 30, 999, ZoneId.of("Asia/Tokyo")).minusYears(1) + "|").append(System.lineSeparator())
        );
    }

    private static void testLocalDateTime() {
        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| LocalDate.now() |" + LocalDateTime.now() + "|").append(System.lineSeparator())
                .append("| LocalDate.now(Clock.systemUTC()) |" + LocalDateTime.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| LocalDate.now(ZoneId.of(\"Europe/Paris\")) |" + LocalDateTime.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| LocalDateTime.of(2010, 10, 1, 10, 20, 30, 123456789) |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30, 123456789) + "|").append(System.lineSeparator())
                .append("| ZonedDateTime.parse(\"2010-10-01T10:20:30.123456789\") |" + LocalDateTime.parse("2010-10-01T10:20:30.123456789") + "|").append(System.lineSeparator())
                .toString()
        );

        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getYear() |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getYear() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getMonth() |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getMonth() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getDayOfMonth() |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getDayOfMonth() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getDayOfYear() |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getDayOfYear() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getHour()  |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getHour() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getMinute() |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getMinute() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).getSecond() |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).getSecond() + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).plusYears(1)  |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).plusYears(1) + "|").append(System.lineSeparator())
                .append("|LocalDateTime.of(2010, 10, 1, 10, 20, 30).minusYears(1) |" + LocalDateTime.of(2010, 10, 1, 10, 20, 30).minusYears(1) + "|").append(System.lineSeparator())
        );
    }

    private static void testLocalDate() {
        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| LocalDate.now() |" + LocalDate.now() + "|").append(System.lineSeparator())
                .append("| LocalDate.now(Clock.systemUTC()) |" + LocalDate.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| LocalDate.now(ZoneId.of(\"Europe/Paris\")) |" + LocalDate.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2015, 10, 1) |" + LocalDate.of(2015, 10, 1) + "|").append(System.lineSeparator())
                .append("| LocalDate.parse(\"2015 - 10 - 10\") |" + LocalDate.parse("2015-10-10") + "|").append(System.lineSeparator())
                .append("| LocalDate.ofYearDay(2016, 60) |" + LocalDate.ofYearDay(2016, 60) + "|").append(System.lineSeparator())
                .toString()
        );

        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| LocalDate.of(2010, 2, 3).getYear() |" + LocalDate.of(2010, 2, 3).getYear() + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010, 2, 3).getMonth() |" + LocalDate.of(2010, 2, 3).getMonth() + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010, 2, 3).getDayOfMonth() |" + LocalDate.of(2010, 2, 3).getDayOfMonth() + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010, 3, 3).getDayOfWeek() |" + LocalDate.of(2010, 3, 3).getDayOfWeek() + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010,1,1).withMonth(3).withYear(2016).withDayOfMonth(15)| " + LocalDate.of(2010, 1, 1).withMonth(3).withYear(2016).withDayOfMonth(15) + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010,1,1).withYear(2016).withDayOfYear(59) | " + LocalDate.of(2010, 1, 1).withYear(2016).withDayOfYear(59) + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010,1,1).minusYears(1).minusMonths(2).minusDays(3) | " + LocalDate.of(2010, 1, 1).minusYears(1).minusMonths(2).minusDays(3) + "|").append(System.lineSeparator())
                .append("| LocalDate.of(2010,1,1).plusYears(1).plusMonths(2).plusDays(3) | " + LocalDate.of(2010, 1, 1).plusYears(1).plusMonths(2).plusDays(3) + "|").append(System.lineSeparator())
        );

    }


    private static void testLocalTime() {

        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| LocalTime.now() |" + LocalTime.now() + "|").append(System.lineSeparator())
                .append("| LocalTime.now(Clock.systemUTC()) |" + LocalTime.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| LocalTime.now(ZoneId.of(\"Europe/Paris\")) |" + LocalTime.now(ZoneId.of("Europe/Paris")) + "|").append(System.lineSeparator())
                .append("| LocalTime.of(6, 10, 30) |" + LocalTime.of(6, 10, 30) + "|").append(System.lineSeparator())
                .append("| LocalTime.parse(\"10:20:30\") |" + LocalTime.parse("10:20:30") + " | ").append(System.lineSeparator())
                .append("| LocalTime.of(6, 10, 30, 999999999) |" + LocalTime.of(6, 10, 30, 999999999) + "|").append(System.lineSeparator())
                .toString()
        );

        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| LocalTime.of(10, 20, 30).getHour()|" + LocalTime.of(10, 20, 30).getHour() + "|").append(System.lineSeparator())
                .append("| LocalTime.of(10, 20, 30).getSecond()|" + LocalTime.of(10, 20, 30).getSecond() + "|").append(System.lineSeparator())
                .append("| LocalTime.of(10, 20, 30).getMinute()|" + LocalTime.of(10, 20, 30).getMinute() + "|").append(System.lineSeparator())
                .append("| LocalTime.of(10, 20, 30).withHour(11).withMinute(22).withSecond(33).withNano(44)| " + LocalTime.of(10, 20, 30).withHour(11).withMinute(22).withSecond(33).withNano(44) + "|").append(System.lineSeparator())
                .append("| LocalTime.of(10, 20, 30).minusHours(1).minusMinutes(1).minusSeconds(1).minusNanos(1)| " + LocalTime.of(10, 20, 30).minusHours(1).minusMinutes(1).minusSeconds(1).minusNanos(1) + "|").append(System.lineSeparator())
                .append("| LocalTime.of(10, 20, 30).plusHours(1).plusMinutes(1).plusSeconds(1).plusNanos(1)| " + LocalTime.of(10, 20, 30).plusHours(1).plusMinutes(1).plusSeconds(1).plusNanos(1) + "|").append(System.lineSeparator())

        );
    }

    private static void testInstant() {

        // エポック秒(1970-01-01T00:00:00Z)を持つ
        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|---|---| ").append(System.lineSeparator())
                .append("| Instant.now() |" + Instant.now()).append(System.lineSeparator())
                .append("| Instant.now(Clock.systemUTC()) |" + Instant.now(Clock.systemUTC()) + "|").append(System.lineSeparator())
                .append("| Instant.now(Clock.system(ZoneId.of(\"Asia/Shanghai\")))  |" + Instant.now(Clock.system(ZoneId.of("Asia/Shanghai"))) + "|").append(System.lineSeparator())
                .append("| Instant.parse(\"2016-10-10T10:20:30.123Z\")  |" + Instant.parse("2016-10-10T10:20:30.123Z") + "|").append(System.lineSeparator())
                .append("| Instant.EPOCH  |" + Instant.EPOCH + "|").append(System.lineSeparator())
                .append("| Instant.ofEpochMilli(10) |" + Instant.ofEpochMilli(10) + "|").append(System.lineSeparator())
                .append("| Instant.ofEpochSecond(1)  |" + Instant.ofEpochSecond(1) + "|").append(System.lineSeparator())
                .append("| Instant.ofEpochSecond(1, 2)  |" + Instant.ofEpochSecond(1, 2) + "|").append(System.lineSeparator())
                .toString()
        );




        Instant instant = Instant.parse("2016-10-10T10:00:00.000Z");


        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|---|---| ").append(System.lineSeparator())
                .append("| Instant instant = Instant.parse(\"2016-10-10T10:00:00.000Z\") |" + instant + "|").append(System.lineSeparator())
                .append("| instant.toEpochMilli() | " + instant.toEpochMilli() + " |").append(System.lineSeparator())
                .append("| instant.plusSeconds(30) | " + instant.plusSeconds(30)).append(System.lineSeparator())
                .append("| instant.minusSeconds(30) | " + instant.minusSeconds(30)).append(System.lineSeparator())
                .append("| instant.isAfter(instant) | " + instant.isAfter(instant)).append(System.lineSeparator())
                .append("| instant.isBefore(instant) | " + instant.isBefore(instant)).append(System.lineSeparator())
                .append("| instant.equals(instant) | " + instant.equals(instant)).append(System.lineSeparator())
                .toString()
        );
    }

    private static void testDuration() {

        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Duration.ofDays(2) | ").append(Duration.ofDays(2)).append("|").append(System.lineSeparator())
                .append("| Duration.ofHours(3) | ").append(Duration.ofHours(3)).append("|").append(System.lineSeparator())
                .append("| Duration.ofMinutes(40) | ").append(Duration.ofMinutes(40)).append("|").append(System.lineSeparator())
                .append("| Duration.parse(\"PT15M\") | ").append(Duration.parse("PT15M")).append("|").append(System.lineSeparator())
        );

        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Duration.ofDays(3).getUnits() | ").append(Duration.ofDays(3).getUnits()).append("|").append(System.lineSeparator())
                .append("| Duration.ofDays(3).toHours() | ").append(Duration.ofDays(3).toHours()).append("|").append(System.lineSeparator())
                .append("| Duration.ofDays(3).toMinutes() | ").append(Duration.ofDays(3).toMinutes()).append("|").append(System.lineSeparator())
                .append("| DDuration.ofDays(3).toMillis() | ").append(Duration.ofDays(3).toMillis()).append("|").append(System.lineSeparator())
                .append("| Duration.ofDays(3).plusDays(1) | ").append(Duration.ofDays(3).plusDays(1)).append("|").append(System.lineSeparator())
                .append("| Duration.ofDays(3).plusDays(-1) | ").append(Duration.ofDays(3).plusDays(-1)).append("|").append(System.lineSeparator())
        );
    }

    private static void testPeriod() {



        System.out.println(
            new StringBuilder()
                .append("| インスタンス取得| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Period.ofDays(2)| ").append(Period.ofDays(2)).append("|").append(System.lineSeparator())
                .append("| Period.ofMonths(3) | ").append(Period.ofMonths(3)).append("|").append(System.lineSeparator())
                .append("| Period.ofYears(4) | ").append(Period.ofYears(4)).append("|").append(System.lineSeparator())
                .append("| Period.parse(\"P4Y\") | ").append(Period.parse("P4Y")).append("|").append(System.lineSeparator())
        );

        System.out.println(
            new StringBuilder()
                .append("| 常用メソッド| 結果| ").append(System.lineSeparator())
                .append("|:---|:---| ").append(System.lineSeparator())
                .append("| Period.ofYears(3).getDays() | ").append(Period.ofYears(3).getDays()).append("|").append(System.lineSeparator())
                .append("| Period.ofYears(3).getMonths() | ").append(Period.ofYears(3).getMonths()).append("|").append(System.lineSeparator())
                .append("| Period.ofYears(3).getYears() | ").append(Period.ofYears(3).getYears()).append("|").append(System.lineSeparator())
                .append("| Period.ofYears(3).getUnits() | ").append(Period.ofYears(3).getUnits()).append("|").append(System.lineSeparator())
                .append("| Period.ofDays(3).plusDays(1) | ").append(Period.ofDays(3).plusDays(1)).append("|").append(System.lineSeparator())
                .append("| Period.ofDays(3).minusDays(1) | ").append(Period.ofDays(3).minusDays(1)).append("|").append(System.lineSeparator())
        );





    }

    private static void testClock() throws InterruptedException {
        // インスタンス取得
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
