//package org.example.warehouse.staticData;
//
//import javafx.util.Pair;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//public class WarehouseStaticInsert {
//
//    private static final HashMap<String, HashMap<String, Integer>> typeMap = new HashMap<>();
//    private static final List<String> toNumList = new ArrayList<>();
//    private static Pair<String, String> geoPair;
//
//    //字段整理
//    private void init() {
//        geoPair = new Pair<>("确认lng", "确认lat");
//        toNumList.add("园区最大可进车型车长(m)");
//        toNumList.add("月台宽度");
//        toNumList.add("月台高度");
//        toNumList.add("雨棚尺寸");
//        HashMap<String, Integer> tempMap = new HashMap<>();
//        tempMap.put("Completed", 1);
//        tempMap.put(" Completed", 1);
//        tempMap.put("completed", 1);
//        tempMap.put("CIP", 2);
//        tempMap.put("Land", 3);
//        tempMap.put("Completed + CIP", 4);
//        tempMap.put("Completed + Land", 5);
//        typeMap.put("建成状态", tempMap);
//        HashMap<String, Integer> tempMap1 = new HashMap<>();
//        tempMap1.put("Bonded", 1);
//        tempMap1.put("Non-Bonded", 2);
//        tempMap1.put("non-Bonded", 2);
//        tempMap1.put("Non-bonded", 2);
//        tempMap1.put("non-bonded", 2);
//        tempMap1.put("Non-Bonded/Bonded", 3);
//        tempMap1.put("Non-bonded/Bonded", 3);
//        typeMap.put("是否保税", tempMap1);
//        HashMap<String, Integer> tempMap2 = new HashMap<>();
//        tempMap2.put("冷库", 1);
//        tempMap2.put("常温库", 2);
//        tempMap2.put("冷库/常温库", 3);
//        tempMap2.put("常温库/冷库", 3);
//        tempMap2.put("恒温库", 4);
//        tempMap2.put("暖库", 5);
//        typeMap.put("仓库类型", tempMap2);
//        HashMap<String, Integer> tempMap3 = new HashMap<>();
//        tempMap3.put("ColdStorage", 1);
//        tempMap3.put("ColdStorage&STDWarehouse", 2);
//        tempMap3.put("ExportSupervisoryWarehouse", 3);
//        tempMap3.put("Warehouse", 4);
//        tempMap3.put("Warehouse/ColdStorage", 5);
//        tempMap3.put("ColdStorage/Warehouse", 5);
//        tempMap3.put("Warehouse/Workshops", 6);
//        tempMap3.put("BTSWarehouse", 7);
//        tempMap3.put("Warehouse/port logistics", 8);
//        tempMap3.put("Export supervisory ColdStorage", 9);
//        typeMap.put("仓库分类", tempMap3);
//        HashMap<String, Integer> tempMap4 = new HashMap<>();
//        tempMap4.put("单面卸货", 1);
//        tempMap4.put("双面卸货", 2);
//        tempMap4.put("三面卸货", 3);
//        typeMap.put("卸货面", tempMap4);
//        HashMap<String, Integer> tempMap5 = new HashMap<>();
//        tempMap5.put("丙二类消防", 1);
//        tempMap5.put("丙一类消防", 2);
//        tempMap5.put("乙类消防", 3);
//        tempMap5.put("丙类消防", 4);
//        tempMap5.put("戊类消防", 5);
//        tempMap5.put("甲类消防", 6);
//        typeMap.put("消防等级", tempMap5);
//        HashMap<String, Integer> tempMap6 = new HashMap<>();
//        tempMap6.put("是", 1);
//        tempMap6.put("否", 2);
//        typeMap.put("是否是危险品库", tempMap6);
//        HashMap<String, Integer> tempMap7 = new HashMap<>();
//        tempMap7.put("是", 1);
//        tempMap7.put("否", 2);
//        typeMap.put("是否是厂房", tempMap7);
//        HashMap<String, Integer> tempMap8 = new HashMap<>();
//        tempMap8.put("是", 1);
//        tempMap8.put("否", 2);
//        typeMap.put("是否有仓配", tempMap8);
//        HashMap<String, Integer> tempMap9 = new HashMap<>();
//        tempMap9.put("是", 1);
//        tempMap9.put("否", 2);
//        typeMap.put("是否是高标仓", tempMap9);
//        HashMap<String, Integer> tempMap10 = new HashMap<>();
//        tempMap10.put("是", 1);
//        tempMap10.put("否", 2);
//        typeMap.put("是否原房东", tempMap10);
//        HashMap<String, Integer> tempMap11 = new HashMap<>();
//        tempMap11.put("是", 1);
//        tempMap11.put("否", 2);
//        typeMap.put("是否近机场", tempMap11);
//        HashMap<String, Integer> tempMap12 = new HashMap<>();
//        tempMap12.put("是", 1);
//        tempMap12.put("否", 2);
//        typeMap.put("是否交通便利", tempMap12);
//        HashMap<String, Integer> tempMap13 = new HashMap<>();
//        tempMap13.put("是", 1);
//        tempMap13.put("否", 2);
//        typeMap.put("可注册", tempMap13);
//        HashMap<String, Integer> tempMap14 = new HashMap<>();
//        tempMap14.put("是", 1);
//        tempMap14.put("否", 2);
//        typeMap.put("要求落税", tempMap14);
//        HashMap<String, Integer> tempMap15 = new HashMap<>();
//        tempMap15.put("是", 1);
//        tempMap15.put("否", 2);
//        typeMap.put("是否可分割", tempMap15);
//        HashMap<String, Integer> tempMap16 = new HashMap<>();
//        tempMap16.put("是", 1);
//        tempMap16.put("否", 2);
//        typeMap.put("是否有重型机械", tempMap16);
//        HashMap<String, Integer> tempMap17 = new HashMap<>();
//        tempMap17.put("是", 1);
//        tempMap17.put("否", 2);
//        typeMap.put("是否有坡道", tempMap17);
//        HashMap<String, Integer> tempMap18 = new HashMap<>();
//        tempMap18.put("是", 1);
//        tempMap18.put("否", 2);
//        typeMap.put("是否有斜坡", tempMap18);
//        HashMap<String, Integer> tempMap20 = new HashMap<>();
//        tempMap20.put("Business Park", 1);
//        tempMap20.put("RP", 2);
//        tempMap20.put("Logistics Park", 3);
//        typeMap.put("BP/RP/LP", tempMap20);
//        HashMap<String, Integer> tempMap21 = new HashMap<>();
//        tempMap21.put("地砖", 1);
//        tempMap21.put("水泥", 2);
//        tempMap21.put("环氧", 3);
//        tempMap21.put("耐磨金刚砂", 4);
//        typeMap.put("地坪材质", tempMap21);
//        HashMap<String, Integer> tempMap22 = new HashMap<>();
//        tempMap22.put("工业用地", 1);
//        tempMap22.put("物流用地", 2);
//        tempMap22.put("物流/工业用地", 3);
//        typeMap.put("土地信息", tempMap22);
//        HashMap<String, Integer> tempMap23 = new HashMap<>();
//        tempMap23.put("国有土地", 1);
//        tempMap23.put("集体土地", 2);
//        typeMap.put("土地性质", tempMap23);
//        HashMap<String, Integer> tempMap24 = new HashMap<>();
//        tempMap24.put("可协助", 1);
//        tempMap24.put("不可协助", 2);
//        typeMap.put("协助办环评", tempMap24);
//        HashMap<String, Integer> tempMap25 = new HashMap<>();
//        tempMap25.put("大开发商", 1);
//        tempMap25.put("本地开发商", 2);
//        typeMap.put("开发商属性", tempMap25);
//        HashMap<String, Integer> tempMap26 = new HashMap<>();
//        tempMap26.put("是", 1);
//        tempMap26.put("否", 2);
//        typeMap.put("是否是楼库", tempMap26);
//        HashMap<String, Integer> tempMap27 = new HashMap<>();
//        tempMap27.put("是", 1);
//        tempMap27.put("否", 2);
//        typeMap.put("是否有电梯", tempMap27);
//        HashMap<String, Integer> tempMap28 = new HashMap<>();
//        tempMap28.put("是", 1);
//        tempMap28.put("否", 2);
//        typeMap.put("是否真实日期", tempMap28);
//        HashMap<String, Integer> tempMap29 = new HashMap<>();
//        tempMap29.put("是", 1);
//        tempMap29.put("否", 2);
//        typeMap.put("是否真实时间", tempMap29);
//    }
//
//    //提取数字
//
//    public static String getStringNumber(String str){
//        // 控制正则表达式的匹配行为的参数(小数)
//        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
//        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
//        Matcher m = p.matcher(str);
//        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
//        if (m.find()) {
//            //如果有相匹配的,则判断是否为null操作
//            //group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
//            str = m.group(1) == null ? "" : m.group(1);
//        } else {
//            //如果匹配不到小数，就进行整数匹配
//            p = Pattern.compile("(\\d+)");
//            m = p.matcher(str);
//            if (m.find()) {
//                //如果有整数相匹配
//                str = m.group(1) == null ? "" : m.group(1);
//            } else {
//                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
//                str = "";
//            }
//        }
//        return str;
//    }
//
//    /**
//     * 数据库字段类型
//     * @param fileName
//     * @return
//     */
//    HashMap<String, Pair<String, Integer>> getSqlColumnType(String fileName) {
//        //创建工作簿对象
//        XSSFWorkbook xssfWorkbook = null;
//        try {
//            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        HashMap<String, Pair<String, Integer>> excelSqlMap = new HashMap<>();
//        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
//        int maxRow = sheet.getLastRowNum();
//        // 第一行是表头
//        for (int row = 1; row <= maxRow; row++) {
//            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
//            String nameString = sheet.getRow(row).getCell(0).getStringCellValue();
//            String typeString = sheet.getRow(row).getCell(1).getStringCellValue();
//            if(sheet.getRow(row).getCell(2) == null || sheet.getRow(row).getCell(2).equals("")) {
//                continue;
//            }
//            String excelNameString = sheet.getRow(row).getCell(2).getStringCellValue();
//            // String 0 int 1
//            int type = 1;
//            if(typeString.contains("varchar")) {
//                type = 0;
//            } else if(typeString.contains("date")) {
//                type = 2;
//            }
//            excelSqlMap.put(excelNameString, new Pair<>(nameString, type));
//        }
//        return excelSqlMap;
//    }
//
//    /**
//     * 生成sql语句
//     * @param fileName
//     * @param excelSqlMap
//     * @return
//     */
//    List<String> getSqlList(String fileName, HashMap<String, Pair<String, Integer>> excelSqlMap) {
//        FilePos filePos = new FilePos();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        //创建工作簿对象
//        XSSFWorkbook xssfWorkbook = null;
//        try {
//            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        XSSFSheet sheet = xssfWorkbook.getSheetAt(filePos.sheetNumInsert);
//        int maxRow = sheet.getLastRowNum();
////        int maxRow = 782;
//        int maxRol = sheet.getRow(0).getLastCellNum();
//        //获取表头
//        List<String> columnName = new ArrayList<>();
//        for (int rol = 0; rol < maxRol; rol++) {
//            columnName.add(sheet.getRow(0).getCell(rol).getStringCellValue());
//        }
//
//        List<String> sqlList = new ArrayList<>();
//        // 第一行是表头
//        for (int row = filePos.rowNumInsert; row <= maxRow; row++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("insert into `t_warehouse_static` set");
//            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
//            maxRol = sheet.getRow(row).getLastCellNum();
//            //经纬度
//            String geoTempL = null;
//            String geoTempR = null;
//            for (int rol = 0; rol < maxRol; rol++) {
//                String excelColumnName = columnName.get(rol);
//                String value;
//                String temp;
//                if(sheet.getRow(row).getCell(rol) != null) {
//                    if(geoPair.getKey().equals(excelColumnName)) {
//                        geoTempR = String.valueOf(sheet.getRow(row).getCell(rol).getNumericCellValue());
//                        continue;
//                    } else if(geoPair.getValue().equals(excelColumnName)) {
//                        geoTempL = String.valueOf(sheet.getRow(row).getCell(rol).getNumericCellValue());
//                        continue;
//                    } else {
//                        sheet.getRow(row).getCell(rol).setCellType(CellType.STRING);
//                        if(sheet.getRow(row).getCell(rol).getStringCellValue() == null || sheet.getRow(row).getCell(rol).getStringCellValue().equals("")) {
//                            temp = "null";
//                        } else {
//                            temp = sheet.getRow(row).getCell(rol).getStringCellValue();
//                        }
//                    }
//                } else {
//                    temp = "null";
//                }
//                if(typeMap.containsKey(excelColumnName) && !temp.equals("null")) {
////                    System.out.println(rol);
////                    System.out.println(row);
////                    System.out.println(excelColumnName);
////                    System.out.println(temp);
//                    System.out.println(excelColumnName);
//                    System.out.println(temp);
//                    System.out.println(typeMap.get(excelColumnName));
//                    System.out.println(typeMap.get(excelColumnName).get(temp));
//                    value = typeMap.get(excelColumnName).get(temp).toString();
//                } else if(toNumList.contains(excelColumnName)) {
//                    value = WarehouseStaticInsert.getStringNumber(temp);
//                    if(value.equals("")) {
//                        value = "null";
//                    }
//                } else {
//                    value = temp;
//                }
//
//                Pair<String, Integer> pair = excelSqlMap.get(excelColumnName);
//                if (pair != null && pair.getKey() != null) {
//                    String sqlColumnName = pair.getKey();
//                    if(value.equals("null")) {
//                        sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
//                    } else{
//                        if (pair.getValue() == 1) {
//                            sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
////                    sb.append(sqlColumnName + "=" + sheet.getRow(0).getCell(rol).getNumericCellValue() + ",");
//                        } else if (pair.getValue() == 0) {
////                        TODO
////                        if(value == "")
//                            value = value.replace("'","''");
//                            sb.append(" ").append(sqlColumnName).append(" = '").append(value).append("',");
//                        } else if(pair.getValue() == 2) {
//                            // date
//
//                            Date date = DateUtil.getJavaDate(Double.parseDouble(value));
//                            sb.append(" ").append(sqlColumnName).append(" = '").append(sdf.format(date)).append("',");
//                        }
//                    }
//                }
//            }
//            sb.append(" x_y = ST_GEOMFROMTEXT('Point(").append(geoTempL).append(" ").append(geoTempR).append(")', 4326)").append(";\n");
//            sb.append("update `t_warehouse_static` w " +
//                    "left join `b_location` b " +
//                    "on w.city_code = b.adcode " +
//                    "and b.location_type = 2 " +
//                    "set w.province_code = b.parent_adcode ");
//            sb.append("where w.vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'").append(";\n");
//            sb.append("update `t_warehouse_static` w " +
//                    "left join `b_location` b " +
//                    "on w.province_code = b.adcode " +
//                    "and b.location_type = 1 " +
//                    "set w.province_name = b.name ");
//            sb.append("where w.vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'").append(";\n");
//            sb.append("update `t_warehouse_static` w " +
//                    "left join `b_location_business` b " +
//                    "on b.adcode = w.city_code " +
//                    "set w.city_cluster_id = b.city_cluster_id ");
//            sb.append("where w.vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'").append(";\n");
//            sb.append("INSERT INTO harvest_logistics.t_warehouse_static_present(province_name, province_code, city_name, city_code, district_name, district_code, vas_id, bu_code, warehouse_branch, NAME, construct_status, construct_date, is_under_bond, depot_type, floor_area, build_area, lettable_build_area, land_due_date, x_y, unloading_front, fire_fighting_level, FLOOR, bearing, is_risk_depot, is_plant, is_accessories, platform_height, platform_width, is_ramp, is_slope, rain_slot_width, create_by, update_by, is_delete, create_time, update_time, city_cluster_id, built_build_area)" +
//                    " SELECT province_name, province_code, city_name, city_code, district_name, district_code, vas_id, bu_code, warehouse_branch, NAME, construct_status, construct_date, is_under_bond, depot_type, floor_area, build_area, lettable_build_area, land_due_date, x_y, unloading_front, fire_fighting_level, FLOOR, bearing, is_risk_depot, is_plant, is_accessories, platform_height, platform_width, is_ramp, is_slope, rain_slot_width, create_by, update_by, is_delete, create_time, update_time, city_cluster_id, built_build_area" +
//                    " FROM `t_warehouse_static`" +
//                    " where vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
//                    .append(";\n");
//            sb.append("UPDATE `t_warehouse_static_present` SET vid = UUID()" +
//                    " where vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
//                    .append(";\n");
//            sqlList.add(sb.toString());
//        }
//        return sqlList;
//    }
//
//    private static void saveAsFileWriter(String content, String filePath) {
//        FileWriter fwriter = null;
//        try {
//            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
//            fwriter = new FileWriter(filePath, true);
//            fwriter.write(content);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                fwriter.flush();
//                fwriter.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        FilePos filePos = new FilePos();
//        WarehouseStaticInsert warehouseStaticUpdate = new WarehouseStaticInsert();
//        warehouseStaticUpdate.init();
//        HashMap<String, Pair<String, Integer>> sqlColumnTypeMap = warehouseStaticUpdate.getSqlColumnType(filePos.sqlColumnName);
//        List<String> list = warehouseStaticUpdate.getSqlList(filePos.updatedFile,sqlColumnTypeMap);
//        System.out.println(list.size());
//        for (String s:
//             list) {
//            saveAsFileWriter(s, filePos.outputFileInsert);
//        }
//    }
//}
