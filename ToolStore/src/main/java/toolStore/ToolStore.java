package toolStore;

public interface ToolStore {
    public default RentalAgreement checkout(String toolCode, int rentalDayCount, int discount, String date) {
        RentalAgreement rentalAgreement = new RentalAgreement(toolCode, rentalDayCount, discount, date);
        return rentalAgreement;
    }
}
