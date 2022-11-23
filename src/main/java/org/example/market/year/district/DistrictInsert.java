//package org.example.market.year.district;
//
//import org.example.utils.FilePos;
//import org.example.utils.InitUtils;
//
//import java.util.List;
//
//public class DistrictInsert  {
//
//    public static void main(String[] args) {
//        ExtraSqlYearDistrict districtInsert = new ExtraSqlYearDistrict();
//        InitUtils initUtils = new InitUtils();
//        FilePosYearDistrict inputs = new FilePosYearDistrict();
//        List<String> list = initUtils.getInitSqlList(inputs, districtInsert, 0, 0, 0);
//        System.out.println(list.size());
//        for (String s :
//                list) {
//            InitUtils.saveAsFileWriter(s, inputs.outputFileInsert);
//        }
//        String extra;
//        StringBuilder sb = new StringBuilder();
//        extra = districtInsert.getString(sb);
//        InitUtils.saveAsFileWriter(extra, inputs.outputFileInsert);
//    }
//
//
//}
