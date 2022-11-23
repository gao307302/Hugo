package org.example.market.quarter.district;

import org.example.utils.FilePos;

public class FilePosQuarterDistrict extends FilePos {
    /*------------------------每次调整--------------------------------*/
    protected String excelSourceFile = "数据更新-仓库-1102-昆太常张行政区回滚.xlsx";
    protected String year = "2022";
    protected String quarter = "3";
    protected boolean newQuarter = false;
    protected String yearBefore = "2022";
    protected String quarterBefore = "2";
    protected String exportFolder = "221102\\";

    protected int sheetNumUpdate = 1;
    protected int sheetNumDelete = 1;
    protected int sheetNumInsert = 1;

    protected int rowNumUpdate = 1;
    protected int rowNumDelete = 1;
    protected int rowNumInsert = 1;
    /*-----------------每次调整--------------------------------*/


    protected String fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

    protected String updatedFile = fileLocation + excelSourceFile;
    protected String sqlColumnName = fileLocation + "excelDatabaseMapping\\districtQuarter.xlsx";

    protected String outputFileDelete = fileLocation + exportFolder + "districtQuarterDelete.sql";
    protected String outputFileUpdate = fileLocation + exportFolder + "districtQuarterUpdate.sql";
    protected String outputFileInsert = fileLocation + exportFolder + "districtQuarterInsert.sql";
}
