package org.example.market.year.city;

import org.example.extra.ExtraSql;
import org.example.market.year.district.FilePosYearDistrict;

import java.sql.Timestamp;

public class ExtraSqlYearDistrict implements ExtraSql {
    @Override
    public void extrasolar(StringBuilder sb, String quarter, String year) {

    }

    @Override
    public void updateMarket(StringBuilder sb, String quarter, String year, String cityName, String districtName) {

    }


    // 额外sql
    protected String getString(StringBuilder sb) {
        return "";
    }
}
