//package org.example.market.year.district;
//
//import org.example.extra.ExtraSql;
//
//import java.sql.Timestamp;
//
//public class ExtraSqlYearDistrict implements ExtraSql {
//    @Override
//    public void insert(StringBuilder sb, String quarter, String year) {
//        sb.append(", date_year = '").append(year).append("',")
//                .append(" date_quarter = ").append(quarter).append(",")
//                .append(" data_type = ").append(2).append(",")
//                .append(" create_time = '").append(new Timestamp(System.currentTimeMillis())).append("',")
//                .append(" data_date = '").append(year).append("';\n");
//    }
//
//    @Override
//    public void update(StringBuilder sb, String quarter, String year, String cityName, String districtName) {
//        sb.append(" where date_year = '").append(year).append("'")
//                .append(" and city_name = '").append(cityName).append("'")
//                .append(" and district_name = '").append(districtName).append("'")
//                .append(" and data_type = ").append(2)
//                .append(" and data_date = '").append(year).append("';\n");
//    }
//
//    // 额外sql
//    protected String getString(StringBuilder sb) {
//        FilePosYearDistrict filePosYearDistrict = new FilePosYearDistrict();
//        String extra;
//        if(filePosYearDistrict.newQuarter) {
//            sb.append("UPDATE ").append(filePosYearDistrict.tableName)
//                    .append(" SET date_order = 0 WHERE date_order = 2;\n");
//            sb.append("UPDATE ").append(filePosYearDistrict.tableName)
//                    .append(" SET date_order = 2 WHERE date_order = 1;\n");
//            sb.append("UPDATE ").append(filePosYearDistrict.tableName)
//                    .append(" w SET w.date_order = 1 WHERE w.date_year = '").append(filePosYearDistrict.year).append("'")
//                    .append(" and w.date_quarter = ").append(filePosYearDistrict.quarter)
//                    .append(";\n");
//        }
//        sb.append("UPDATE `").append(filePosYearDistrict.tableName).append("` w " +
//                "left join `b_location_business` b " +
//                "on b.adcode = w.city_code " +
//                "set w.city_cluster_id = b.city_cluster_id ");
//        sb.append("where isnull(w.city_cluster_id);\n");
//        extra = sb.toString();
//        return extra;
//    }
//}
