package org.example.extra;

import java.util.Objects;

public class ExtraSqlTraffic implements ExtraSql {
    @Override
    public void insert(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String developer, String fileLocation) {
        sb.append(" date_quarter = 0,")
                .append(" data_type = ").append(districtType).append(",")
                .append(" data_date = '").append(year).append("';\n");
    }

    @Override
    public void update(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String cityName, String districtName, String fileLocation) {
        sb.append(" where date_year = '").append(year).append("'")
                .append(" and data_type = ").append(districtType);
        if(districtName != null) {
            sb.append(" and district_name = '").append(districtName).append("'");
        }
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
