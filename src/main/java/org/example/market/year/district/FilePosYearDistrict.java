package org.example.market.year.district;

import javafx.util.Pair;
import org.example.utils.FilePos;

public class FilePosYearDistrict extends FilePos {
    /*------------------------每次调整--------------------------------*/
    boolean newQuarter = false;
    String excelSourceFile = "数据更新_市场信息_1025.xlsx";
    String year = "2022";
    String quarter = "3";
    String yearBefore = "2022";
    String quarterBefore = "2";
    String exportFolder = "221103\\";
    int sheetNumUpdate = 3;
    int sheetNumDelete = 1;
    int sheetNumInsert = 3;
    int rowNumUpdate = 1;
    int rowNumDelete = 1;
    int rowNumInsert = 1;
    /*------------------------每次调整--------------------------------*/

    Pair<String, String> datePair = new Pair<>("年度", "季度");

    String tableName = "t_market_overview_year";
    String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
    String updatedFile = fileLocation + excelSourceFile;
    String sqlColumnName = fileLocation + "excelDatabaseMapping\\districtYear.xlsx";
    String outputFileDelete = fileLocation + exportFolder + "districtYearDelete.sql";
    String outputFileUpdate = fileLocation + exportFolder + "districtYearUpdate.sql";
    String outputFileInsert = fileLocation + exportFolder + "districtYearInsert.sql";

}
