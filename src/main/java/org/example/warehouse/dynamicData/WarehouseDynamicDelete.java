//package org.example.warehouse.dynamicData;
//
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class WarehouseDynamicDelete {
//
//    /**
//     * 生成sql语句
//     *
//     * @param fileName
//     * @return
//     */
//    List<String> getSqlList(String fileName) {
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
//
//        // TODO
//
//        XSSFSheet sheet = xssfWorkbook.getSheetAt(FilePos.sheetNumDelete);
//        int maxRow = sheet.getLastRowNum();
//        int maxRol = sheet.getRow(0).getLastCellNum();
//
//        List<String> sqlList = new ArrayList<>();
//        // 第一行是表头
//        for (int row = FilePos.rowNumDelete; row <= maxRow; row++) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("delete from `t_warehouse_dynamic`")
//                    .append(" where vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
//                    .append(" and date_year = '").append(numberFormat.format(sheet.getRow(row).getCell(3).getNumericCellValue())).append("'")
//                    .append(" and date_quarter = ").append(numberFormat.format(sheet.getRow(row).getCell(4).getNumericCellValue()))
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
//    public static void main(String[] args) {
//        WarehouseDynamicDelete warehouseStaticUpdate = new WarehouseDynamicDelete();
//        List<String> list = warehouseStaticUpdate.getSqlList(FilePos.updatedFile);
//        System.out.println(list.size());
//        for (String s :
//                list) {
//            saveAsFileWriter(s, FilePos.outputFileDelete);
//        }
//    }
//}
