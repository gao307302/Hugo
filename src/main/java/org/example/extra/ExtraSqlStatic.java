package org.example.extra;

import javafx.util.Pair;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExtraSqlStatic implements ExtraSql {


    private List<String> getPresentColumn (String fileLocation){
        String fileName = fileLocation + "excelDatabaseMapping\\warehouseStaticPresent.xlsx";
        //创建工作簿对象
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> excelSqlMap = new ArrayList<>();
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int maxRow = sheet.getLastRowNum();
        // 第一行是表头
        for (int row = 0; row <= maxRow; row++) {
            //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
            excelSqlMap.add(sheet.getRow(row).getCell(0).getStringCellValue());
        }
        return excelSqlMap;
    }

    @Override
    public void insert(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String developer, String fileLocation) {
        List<String> presentColumn = getPresentColumn(fileLocation);

        sb.append(" x_y = ST_GEOMFROMTEXT('Point(").append(geoTempL).append(" ").append(geoTempR).append(")', 4326)").append(";\n");
//        if(developer != null || developer != "") {
//            sb.append("UPDATE `t_warehouse_static` SET developer_id = (select id from harvest_logistics.t_logistics_developer where developer_name = " + developer +
//                            ") where vas_id = '").append(vasId).append("'")
//                    .append(";\n");
//        }
        sb.append("INSERT INTO harvest_logistics.t_warehouse_static_present (");
        for (String s:
             presentColumn) {
            sb.append(s).append(",");
        }
        sb.append("vas_id");
        sb.append(") SELECT ");
        for (String s:
                presentColumn) {
            sb.append(s).append(",");
        }
        sb.append("vas_id");
        sb.append(" FROM `t_warehouse_static`" +
                        " where vas_id = '").append(vasId).append("'")
                .append(";\n");
        sb.append("UPDATE `t_warehouse_static_present` SET vid = UUID()" +
                        " where vas_id = '").append(vasId).append("'")
                .append(";\n");
    }

    @Override
    public void update(StringBuilder sb, String quarter, String year, String geoTempL, String geoTempR, String vasId, int districtType, String cityName, String districtName, String fileLocation) {
        List<String> presentColumn = getPresentColumn(fileLocation);

        sb.append(", x_y = ST_GEOMFROMTEXT('Point(").append(geoTempL).append(" ").append(geoTempR).append(")', 4326)");
        sb.append(" where vas_id = '").append(vasId).append("'").append(";\n");
        sb.append("update `t_warehouse_static_present` o, `t_warehouse_static` n " +
                "set ");

        for (String s:
                presentColumn) {
            sb.append("o.").append(s).append(" = n.").append(s).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" where o.vas_id = '").append(vasId).append("'")
                .append(" and n.vas_id = '").append(vasId).append("'")
                .append(";\n");
    }

    @Override
    public String insertEnding(StringBuilder sb) {

        return "";
    }

    @Override
    public String updateEnding(StringBuilder sb) {
        return "";
    }

    @Override
    public String ending(StringBuilder sb) {
        return "";
    }

}
