package org.example.utils;

import javafx.util.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.extra.ExtraSql;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitUtils {

    /**
     * 数据库字段类型
     *
     * @param fileName
     * @return
     */
    public static HashMap<String, Pair<String, Integer>> getSqlColumnType(String fileName) {
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
    public static void saveAsFileWriter(String content, String filePath) {
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


    /**
     * 生成sql语句
     *
     */
    public List<String> getInitSqlList(FilePos filePos, ExtraSql extraSql, int type) {
        String fileName = filePos.updatedFile;
        String tableName = filePos.tableName;
        Pair<String, String> datePair = filePos.datePair;
        Integer sheetNum = null;
        Integer rowNum = null;
        if(type == 0) {
            rowNum = filePos.rowNumInsert;
            sheetNum = filePos.sheetNumInsert;
        } else if(type == 1) {
            rowNum = filePos.rowNumUpdate;
            sheetNum = filePos.sheetNumUpdate;
        }
        HashMap<String, Pair<String, Integer>> excelSqlMap = InitUtils.getSqlColumnType(filePos.sqlColumnName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 不显示千位分割符，否则显示结果会变成类似1,234,567,890
        numberFormat.setGroupingUsed(false);
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO

        XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNum);
        int maxRow = sheet.getLastRowNum();
        int maxRol = sheet.getRow(0).getLastCellNum();
        //获取表头
        List<String> columnName = new ArrayList<>();
        for (int rol = 0; rol < maxRol; rol++) {
            columnName.add(sheet.getRow(0).getCell(rol).getStringCellValue());
        }

        List<String> sqlList = new ArrayList<>();
        // 第一行是表头
        for (int row = rowNum; row <= maxRow; row++) {
            StringBuilder sb = new StringBuilder();
            if(type == 0) {
                sb.append("insert into `").append(tableName).append("` set");
            } else if(type == 1) {
                sb.append("update `").append(tableName).append("` set");
            }
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            maxRol = sheet.getRow(row).getLastCellNum();
            //年 季度
            String quarter = null;
            String year = null;
            for (int rol = 0; rol < maxRol; rol++) {
                String excelColumnName = columnName.get(rol);
                String value;
                String temp;
                if (sheet.getRow(row).getCell(rol) != null) {
                    if(datePair.getKey().equals(excelColumnName)) {
                        year = numberFormat.format(sheet.getRow(row).getCell(rol).getNumericCellValue());
                        continue;
                    } else if(datePair.getValue().equals(excelColumnName)) {
                        quarter = numberFormat.format(sheet.getRow(row).getCell(rol).getNumericCellValue());
                        continue;
                    }
                    sheet.getRow(row).getCell(rol).setCellType(CellType.STRING);
                    Cell cell =sheet.getRow(row).getCell(rol);
                    if (CommonUtils.nullCellCheck(cell)) {
                        temp = "null";
                    } else {
                        temp = sheet.getRow(row).getCell(rol).getStringCellValue();
                    }
                } else {
                    temp = "null";
                }
                value = temp;

                Pair<String, Integer> pair = excelSqlMap.get(excelColumnName);
                if (pair != null && pair.getKey() != null) {
                    String sqlColumnName = pair.getKey();
                    if (value.equals("null")) {
                        sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
                    } else {
                        if (pair.getValue() == 1) {
                            sb.append(" ").append(sqlColumnName).append(" = ").append(value).append(",");
                        } else if (pair.getValue() == 0) {
                            sb.append(" ").append(sqlColumnName).append(" = '").append(value).append("',");
                        } else if (pair.getValue() == 2) {
                            Date date = DateUtil.getJavaDate(Double.parseDouble(value));
                            sb.append(" ").append(sqlColumnName).append(" = '").append(sdf.format(date)).append("',");
                        }
                    }
                }
            }
            String cityName = sheet.getRow(row).getCell(2).getStringCellValue();
            String districtName = sheet.getRow(row).getCell(4).getStringCellValue();
            if(type == 0) {
                extraSql.extrasolar(sb, quarter, year);
            } else if (type == 1) {
                sb.deleteCharAt(sb.length() - 1);
                extraSql.updateMarket(sb, quarter, year, cityName, districtName);
            }
            sqlList.add(sb.toString());
        }
        return sqlList;
    }


    /**
     * 生成删除sql语句
     *
     * @return
     */
    public List<String> getCityQuarterDeleteSqlList(FilePos filePos, ExtraSql extraSql) {
        String fileName = filePos.updatedFile;
        String tableName = filePos.tableName;
        Pair<String, String> datePair = filePos.datePair;
        HashMap<String, Pair<String, Integer>> excelSqlMap = InitUtils.getSqlColumnType(filePos.sqlColumnName);
        Integer sheetNum = filePos.sheetNumInsert;
        Integer rowNum = filePos.rowNumDelete;
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 不显示千位分割符，否则显示结果会变成类似1,234,567,890
        numberFormat.setGroupingUsed(false);
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // TODO

        XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNum);
        int maxRow = sheet.getLastRowNum();
        int maxRol = sheet.getRow(0).getLastCellNum();

        List<String> sqlList = new ArrayList<>();
        // 第一行是表头
        for (int row = rowNum; row <= maxRow; row++) {
            StringBuilder sb = new StringBuilder();
            sb.append("delete from `").append(tableName).append("`")
                    .append(" where city_code = ").append(sheet.getRow(row).getCell(3).getStringCellValue())
                    .append(" where data_type =").append(filePos.dataType)
                    .append(" and date_year = '").append(numberFormat.format(sheet.getRow(row).getCell(5).getNumericCellValue())).append("'")
                    .append(" and date_quarter = ").append(numberFormat.format(sheet.getRow(row).getCell(6).getNumericCellValue()))
                    .append(";\n");
            sqlList.add(sb.toString());
        }
        return sqlList;
    }

    /**
     * 生成删除sql语句
     *
     * @return
     */
    public List<String> getDistrictQuarterDeleteSqlList(FilePos filePos, ExtraSql extraSql) {
        String fileName = filePos.updatedFile;
        String tableName = filePos.tableName;
        Pair<String, String> datePair = filePos.datePair;
        HashMap<String, Pair<String, Integer>> excelSqlMap = InitUtils.getSqlColumnType(filePos.sqlColumnName);
        Integer sheetNum = filePos.sheetNumInsert;
        Integer rowNum = filePos.rowNumDelete;
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 不显示千位分割符，否则显示结果会变成类似1,234,567,890
        numberFormat.setGroupingUsed(false);
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // TODO

        XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNum);
        int maxRow = sheet.getLastRowNum();
        int maxRol = sheet.getRow(0).getLastCellNum();

        List<String> sqlList = new ArrayList<>();
        // 第一行是表头
        for (int row = rowNum; row <= maxRow; row++) {
            StringBuilder sb = new StringBuilder();
            sb.append("delete from `").append(tableName).append("`")
                    .append(" where district_code = ").append(sheet.getRow(row).getCell(5).getStringCellValue())
                    .append(" where data_type =").append(filePos.dataType)
                    .append(" and date_year = '").append(numberFormat.format(sheet.getRow(row).getCell(5).getNumericCellValue())).append("'")
                    .append(" and date_quarter = ").append(numberFormat.format(sheet.getRow(row).getCell(6).getNumericCellValue()))
                    .append(";\n");
            sqlList.add(sb.toString());
        }
        return sqlList;
    }
}
