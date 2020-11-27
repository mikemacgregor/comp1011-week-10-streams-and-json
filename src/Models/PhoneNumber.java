package Models;

public class PhoneNumber {
    private String areaCode;
    private String cityCode;
    private String lineNumber;

    public PhoneNumber(String areaCode, String cityCode, String lineNumber) {
        setAreaCode(areaCode);
        setCityCode(cityCode);
        setLineNumber(lineNumber);

    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        if (areaCode.matches("[2-9]\\d{2}"))
            this.areaCode = areaCode;
        else
            throw new IllegalArgumentException("This area code is not valid");
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        if (cityCode.matches("[2-9]\\d{2}"))
            this.cityCode = cityCode;
        else
            throw new IllegalArgumentException("This city code is not valid");
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        if (lineNumber.matches("\\d{4}"))
            this.lineNumber = lineNumber;
        else
            throw new IllegalArgumentException("This line number is not valid");
    }

    @Override
    public String toString()
    {
        return String.format("(%s) %s-%s", areaCode, cityCode, lineNumber);
    }
}
