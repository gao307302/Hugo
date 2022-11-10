package org.example.market.quarter.city;

import org.example.utils.FilePos;
import org.example.utils.InitUtils;

import java.util.List;

public class CityDelete {



    public static void main(String[] args) {
        InitUtils initUtils = new InitUtils();
        FilePos filePosQuarterCity = new FilePosQuarterCity();

        ExtraSqlYearDistrict districtInsert = new ExtraSqlYearDistrict();
        List<String> list = initUtils.getCityQuarterDeleteSqlList(filePosQuarterCity, districtInsert);
        System.out.println(list.size());
        for (String s :
                list) {
            InitUtils.saveAsFileWriter(s, FilePos.outputFileDelete);
        }
    }
}
