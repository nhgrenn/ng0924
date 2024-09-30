package toolStore;

public interface ToolStore {
    static RentalAgreement checkout(String toolCode, String date, int rentalDayCount, int discount) {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Please enter a rental day count greater than or equal to 1.");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Please enter a discount value between 0-100.");
        }

        return new RentalAgreement(toolCode, rentalDayCount, discount, date);
    }
}
