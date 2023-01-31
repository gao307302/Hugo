package org.example.extra;

import java.util.Objects;

public class ExtraSqlDynamic implements ExtraSql {
    @Override
    public void insert(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String developer, String fileLocation) {

        sb.append(" valuation_quarter = '").append(year).append("Q").append(quarter).append("';\n");
        sb.append("update `t_warehouse_dynamic` w " +
                "left join `t_warehouse_static_present` wsp " +
                "on wsp.vas_id = w.vas_id " +
                "set w.warehouse_id = wsp.id ");
        sb.append("where w.vas_id = '").append(vasId).append("'")
                .append(" and w.date_year = '").append(year).append("'")
                .append(" and w.date_quarter = ").append(quarter)
                .append(";\n");
    }

    @Override
    public void update(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String cityName, String districtName, String fileLocation) {

        sb.append(" where vas_id = '").append(vasId).append("'")
                .append(" and date_year = '").append(year).append("'")
                .append(" and date_quarter = ").append(quarter)
                .append(";\n");
        sb.append("update `t_warehouse_dynamic` w " +
                "left join `t_warehouse_static_present` wsp " +
                "on wsp.vas_id = w.vas_id " +
                "set w.warehouse_id = wsp.id ");
        sb.append("where w.vas_id = '").append(vasId).append("'")
                .append(" and w.date_year = '").append(year).append("'")
                .append(" and w.date_quarter = ").append(quarter)
                .append(";\n");
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
