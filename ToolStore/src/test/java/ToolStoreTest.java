import org.junit.jupiter.api.Test;
import toolStore.RentalAgreement;
import toolStore.ToolStore;
import toolStore.constants.Brand;
import toolStore.constants.DailyCharges;
import toolStore.constants.ToolCode;
import toolStore.constants.ToolType;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToolStoreTest {

    public static final float TOLERANCE = 0.000001F;

    @Test
    public void discountExceptionTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ToolStore.checkout("JAKR", "9/3/15", 5, 101);
        });
        System.out.println("-- Test case 1 --");
        System.out.println("Exception message: " + exception.getMessage());
    }

    @Test
    public void ladderTest() {
        RentalAgreement rentalAgreement = ToolStore.checkout("LADW", "7/2/20", 3, 10);
        assertEquals(ToolCode.LADW, rentalAgreement.getTool().getToolCode());
        assertEquals(ToolType.LADDER,  rentalAgreement.getTool().getToolType());
        assertEquals(Brand.WERNER, rentalAgreement.getTool().getBrand());
        assertEquals(3, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, Month.JULY, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, Month.JULY, 4), rentalAgreement.getDueDate());
        assertEquals(DailyCharges.LADDER, rentalAgreement.getTool().getDailyCharge());
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals(3.98F, rentalAgreement.getPreDiscountCharge(), TOLERANCE);
        assertEquals(10, rentalAgreement.getDiscountPercent());
        assertEquals(0.4F, rentalAgreement.getRoundedDiscountAmount(), TOLERANCE);
        assertEquals(3.58F, rentalAgreement.getFinalCharge(), TOLERANCE);

        System.out.println("-- Test case 2 --");
        System.out.println(rentalAgreement);
    }

    @Test
    public void chainsawTest() {
        RentalAgreement rentalAgreement = ToolStore.checkout("CHNS", "7/2/15", 5, 25);
        assertEquals(ToolCode.CHNS, rentalAgreement.getTool().getToolCode());
        assertEquals(ToolType.CHAINSAW,  rentalAgreement.getTool().getToolType());
        assertEquals(Brand.STIHL, rentalAgreement.getTool().getBrand());
        assertEquals(5, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, Month.JULY, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, Month.JULY, 6), rentalAgreement.getDueDate());
        assertEquals(DailyCharges.CHAINSAW, rentalAgreement.getTool().getDailyCharge());
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(4.47F, rentalAgreement.getPreDiscountCharge(), TOLERANCE);
        assertEquals(25, rentalAgreement.getDiscountPercent());
        assertEquals(1.12F, rentalAgreement.getRoundedDiscountAmount(), TOLERANCE);
        assertEquals(3.35F, rentalAgreement.getFinalCharge(), TOLERANCE);

        System.out.println("-- Test case 3 --");
        System.out.println(rentalAgreement);
    }

    @Test
    public void dewaltJackhammerTest() {
        RentalAgreement rentalAgreement = ToolStore.checkout("JAKD", "9/3/15", 6, 0);
        assertEquals(ToolCode.JAKD, rentalAgreement.getTool().getToolCode());
        assertEquals(ToolType.JACKHAMMER,  rentalAgreement.getTool().getToolType());
        assertEquals(Brand.DEWALT, rentalAgreement.getTool().getBrand());
        assertEquals(6, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, Month.SEPTEMBER, 3), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, Month.SEPTEMBER, 8), rentalAgreement.getDueDate());
        assertEquals(DailyCharges.JACKHAMMER, rentalAgreement.getTool().getDailyCharge());
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(8.97F, rentalAgreement.getPreDiscountCharge(), TOLERANCE);
        assertEquals(0, rentalAgreement.getDiscountPercent());
        assertEquals(0F, rentalAgreement.getRoundedDiscountAmount(), TOLERANCE);
        assertEquals(8.97F, rentalAgreement.getFinalCharge(), TOLERANCE);

        System.out.println("-- Test case 4 --");
        System.out.println(rentalAgreement);
    }

    @Test
    public void rigidJackhammerNoDiscountTest() {
        RentalAgreement rentalAgreement = ToolStore.checkout("JAKR", "7/2/15", 9, 0);
        assertEquals(ToolCode.JAKR, rentalAgreement.getTool().getToolCode());
        assertEquals(ToolType.JACKHAMMER,  rentalAgreement.getTool().getToolType());
        assertEquals(Brand.RIDGID, rentalAgreement.getTool().getBrand());
        assertEquals(9, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, Month.JULY, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, Month.JULY, 10), rentalAgreement.getDueDate());
        assertEquals(DailyCharges.JACKHAMMER, rentalAgreement.getTool().getDailyCharge());
        assertEquals(6, rentalAgreement.getChargeDays());
        assertEquals(17.94F, rentalAgreement.getPreDiscountCharge(), TOLERANCE);
        assertEquals(0, rentalAgreement.getDiscountPercent());
        assertEquals(0F, rentalAgreement.getRoundedDiscountAmount(), TOLERANCE);
        assertEquals(17.94F, rentalAgreement.getFinalCharge(), TOLERANCE);

        System.out.println("-- Test case 5 --");
        System.out.println(rentalAgreement);
    }

    @Test
    public void rigidJackhammerDiscountTest() {
        RentalAgreement rentalAgreement = ToolStore.checkout("JAKR", "7/2/20", 4, 50);
        assertEquals(ToolCode.JAKR, rentalAgreement.getTool().getToolCode());
        assertEquals(ToolType.JACKHAMMER,  rentalAgreement.getTool().getToolType());
        assertEquals(Brand.RIDGID, rentalAgreement.getTool().getBrand());
        assertEquals(4, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, Month.JULY, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, Month.JULY, 5), rentalAgreement.getDueDate());
        assertEquals(DailyCharges.JACKHAMMER, rentalAgreement.getTool().getDailyCharge());
        assertEquals(1, rentalAgreement.getChargeDays());
        assertEquals(2.99F, rentalAgreement.getPreDiscountCharge(), TOLERANCE);
        assertEquals(50, rentalAgreement.getDiscountPercent());
        assertEquals(1.50F, rentalAgreement.getRoundedDiscountAmount(), TOLERANCE);
        assertEquals(1.49F, rentalAgreement.getFinalCharge(), TOLERANCE);

        System.out.println("-- Test case 6 --");
        System.out.println(rentalAgreement);
    }
}
