package org.example.warehouse.dynamicData;

import javafx.util.Pair;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WarehouseDynamicUpdate {

    private static final HashMap<String, HashMap<String, Integer>> typeMap = new HashMap<>();
    private static final List<String> toNumList = new ArrayList<>();
    private static Pair<String, String> valQuarter;
    //字段整理
    private void init() {
        valQuarter = new Pair<>("Valuation_Year", "Valuation_Quarter");
    }

    //提取数字
    public static String getStringNumber(String str){
        // 控制正则表达式的匹配行为的参数(小数)
        Pattern p = Pattern.compile("(\\d+\\.\\d+)");
        //Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
        Matcher m = p.matcher(str);
        //m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
        if (m.find()) {
            //如果有相匹配的,则判断是否为null操作
            //group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
            str = m.group(1) == null ? "" : m.group(1);
        } else {
            //如果匹配不到小数，就进行整数匹配
            p = Pattern.compile("(\\d+)");
            m = p.matcher(str);
            if (m.find()) {
                //如果有整数相匹配
                str = m.group(1) == null ? "" : m.group(1);
            } else {
                //如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
                str = "";
            }
        }
        return str;
    }

    /**
     * 数据库字段类型
     *
     * @param fileName
     * @return
     */
    HashMap<String, Pair<String, Integer>> getSqlColumnType(String fileName) {
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Pair<String, Integer>> excelSqlMap = new HashMap<>();
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int maxRow = sheet.getLastRowNum();
        // 第一行是表头
        for (int row = 1; row <= maxRow; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            String nameString = sheet.getRow(row).getCell(0).getStringCellValue();
            String typeString = sheet.getRow(row).getCell(1).getStringCellValue();
            if (sheet.getRow(row).getCell(2) == null || sheet.getRow(row).getCell(2).equals("")) {
                continue;
            }
            String excelNameString = sheet.getRow(row).getCell(2).getStringCellValue();
            // String 0 int 1
            int type = 1;
            if (nameString.contains("valuation_date")) {
                type = 2;
            } else if (typeString.contains("varchar")) {
                type = 0;
            }
            excelSqlMap.put(excelNameString, new Pair<>(nameString, type));
        }
        return excelSqlMap;
    }

    /**
     * 生成sql语句
     *
     * @param fileName
     * @param excelSqlMap
     * @return
     */
    List<String> getSqlList(String fileName, HashMap<String, Pair<String, Integer>> excelSqlMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO

        XSSFSheet sheet = xssfWorkbook.getSheetAt(FilePos.sheetNumUpdate);
        int maxRow = sheet.getLastRowNum();
        int maxRol = sheet.getRow(0).getLastCellNum();
        //获取表头
        List<String> columnName = new ArrayList<>();
        for (int rol = 0; rol < maxRol; rol++) {
            columnName.add(sheet.getRow(0).getCell(rol).getStringCellValue());
        }

        List<String> sqlList = new ArrayList<>();
        // 第一行是表头
        for (int row = FilePos.rowNumUpdate; row <= maxRow; row++) {
            StringBuilder sb = new StringBuilder();
            sb.append("update `t_warehouse_dynamic` set");
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            maxRol = sheet.getRow(row).getLastCellNum();
            //经纬度
            String geoTempL = null;
            String geoTempR = null;
            // vas_id 不用
            for (int rol = 1; rol < maxRol; rol++) {
                String excelColumnName = columnName.get(rol);
                String value;
                String temp;
                if (sheet.getRow(row).getCell(rol) != null) {
                    sheet.getRow(row).getCell(rol).setCellType(CellType.STRING);
                    if (sheet.getRow(row).getCell(rol).getStringCellValue() == null
                            || sheet.getRow(row).getCell(rol).getStringCellValue().equals("")
                            || sheet.getRow(row).getCell(rol).getStringCellValue().equals("-")) {
                        temp = "null";
                    } else {
                        temp = sheet.getRow(row).getCell(rol).getStringCellValue();
                    }
                } else {
                    temp = "null";
                }
                if (typeMap.containsKey(excelColumnName) && !temp.equals("null")) {
//                    System.out.println(rol);
//                    System.out.println(row);
//                    System.out.println(excelColumnName);
//                    System.out.println(temp);
                    value = typeMap.get(excelColumnName).get(temp).toString();
                } else if (toNumList.contains(excelColumnName)) {
                    value = WarehouseDynamicUpdate.getStringNumber(temp);
                } else {
                    value = temp;
                }
                if (valQuarter.getKey().equals(excelColumnName)) {
                    geoTempL = temp;
                } else if (valQuarter.getValue().equals(excelColumnName)) {
                    geoTempR = temp;
                }


                Pair<String, Integer> pair = excelSqlMap.get(excelColumnName);
                if (pair != null && pair.getKey() != null) {
                    String sqlColumnName = pair.getKey();
                    if (value.equals("null")) {
                        sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
                    } else {
                        if (pair.getValue() == 1) {
                            sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
//                    sb.append(sqlColumnName + "=" + sheet.getRow(0).getCell(rol).getNumericCellValue() + ",");
                        } else if (pair.getValue() == 0) {
//                        TODO
//                        if(value == "")
                            sb.append(" ").append(sqlColumnName).append(" = '").append(value).append("',");
                        } else if (pair.getValue() == 2) {
                            // date

                            Date date = DateUtil.getJavaDate(Double.parseDouble(value));
                            sb.append(" ").append(sqlColumnName).append(" = '").append(sdf.format(date)).append("',");
                        }
                    }
                }
            }

//                    .append(" valuation_quarter = '").append(geoTempL).append("Q").append(geoTempR).append("'")
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" where vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
                    .append(" and date_year = '").append(sheet.getRow(row).getCell(3).getStringCellValue()).append("'")
                    .append(" and date_quarter = ").append(sheet.getRow(row).getCell(4).getStringCellValue())
                    .append(";\n");
            sb.append("update `t_warehouse_dynamic` w " +
                    "left join `t_warehouse_static_present` wsp " +
                    "on wsp.vas_id = w.vas_id " +
                    "set w.warehouse_id = wsp.id ");
            
            sb.append("where w.vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
                    .append(" and w.date_year = '").append(sheet.getRow(row).getCell(3).getStringCellValue()).append("'")
                    .append(" and w.date_quarter = ").append(sheet.getRow(row).getCell(4).getStringCellValue())
                    .append(";\n");
            sqlList.add(sb.toString());
        }
        return sqlList;
    }

    private static void saveAsFileWriter(String content, String filePath) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath, true);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WarehouseDynamicUpdate warehouseStaticUpdate = new WarehouseDynamicUpdate();
        warehouseStaticUpdate.init();
        HashMap<String, Pair<String, Integer>> sqlColumnTypeMap = warehouseStaticUpdate.getSqlColumnType(FilePos.sqlColumnName);
        List<String> list = warehouseStaticUpdate.getSqlList(FilePos.updatedFile, sqlColumnTypeMap);
        System.out.println(list.size());
        for (String s :
                list) {
            saveAsFileWriter(s, FilePos.outputFileUpdate);
        }
    }
}
