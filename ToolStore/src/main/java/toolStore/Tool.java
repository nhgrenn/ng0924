package toolStore;

import toolStore.constants.Brand;
import toolStore.constants.DailyCharges;
import toolStore.constants.ToolCode;
import toolStore.constants.ToolType;

public class Tool {
    private final String toolCode;
    private final String toolType;
    private final String brand;
    private final float dailyCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public Tool(String code){
        code = code.toUpperCase();
        switch (code){
            case ToolCode.CHNS:
                toolCode = ToolCode.CHNS;
                toolType = ToolType.CHAINSAW;
                brand = Brand.STIHL;
                dailyCharge = DailyCharges.CHAINSAW;
                weekendCharge = false;
                holidayCharge = true;
                break;
            case ToolCode.LADW:
                toolCode = ToolCode.LADW;
                toolType = ToolType.LADDER;
                brand = Brand.WERNER;
                dailyCharge = DailyCharges.LADDER;
                weekendCharge = true;
                holidayCharge = false;
                break;
            case ToolCode.JAKD:
                toolCode = ToolCode.JAKD;
                toolType = ToolType.JACKHAMMER;
                brand = Brand.DEWALT;
                dailyCharge = DailyCharges.JACKHAMMER;
                weekendCharge = false;
                holidayCharge = false;
                break;
            case ToolCode.JAKR:
                toolCode = ToolCode.JAKR;
                toolType = ToolType.JACKHAMMER;
                brand = Brand.RIDGID;
                dailyCharge = DailyCharges.JACKHAMMER;
                weekendCharge = false;
                holidayCharge = false;
                break;
            default:
                throw new RuntimeException("Please enter valid string tool code: (CHNS, LADW, JAKD, JAKR)");
        }
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public String getBrand() {
        return brand;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolCode() {
        return toolCode;
    }

}
