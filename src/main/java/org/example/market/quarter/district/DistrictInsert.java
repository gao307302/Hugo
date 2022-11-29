//package org.example.market.quarter.district;
//
//import javafx.util.Pair;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.example.utils.CommonUtils;
//import org.example.utils.InitUtils;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.sql.Timestamp;
//import java.text.NumberFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//public class DistrictInsert {
//
//    private static final Pair<String, String> datePair = new Pair<>("年份", "季度");
//
//    private static final String tableName = "t_market_overview_quarter";
//    /**
//     * 生成sql语句
//     *
//     * @param fileName
//     * @param excelSqlMap
//     * @return
//     */
//    static List<String> getSqlList(String fileName, HashMap<String, Pair<String, Integer>> excelSqlMap) {
//        FilePosQuarterDistrict filePosQuarterDistrict = new FilePosQuarterDistrict();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        NumberFormat numberFormat = NumberFormat.getInstance();
//        // 不显示千位分割符，否则显示结果会变成类似1,234,567,890
//        numberFormat.setGroupingUsed(false);
//        //创建工作簿对象
//        XSSFWorkbook xssfWorkbook = null;
//        try {
//            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // TODO
//
//        XSSFSheet sheet = xssfWorkbook.getSheetAt(filePosQuarterDistrict.sheetNumInsert);
//        int maxRow = sheet.getLastRowNum();
//        int maxRol = sheet.getRow(0).getLastCellNum();
//        //获取表头
//        List<String> columnName = new ArrayList<>();
//        for (int rol = 0; rol < maxRol; rol++) {
//            columnName.add(sheet.getRow(0).getCell(rol).getStringCellValue());
//        }
//
//        List<String> sqlList = new ArrayList<>();
//        // 第一行是表头
//        for (int row = filePosQuarterDistrict.rowNumInsert; row <= maxRow; row++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("insert into `").append(tableName).append("` set");
//            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
//            maxRol = sheet.getRow(row).getLastCellNum();
//            //年 季度
//            String quarter = null;
//            String year = null;
//            for (int rol = 0; rol < maxRol; rol++) {
//                String excelColumnName = columnName.get(rol);
//                String value;
//                String temp;
//                if (sheet.getRow(row).getCell(rol) != null) {
//                    if(datePair.getKey().equals(excelColumnName)) {
//                        year = numberFormat.format(sheet.getRow(row).getCell(rol).getNumericCellValue());
//                        continue;
//                    } else if(datePair.getValue().equals(excelColumnName)) {
//                        quarter = numberFormat.format(sheet.getRow(row).getCell(rol).getNumericCellValue());
//                        continue;
//                    }
//                    sheet.getRow(row).getCell(rol).setCellType(CellType.STRING);
//                    Cell cell =sheet.getRow(row).getCell(rol);
//                    if (CommonUtils.nullCellCheck(cell)) {
//                        temp = "null";
//                    } else {
//                        temp = sheet.getRow(row).getCell(rol).getStringCellValue();
//                    }
//                } else {
//                    temp = "null";
//                }
//                value = temp;
//
//                Pair<String, Integer> pair = excelSqlMap.get(excelColumnName);
//                if (pair != null && pair.getKey() != null) {
//                    String sqlColumnName = pair.getKey();
//                    if (value.equals("null")) {
//                        sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
//                    } else {
//                        if (pair.getValue() == 1) {
//                            sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
//                        } else if (pair.getValue() == 0) {
//                            sb.append(" ").append(sqlColumnName).append(" = '").append(value).append("',");
//                        } else if (pair.getValue() == 2) {
//                            Date date = DateUtil.getJavaDate(Double.parseDouble(value));
//                            sb.append(" ").append(sqlColumnName).append(" = '").append(sdf.format(date)).append("',");
//                        }
//                    }
//                }
//            }
//
//
//            sb.append(" date_year = '").append(year).append("',")
//                    .append(" date_quarter = ").append(quarter).append(",")
//                    .append(" data_type = ").append(2).append(",")
//                    .append(" create_time = '").append(new Timestamp(System.currentTimeMillis())).append("',")
//                    .append(" data_date = '").append(year).append("Q").append(quarter).append("';\n");
//            sqlList.add(sb.toString());
//        }
//        return sqlList;
//    }
//    // 额外sql
//    private static String getString(StringBuilder sb) {
//        FilePosQuarterDistrict filePosQuarterDistrict = new FilePosQuarterDistrict();
//        String extra;
//        sb.append("UPDATE ").append(tableName)
//                .append(" SET date_order = 0 WHERE date_order = 2;\n");
//        sb.append("UPDATE ").append(tableName)
//                .append(" SET date_order = 2 WHERE date_order = 1;\n");
//        sb.append("UPDATE ").append(tableName)
//                .append(" w SET w.date_order = 1 WHERE w.date_year = '").append(filePosQuarterDistrict.year).append("'")
//                .append(" and w.date_quarter = ").append(filePosQuarterDistrict.quarter)
//                .append(";\n");
//        sb.append("UPDATE `").append(tableName).append("` w " +
//                "left join `b_location_business` b " +
//                "on b.adcode = w.city_code " +
//                "set w.city_cluster_id = b.city_cluster_id ");
//        sb.append("where isnull(w.city_cluster_id);\n");
//        extra = sb.toString();
//        return extra;
//    }
//
//
//    public static void main(String[] args) {
//        FilePosQuarterDistrict filePosQuarterDistrict = new FilePosQuarterDistrict();
//        HashMap<String, Pair<String, Integer>> sqlColumnTypeMap = InitUtils.getSqlColumnType(filePosQuarterDistrict.sqlColumnName);
//        List<String> list = getSqlList(filePosQuarterDistrict.updatedFile, sqlColumnTypeMap);
//        System.out.println(list.size());
//        for (String s :
//                list) {
//            InitUtils.saveAsFileWriter(s, filePosQuarterDistrict.outputFileInsert);
//        }
//        if(filePosQuarterDistrict.newQuarter) {
//            String extra;
//            StringBuilder sb = new StringBuilder();
//            extra = getString(sb);
//            InitUtils.saveAsFileWriter(extra, filePosQuarterDistrict.outputFileInsert);
//        }
//    }
//
//
//}
