package org.example.utils;

import javafx.util.Pair;

public class InputField {
    /*------------------------每次调整--------------------------------*/
    public String tableName;
    public Pair<String, String> datePair;
    public boolean newQuarter = false;
    public String excelSourceFile = "数据更新-市场信息-1028.xlsx";
//    String excelSourceFile = "数据更新_市场信息_1025.xlsx";
    public String year = "2022";
    public String quarter = "3";
    public String yearBefore = "2022";
    public String quarterBefore = "2";
    public String exportFolder = "221028\\";
    public int sheetNumUpdate = 1;
    public int sheetNumDelete = 1;
    public int sheetNumInsert = 3;
    public int rowNumUpdate = 1;
    public int rowNumDelete = 1;
    public int rowNumInsert = 1;
     /*----public -----每次调整--------------------------------*/

    public String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
    public String updatedFile = fileLocation + excelSourceFile;
    public String sqlColumnName = fileLocation + "excelDatabaseMapping\\districtYear.xlsx";
    public String outputFileDelete = fileLocation + exportFolder + "districtYearDelete.sql";
    public String outputFileUpdate = fileLocation + exportFolder + "districtYearUpdate.sql";
    public String outputFileInsert = fileLocation + exportFolder + "districtYearInsert.sql";
    public String dataType = "2";

}
