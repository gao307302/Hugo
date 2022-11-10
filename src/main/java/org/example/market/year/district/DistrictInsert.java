package org.example.market.year.district;

import org.example.utils.FilePos;
import org.example.utils.InitUtils;

import java.util.List;

public class DistrictInsert  {

    public static void main(String[] args) {
        ExtraSqlYearDistrict districtInsert = new ExtraSqlYearDistrict();
        InitUtils initUtils = new InitUtils();
        FilePos dad = new FilePosYearDistrict();
        List<String> list = initUtils.getInitSqlList(dad, districtInsert, 0);
        System.out.println(list.size());
        for (String s :
                list) {
            InitUtils.saveAsFileWriter(s, FilePosYearDistrict.outputFileInsert);
        }
        String extra;
        StringBuilder sb = new StringBuilder();
        extra = districtInsert.getString(sb);
        InitUtils.saveAsFileWriter(extra, FilePosYearDistrict.outputFileInsert);
    }


}
