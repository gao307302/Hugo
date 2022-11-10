package org.example.warehouse.dynamicData;

public class FilePos {
/*------------------------每次调整--------------------------------*/

    protected static String year = "2022";
    protected static String quarter = "3";
    protected static boolean newQuarter = true;
    protected static String yearBefore = "2022";
    protected static String quarterBefore = "2";

    protected static String excelSourceFile = "数据更新_仓库信息_1025.xlsx";
    protected static String exportFolder = "221025\\";

    protected static int sheetNumUpdate = 1;
    protected static int sheetNumDelete = 1;
    protected static int sheetNumInsert = 3;

    protected static int rowNumUpdate = 1;
    protected static int rowNumDelete = 1;
    protected static int rowNumInsert = 1;
/*------------------------每次调整--------------------------------*/


    protected static String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

    protected static String updatedFile = fileLocation + excelSourceFile;
    protected static String sqlColumnName = fileLocation + "excelDatabaseMapping\\dynamicColumn.xlsx";

    protected static String outputFileDelete = fileLocation + exportFolder + "dynamicDelete.sql";
    protected static String outputFileUpdate = fileLocation + exportFolder + "dynamicUpdate.sql";
    protected static String outputFileInsert = fileLocation + exportFolder + "dynamicInsert.sql";
}
