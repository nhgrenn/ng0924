package toolStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final LocalDate[] rentalPeriod;
    private final int discountPercent;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final int chargeDays;
    private final float preDiscountCharge;
    private final float finalCharge;
    private final float roundedDiscountAmount;

    public RentalAgreement(String toolCode, int rentalDayCount, int discount, String date) {
        tool = new Tool(toolCode);
        rentalDays = rentalDayCount;
        discountPercent = discount;
        String [] dateValues = date.split("/");

        LocalDate startDate = getLocalDate(dateValues);
        checkoutDate = startDate;
        rentalPeriod = new LocalDate[rentalDays];
        for (int i = 0; i < rentalDays; i++) {
            rentalPeriod[i] = startDate;
            startDate = startDate.plusDays(1);
        }
        dueDate = rentalPeriod[rentalDays-1];
        chargeDays = calculateChargeDays();
        preDiscountCharge = tool.getDailyCharge() * chargeDays;
        float discountAmount = preDiscountCharge * ((float) discountPercent / 100);
        BigDecimal bdRoundedDiscountAmount = new BigDecimal(Float.toString(discountAmount));
        bdRoundedDiscountAmount = bdRoundedDiscountAmount.setScale(2, RoundingMode.HALF_UP);
        roundedDiscountAmount = bdRoundedDiscountAmount.floatValue();
        finalCharge = preDiscountCharge - roundedDiscountAmount;
    }

    @Override
    public String toString() {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US);
        numberFormatter.setGroupingUsed(true);
        numberFormatter.setMinimumFractionDigits(2);
        numberFormatter.setMaximumFractionDigits(2);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return  "Tool code: " + tool.getToolCode() + "\n" +
                "Tool type: " + tool.getToolType() + "\n" +
                "Tool brand: " + tool.getBrand() + "\n" +
                "Rental days: " + rentalDays + "\n" +
                "Checkout date: " + checkoutDate.format(dateFormatter) + "\n" +
                "Due date: " + dueDate.format(dateFormatter)+ "\n" +
                "Daily rental charge: $" + tool.getDailyCharge() + "\n" +
                "Charge days: " + chargeDays + "\n" +
                "Pre-discount charge: $" + numberFormatter.format(preDiscountCharge) + "\n" +
                "Discount percent: " + discountPercent + "%\n" +
                "Discount amount: $" + numberFormatter.format(roundedDiscountAmount) + "\n" +
                "Final charge: $" + numberFormatter.format(finalCharge) + "\n";
    }

    private static LocalDate getLocalDate(String[] dateValues) {
        if (dateValues.length != 3) {
            throw new RuntimeException("Please enter date in format mm/dd/yy.");
        }
        if (dateValues[2].length() != 2) {
            throw new RuntimeException("Please enter 2 digit year (ex: '24' for year 2024)");
        }

        int yearInt = 2000 + Integer.parseInt(dateValues[2]);
        int monthInt = Integer.parseInt(dateValues[0]);
        int dayInt = Integer.parseInt(dateValues[1]);
        return LocalDate.of(yearInt, monthInt, dayInt);
    }

    private int calculateChargeDays() {
        int chargeDayCount = 0;
        LocalDate[] holidays = getObservedHolidays();

        for(LocalDate date : rentalPeriod) {
            // Weekend case
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                if (tool.isWeekendCharge()) { // Only charge if weekend charge
                    chargeDayCount++;
                }
            } else { // Weekday Case
                if (tool.isHolidayCharge()) { // Charge if holiday charge
                    chargeDayCount ++;
                } else { // Charge if no holiday charge but not a holiday
                    if (!date.isEqual(holidays[0]) && !date.isEqual(holidays[1])) {
                        chargeDayCount++;
                    }
                }
            }
        }

        return chargeDayCount;
    }

    private LocalDate[] getObservedHolidays() {
        LocalDate[] holidays = new LocalDate[2];
        holidays[0] = getObservedIndependenceDay();
        holidays[1] = getObservedLaborDay();
        return holidays;
    }

    private LocalDate getObservedLaborDay() {
        int year = checkoutDate.getYear();
        LocalDate laborDay = LocalDate.of(year, Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }
        return laborDay;
    }

    private LocalDate getObservedIndependenceDay() {
        int year = checkoutDate.getYear();
        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        return independenceDay;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public float getFinalCharge() {
        return finalCharge;
    }

    public float getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate[] getRentalPeriod() {
        return rentalPeriod;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Tool getTool() {
        return tool;
    }

    public float getRoundedDiscountAmount() {
        return roundedDiscountAmount;
    }
}
