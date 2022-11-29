//package org.example.warehouse.staticData;
//
//public class FilePos {
///*------------------------每次调整--------------------------------*/
//    protected String excelSourceFile = "数据更新-仓库-1103-坐标补充.xlsx";
//    protected String exportFolder;
//
//    protected int sheetNumUpdate = 0;
//    protected int sheetNumDelete = 1;
//    protected int sheetNumInsert = 1;
//
//    protected int rowNumUpdate = 1;
//    protected int rowNumDelete = 1;
//    protected int rowNumInsert = 1;
///*------------------------每次调整--------------------------------*/
//
//    protected String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
//
//    protected String updatedFile = fileLocation + excelSourceFile;
//    protected String sqlColumnName = fileLocation + "excelDatabaseMapping\\staticColumn.xlsx";
//
//    protected String outputFileDelete = fileLocation + exportFolder + "staticDelete.sql";
//    protected String outputFileUpdate = fileLocation + exportFolder + "staticUpdate.sql";
//    protected String outputFileInsert = fileLocation + exportFolder + "staticInsert.sql";
//
//    public FilePos() {
//    }
//
//    public FilePos(String excelSourceFile, String exportFolder) {
//        this.excelSourceFile = excelSourceFile;
//        this.exportFolder = exportFolder;
//        fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
//        updatedFile = fileLocation + excelSourceFile;
//        sqlColumnName = fileLocation + "excelDatabaseMapping\\staticColumn.xlsx";
//        outputFileDelete = fileLocation + exportFolder + "staticDelete.sql";
//        outputFileUpdate = fileLocation + exportFolder + "staticUpdate.sql";
//        outputFileInsert = fileLocation + exportFolder + "staticInsert.sql";
//    }
//
//    public FilePos(String exportFolder) {
//        this.exportFolder = exportFolder;
//        fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";
//        updatedFile = fileLocation + excelSourceFile;
//        sqlColumnName = fileLocation + "excelDatabaseMapping\\staticColumn.xlsx";
//        outputFileDelete = fileLocation + exportFolder + "staticDelete.sql";
//        outputFileUpdate = fileLocation + exportFolder + "staticUpdate.sql";
//        outputFileInsert = fileLocation + exportFolder + "staticInsert.sql";
//    }
//
//    public FilePos(String excelSourceFile, String exportFolder, int sheetNumUpdate, int sheetNumDelete, int sheetNumInsert) {
//        this.excelSourceFile = excelSourceFile;
//        this.exportFolder = exportFolder;
//        this.sheetNumUpdate = sheetNumUpdate;
//        this.sheetNumDelete = sheetNumDelete;
//        this.sheetNumInsert = sheetNumInsert;
//    }
//}
