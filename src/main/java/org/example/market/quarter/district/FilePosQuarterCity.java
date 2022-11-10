package org.example.market.quarter.district;

public class FilePosQuarterCity {
    /*------------------------每次调整--------------------------------*/
    protected static String excelSourceFile = "数据更新-仓库-1102-昆太常张行政区回滚.xlsx";
    protected static String year = "2022";
    protected static String quarter = "3";
    protected static boolean newQuarter = false;
    protected static String yearBefore = "2022";
    protected static String quarterBefore = "2";
    protected static String exportFolder = "221102\\";

    protected static int sheetNumUpdate = 1;
    protected static int sheetNumDelete = 1;
    protected static int sheetNumInsert = 1;

    protected static int rowNumUpdate = 1;
    protected static int rowNumDelete = 1;
    protected static int rowNumInsert = 1;
    /*------------------------每次调整--------------------------------*/


    protected static String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

    protected static String updatedFile = fileLocation + excelSourceFile;
    protected static String sqlColumnName = fileLocation + "excelDatabaseMapping\\districtQuarter.xlsx";

    protected static String outputFileDelete = fileLocation + exportFolder + "districtQuarterDelete.sql";
    protected static String outputFileUpdate = fileLocation + exportFolder + "districtQuarterUpdate.sql";
    protected static String outputFileInsert = fileLocation + exportFolder + "districtQuarterInsert.sql";
}
