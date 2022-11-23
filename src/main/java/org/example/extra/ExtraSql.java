package org.example.extra;

public interface ExtraSql {
    void insert(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType);
    void update(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String cityName, String districtName);

    // 最后额外sql
    String insertEnding(StringBuilder sb);
    String updateEnding(StringBuilder sb);
    String ending(StringBuilder sb);

}
