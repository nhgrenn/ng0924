package toolStore;

public class RentalAgreement {
    private final Tool tool;

    public RentalAgreement(String toolCode, int rentalDayCount, int discount, String date) {
        tool = new Tool(toolCode);
    }
}
