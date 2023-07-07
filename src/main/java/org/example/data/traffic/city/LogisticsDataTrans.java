package org.example.data.traffic.city;

import javafx.util.Pair;
import org.example.extra.*;
import org.example.utils.InitUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LogisticsDataTrans {

    public HashMap<String, HashMap<String, Integer>> typeMap = new HashMap<>();
    public List<String> toNumList = new ArrayList<>();
    public Pair<String, String> geoPair;

    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);

        LogisticsDataTrans logisticsDataTrans = new LogisticsDataTrans();
        System.out.println("请输入 源文件夹：");
        System.out.println("请输入 源文件名：");
        System.out.println("请输入 输出文件夹：");
        System.out.println("请输入 操作类型（0 insert 1 update 2 delete）：");
        System.out.println("请输入 数据类型（0 market year 1 market quarter 2 traffic 3 economics 4 static 5 dynamic 6 tenants）：");
        System.out.println("请输入 行政区划类型（1 city 2 district）：");
        System.out.println("请输入 sheet number：");
        String sourseFolder = scan.nextLine();
        String sourceFile =scan.nextLine();
        String exportFolder = scan.nextLine();
        int manipulateType = Integer.parseInt(scan.nextLine());
        int dataType = Integer.parseInt(scan.nextLine());
        int districtType = Integer.parseInt(scan.nextLine());
        int sheetNum = Integer.parseInt(scan.nextLine());
