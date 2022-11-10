package org.example.market.year.city;

public class FilePosYearCity {
    /*------------------------每次调整--------------------------------*/
    protected static boolean newQuarter = false;
    protected static String excelSourceFile = "数据更新-市场信息-1028.xlsx";
    protected static String year = "2022";
    protected static String quarter = "3";
    protected static String yearBefore = "2022";
    protected static String quarterBefore = "2";
    protected static String exportFolder = "221103\\";

    protected static int sheetNumUpdate = 3;
    protected static int sheetNumDelete = 1;
    protected static int sheetNumInsert = 2;

    protected static int rowNumUpdate = 1;
    protected static int rowNumDelete = 1;
    protected static int rowNumInsert = 1;
/*------------------------每次调整--------------------------------*/


    protected static String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

    protected static String updatedFile = fileLocation + excelSourceFile;
    protected static String sqlColumnName = fileLocation + "excelDatabaseMapping\\cityYear.xlsx";

    protected static String outputFileDelete = fileLocation + exportFolder + "cityYearDelete.sql";
    protected static String outputFileUpdate = fileLocation + exportFolder + "cityYearUpdate.sql";
    protected static String outputFileInsert = fileLocation + exportFolder + "cityYearInsert.sql";
}
