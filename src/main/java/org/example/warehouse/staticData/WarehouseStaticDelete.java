package org.example.warehouse.staticData;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WarehouseStaticDelete {

    /**
     * 生成sql语句
     *
     * @param fileName
     * @return
     */
    List<String> getSqlList(String fileName) {
        FilePos filePos = new FilePos();
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // TODO
        // 需修改
        XSSFSheet sheet = xssfWorkbook.getSheetAt(filePos.sheetNumDelete);
        int maxRow = sheet.getLastRowNum();
        int maxRol = sheet.getRow(0).getLastCellNum();

        List<String> sqlList = new ArrayList<>();
        // 第一行是表头
        // 需修改
        for (int row = filePos.rowNumDelete; row <= maxRow; row++) {
            StringBuilder sb = new StringBuilder();
            sb.append("delete from `t_warehouse_static`")
                    .append(" where vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
                    .append(" and name = '").append(sheet.getRow(row).getCell(3).getStringCellValue()).append("'")
                    .append(";\n");
            sb.append("delete from `t_warehouse_static_present`")
                    .append(" where vas_id = '").append(sheet.getRow(row).getCell(0).getStringCellValue()).append("'")
                    .append(" and name = '").append(sheet.getRow(row).getCell(3).getStringCellValue()).append("'")
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
        FilePos filePos = new FilePos();
        WarehouseStaticDelete warehouseStaticUpdate = new WarehouseStaticDelete();
        List<String> list = warehouseStaticUpdate.getSqlList(filePos.updatedFile);
        System.out.println(list.size());
        for (String s :
                list) {
            saveAsFileWriter(s, filePos.outputFileDelete);
        }
    }
}