//        String sourseFolder = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
//        String sourceFile = "市场_Q4_230120.xlsx";
//        String exportFolder = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\230130\\";
//
//        // 0 insert 1 update 2 delete
//        int manipulateType = 0;
//
//        // 0 market year 1 market quarter 2 traffic 3 economics 4 static 5 dynamic
//        int dataType = 6;
//
//        // 1 city 2 district
//        int districtType = 2;
//
//        int sheetNum = 0;

        ExtraSql extraSql = null;
        InitUtils initUtils = new InitUtils();
        FilePosTrafficCity inputs = new FilePosTrafficCity();
        inputs.fileLocation = sourseFolder;
        inputs.excelSourceFile = sourceFile;
        inputs.updatedFile = sourseFolder + sourceFile;
        inputs.dataType = String.valueOf(districtType);
        switch (dataType) {
            case 0 :
                inputs.tableName = "t_market_overview_year";
                inputs.datePair = new Pair<>("年度", "");
                if(districtType == 2) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\marketDistrictYear.xlsx";
                    inputs.outputFile = exportFolder + "marketDistrictYear";
                    extraSql = new ExtraSqlMarket();
                } else if(districtType == 1) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\marketCityYear.xlsx";
                    inputs.outputFile = exportFolder + "marketCityYear";
                    extraSql = new ExtraSqlMarket();
                }
                break;
            case 1 :
                inputs.tableName = "t_market_overview_quarter";
                if(districtType == 2) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\marketDistrictQuarter.xlsx";
                    inputs.outputFile = exportFolder + "marketDistrictQuarter";
                    extraSql = new ExtraSqlMarket();
                } else if(districtType == 1) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\marketCityQuarter.xlsx";
                    inputs.outputFile = exportFolder + "marketCityQuarter";
                    extraSql = new ExtraSqlMarket();
                }
                break;
            case 2 :
                inputs.tableName = "t_traffic_data";
                logisticsDataTrans.initTraffic();
                inputs.typeMap = logisticsDataTrans.typeMap;
                if(districtType == 2) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\trafficDistrict.xlsx";
                    inputs.outputFile = exportFolder + "trafficDistrict";
                    extraSql = new ExtraSqlTraffic();
                } else if(districtType == 1) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\trafficCity.xlsx";
                    inputs.outputFile = exportFolder + "trafficCity";
                    extraSql = new ExtraSqlTraffic();
                }
                break;
            case 3 :
                inputs.tableName = "t_economics_data";
                if(districtType == 2) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\economicsDistrict.xlsx";
                    inputs.outputFile = exportFolder + "economicsDistrict";
                    extraSql = new ExtraSqlEconomics();
                } else if(districtType == 1) {
                    inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\economicsCity.xlsx";
                    inputs.outputFile = exportFolder + "economicsCity";
                    extraSql = new ExtraSqlEconomics();
                }
                break;
            case 4 :
                inputs.tableName = "t_warehouse_static";
                inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\warehouseStatic.xlsx";
                inputs.outputFile = exportFolder + "warehouseStatic";
                extraSql = new ExtraSqlStatic();
                logisticsDataTrans.initWarehouse();
                inputs.typeMap = logisticsDataTrans.typeMap;
                inputs.geoPair = logisticsDataTrans.geoPair;
                inputs.toNumList = logisticsDataTrans.toNumList;
                break;
            case 5 :
                inputs.tableName = "t_warehouse_dynamic";
                inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\warehouseDynamic.xlsx";
                inputs.outputFile = exportFolder + "warehouseDynamic";
                extraSql = new ExtraSqlDynamic();
                inputs.datePair = new Pair<>("Valuation_Year", "Valuation_Quarter");
                break;
            case 6 :
                inputs.tableName = "t_tenants";
                inputs.sqlColumnName = sourseFolder +  "excelDatabaseMapping\\tenants.xlsx";
                inputs.outputFile = exportFolder + "tenants";
                extraSql = new ExtraSqlTenants();
                break;
        }
        switch (manipulateType) {
            case 0 :
                inputs.sheetNumInsert = sheetNum;
                inputs.outputFile = inputs.outputFile + "Insert.sql";
                break;
            case 1 :
                inputs.sheetNumUpdate = sheetNum;
                inputs.outputFile = inputs.outputFile + "Update.sql";
                break;
            case 2 :
                inputs.sheetNumDelete = sheetNum;
                inputs.outputFile = inputs.outputFile + "Delete.sql";
                break;
        }
        inputs.sheetNum = sheetNum;
        List<String> list = initUtils.getInitSqlList(inputs, extraSql, manipulateType, districtType, dataType);
        System.out.println("总条数：" + list.size());
        for (String s :
                list) {
            InitUtils.saveAsFileWriter(s, inputs.outputFile);
        }
        String extra;
        StringBuilder sb = new StringBuilder();
        if(manipulateType == 0) {
            extra = extraSql.insertEnding(sb);
        } else if(manipulateType == 1){
            extra = extraSql.updateEnding(sb);
        } else {
            extra = "";
        }
        InitUtils.saveAsFileWriter(extra, inputs.outputFile);
    }


    //仓库字段整理
    private void initWarehouse() {
        geoPair = new Pair<>("确认lng", "确认lat");
        toNumList.add("园区最大可进车型车长(m)");
        toNumList.add("月台宽度");
        toNumList.add("月台高度");
        toNumList.add("雨棚尺寸");
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.put("Completed", 1);
        tempMap.put("completed", 1);
        tempMap.put(" Completed", 1);
        tempMap.put("CIP", 2);
        tempMap.put("Land", 3);
        tempMap.put("Completed + CIP", 4);
        tempMap.put("Completed + Land", 5);
        tempMap.put("renovating", 6);
        typeMap.put("建成状态", tempMap);
        HashMap<String, Integer> tempMap1 = new HashMap<>();
        tempMap1.put("Bonded", 1);
        tempMap1.put("Non-Bonded", 2);
        tempMap1.put("non-Bonded", 2);
        tempMap1.put("Non-bonded", 2);
        tempMap1.put("non-bonded", 2);
        tempMap1.put("Non-Bonded/Bonded", 3);
        tempMap1.put("Non-bonded/Bonded", 3);
        typeMap.put("是否保税", tempMap1);
        HashMap<String, Integer> tempMap2 = new HashMap<>();
        tempMap2.put("冷库", 1);
        tempMap2.put("常温库", 2);
        tempMap2.put("冷库/常温库", 3);
        tempMap2.put("常温库/冷库", 3);
        tempMap2.put("恒温库", 4);
        tempMap2.put("暖库", 5);
        typeMap.put("仓库类型", tempMap2);
        HashMap<String, Integer> tempMap3 = new HashMap<>();
        tempMap3.put("ColdStorage", 1);
        tempMap3.put("ColdStorage&STDWarehouse", 2);
        tempMap3.put("ExportSupervisoryWarehouse", 3);
        tempMap3.put("Warehouse", 4);
        tempMap3.put("warehouse", 4);
        tempMap3.put("Warehouse/ColdStorage", 5);
        tempMap3.put("ColdStorage/Warehouse", 5);
        tempMap3.put("Warehouse/Workshops", 6);
        tempMap3.put("BTSWarehouse", 7);
        tempMap3.put("Warehouse/port logistics", 8);
        tempMap3.put("Export supervisory ColdStorage", 9);
        typeMap.put("仓库分类", tempMap3);
        HashMap<String, Integer> tempMap4 = new HashMap<>();
        tempMap4.put("单面卸货", 1);
        tempMap4.put("双面卸货", 2);
        tempMap4.put("三面卸货", 3);
        typeMap.put("卸货面", tempMap4);
        HashMap<String, Integer> tempMap5 = new HashMap<>();
        tempMap5.put("丙二类消防", 1);
        tempMap5.put("丙一类消防", 2);
        tempMap5.put("乙类消防", 3);
        tempMap5.put("丙类消防", 4);
        tempMap5.put("戊类消防", 5);
        tempMap5.put("甲类消防", 6);
        typeMap.put("消防等级", tempMap5);
        HashMap<String, Integer> tempMap6 = new HashMap<>();
        tempMap6.put("是", 1);
        tempMap6.put("否", 2);
        typeMap.put("是否是危险品库", tempMap6);
        HashMap<String, Integer> tempMap7 = new HashMap<>();
        tempMap7.put("是", 1);
        tempMap7.put("否", 2);
        typeMap.put("是否是厂房", tempMap7);
        HashMap<String, Integer> tempMap8 = new HashMap<>();
        tempMap8.put("是", 1);
        tempMap8.put("否", 2);
        typeMap.put("是否有仓配", tempMap8);
        HashMap<String, Integer> tempMap9 = new HashMap<>();
        tempMap9.put("是", 1);
        tempMap9.put("否", 2);
        typeMap.put("是否是高标仓", tempMap9);
        HashMap<String, Integer> tempMap10 = new HashMap<>();
        tempMap10.put("是", 1);
        tempMap10.put("否", 2);
        typeMap.put("是否原房东", tempMap10);
        HashMap<String, Integer> tempMap11 = new HashMap<>();
        tempMap11.put("是", 1);
        tempMap11.put("否", 2);
        typeMap.put("是否近机场", tempMap11);
        HashMap<String, Integer> tempMap12 = new HashMap<>();
        tempMap12.put("是", 1);
        tempMap12.put("否", 2);
        typeMap.put("是否交通便利", tempMap12);
        HashMap<String, Integer> tempMap13 = new HashMap<>();
        tempMap13.put("是", 1);
        tempMap13.put("否", 2);
        typeMap.put("可注册", tempMap13);
        HashMap<String, Integer> tempMap14 = new HashMap<>();
        tempMap14.put("是", 1);
        tempMap14.put("否", 2);
        typeMap.put("要求落税", tempMap14);
        HashMap<String, Integer> tempMap15 = new HashMap<>();
        tempMap15.put("是", 1);
        tempMap15.put("否", 2);
        typeMap.put("是否可分割", tempMap15);
        HashMap<String, Integer> tempMap16 = new HashMap<>();
        tempMap16.put("是", 1);
        tempMap16.put("否", 2);
        typeMap.put("是否有重型机械", tempMap16);
        HashMap<String, Integer> tempMap17 = new HashMap<>();
        tempMap17.put("是", 1);
        tempMap17.put("否", 2);
        typeMap.put("是否有坡道", tempMap17);
        HashMap<String, Integer> tempMap18 = new HashMap<>();
        tempMap18.put("是", 1);
        tempMap18.put("否", 2);
        typeMap.put("是否有斜坡", tempMap18);
        HashMap<String, Integer> tempMap20 = new HashMap<>();
        tempMap20.put("Business Park", 1);
        tempMap20.put("RP", 2);
        tempMap20.put("Logistics Park", 3);
        typeMap.put("BP/RP/LP", tempMap20);
        HashMap<String, Integer> tempMap21 = new HashMap<>();
        tempMap21.put("地砖", 1);
        tempMap21.put("水泥", 2);
        tempMap21.put("环氧", 3);
        tempMap21.put("耐磨金刚砂", 4);
        typeMap.put("地坪材质", tempMap21);
        HashMap<String, Integer> tempMap22 = new HashMap<>();
        tempMap22.put("工业用地", 1);
        tempMap22.put("物流用地", 2);
        tempMap22.put("物流/工业用地", 3);
        typeMap.put("土地信息", tempMap22);
        HashMap<String, Integer> tempMap23 = new HashMap<>();
        tempMap23.put("国有土地", 1);
        tempMap23.put("集体土地", 2);
        typeMap.put("土地性质", tempMap23);
        HashMap<String, Integer> tempMap24 = new HashMap<>();
        tempMap24.put("可协助", 1);
        tempMap24.put("不可协助", 2);
        typeMap.put("协助办环评", tempMap24);
        HashMap<String, Integer> tempMap25 = new HashMap<>();
        tempMap25.put("大开发商", 1);
        tempMap25.put("本地开发商", 2);
        typeMap.put("开发商属性", tempMap25);
        HashMap<String, Integer> tempMap26 = new HashMap<>();
        tempMap26.put("是", 1);
        tempMap26.put("否", 2);
        typeMap.put("是否是楼库", tempMap26);
        HashMap<String, Integer> tempMap27 = new HashMap<>();
        tempMap27.put("是", 1);
        tempMap27.put("否", 2);
        typeMap.put("是否有电梯", tempMap27);
        HashMap<String, Integer> tempMap28 = new HashMap<>();
        tempMap28.put("是", 1);
        tempMap28.put("否", 2);
        typeMap.put("是否真实日期", tempMap28);
        HashMap<String, Integer> tempMap29 = new HashMap<>();
        tempMap29.put("是", 1);
        tempMap29.put("否", 2);
        typeMap.put("是否真实时间", tempMap29);
    }


    //字段整理
    private void initTraffic() {
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.put("无", 0);
        tempMap.put("大", 3);
        tempMap.put("中", 2);
        tempMap.put("小", 1);
        typeMap.put("铁路枢纽能级", tempMap);
        HashMap<String, Integer> tempMap1 = new HashMap<>();
        tempMap1.put("无", 0);
        tempMap1.put("大", 3);
        tempMap1.put("中", 2);
        tempMap1.put("小", 1);
        typeMap.put("水运枢纽能级", tempMap1);
        HashMap<String, Integer> tempMap2 = new HashMap<>();
        tempMap2.put("无", 0);
        tempMap2.put("大", 3);
        tempMap2.put("中", 2);
        tempMap2.put("小", 1);
        typeMap.put("航空枢纽能级", tempMap2);
    }
}
