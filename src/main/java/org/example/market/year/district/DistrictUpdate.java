//package org.example.market.year.district;
//
//import org.example.utils.FilePos;
//import org.example.utils.InitUtils;
//
//import java.util.List;
//
//public class DistrictUpdate {
//
//    public static void main(String[] args) {
//        ExtraSqlYearDistrict districtInsert = new ExtraSqlYearDistrict();
//        InitUtils initUtils = new InitUtils();
//        FilePosYearDistrict filePosYearDistrict = new FilePosYearDistrict();
//        List<String> list = initUtils.getInitSqlList(filePosYearDistrict, districtInsert, 1, 0, 0);
//        System.out.println(list.size());
//        for (String s :
//                list) {
//            InitUtils.saveAsFileWriter(s, filePosYearDistrict.outputFileUpdate);
//        }
//        String extra;
//        StringBuilder sb = new StringBuilder();
//        extra = districtInsert.getString(sb);
//        InitUtils.saveAsFileWriter(extra, filePosYearDistrict.outputFileUpdate);
//    }
//
//
//}
