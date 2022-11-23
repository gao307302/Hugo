//package org.example.data.traffic.city;
//
//import org.example.extra.ExtraSqlTraffic;
//import org.example.utils.InitUtils;
//
//import java.util.List;
//
//public class CityUpdate {
//
//    public static void main(String[] args) {
//        ExtraSqlTraffic districtInsert = new ExtraSqlTraffic();
//        InitUtils initUtils = new InitUtils();
//        FilePosTrafficCity inputs = new FilePosTrafficCity();
//        List<String> list = initUtils.getInitSqlList(inputs, districtInsert, 1, 1, 0);
//        System.out.println(list.size());
//        for (String s :
//                list) {
//            InitUtils.saveAsFileWriter(s, inputs.outputFileUpdate);
//        }
//        String extra;
//        StringBuilder sb = new StringBuilder();
//        extra = districtInsert.getString(sb);
//        InitUtils.saveAsFileWriter(extra, inputs.outputFileUpdate);
//    }
//}
