package org.example.utils;

import javafx.util.Pair;

public class FilePos {
    /*------------------------每次调整--------------------------------*/
    static public String tableName;
    static public Pair<String, String> datePair;
    static public boolean newQuarter = false;
    static public String excelSourceFile = "数据更新-市场信息-1028.xlsx";
//    String excelSourceFile = "数据更新_市场信息_1025.xlsx";
    static public String year = "2022";
    static public String quarter = "3";
    static public String yearBefore = "2022";
    static public String quarterBefore = "2";
    static public String exportFolder = "221028\\";
    static public int sheetNumUpdate = 1;
    static public int sheetNumDelete = 1;
    static public int sheetNumInsert = 3;
    static public int rowNumUpdate = 1;
    static public int rowNumDelete = 1;
    static public int rowNumInsert = 1;
     /*----public -----每次调整--------------------------------*/

    static public String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
    static public String updatedFile = fileLocation + excelSourceFile;
    static public String sqlColumnName = fileLocation + "excelDatabaseMapping\\districtYear.xlsx";
    static public String outputFileDelete = fileLocation + exportFolder + "districtYearDelete.sql";
    static public String outputFileUpdate = fileLocation + exportFolder + "districtYearUpdate.sql";
    static public String outputFileInsert = fileLocation + exportFolder + "districtYearInsert.sql";
    static public String dataType = "2";

}
