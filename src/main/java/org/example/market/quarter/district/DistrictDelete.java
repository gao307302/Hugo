package org.example.market.quarter.district;

import org.example.market.quarter.city.ExtraSqlYearDistrict;
import org.example.market.quarter.city.FilePosQuarterCity;
import org.example.utils.FilePos;
import org.example.utils.InitUtils;

import java.util.List;

public class DistrictDelete {



    public static void main(String[] args) {
        InitUtils initUtils = new InitUtils();
        FilePos filePosQuarterCity = new FilePosQuarterCity();
        ExtraSqlYearDistrict districtInsert = new ExtraSqlYearDistrict();
        List<String> list = initUtils.getDistrictQuarterDeleteSqlList(filePosQuarterCity, districtInsert);
        System.out.println(list.size());
        for (String s :
                list) {
            InitUtils.saveAsFileWriter(s, FilePos.outputFileDelete);
        }
    }
}
