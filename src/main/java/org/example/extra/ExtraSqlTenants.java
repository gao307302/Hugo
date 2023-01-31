package org.example.extra;

public class ExtraSqlTenants implements ExtraSql {
    @Override
    public void insert(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String developer, String fileLocation) {
    }

    @Override
    public void update(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String cityName, String districtName, String fileLocation) {
        sb.append(" where data_date = '").append(year).append("'");
        sb.append(" and city_name = '").append(cityName).append("';\n");
    }

    @Override
    public String insertEnding(StringBuilder sb) {
        return "";
    }

    @Override
    public String updateEnding(StringBuilder sb) {
        return "";
    }

    @Override
    public String ending(StringBuilder sb) {
        return "";
    }
}
