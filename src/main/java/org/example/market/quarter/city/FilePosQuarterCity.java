package org.example.market.quarter.city;

import org.example.utils.FilePos;

public class FilePosQuarterCity extends FilePos {
    static {
        /*------------------------每次调整--------------------------------*/
        excelSourceFile = "数据更新-市场&仓库-1031.xlsx";
        year = "2022";
        quarter = "3";
        newQuarter = false;
        yearBefore = "2022";
        quarterBefore = "2";
        exportFolder = "221031\\";

        sheetNumUpdate = 0;
        sheetNumDelete = 0;
        sheetNumInsert = 5;

        rowNumUpdate = 1;
        rowNumDelete = 1;
        rowNumInsert = 1;
        /*-------每次调整--------------------------------*/


        fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

        updatedFile = fileLocation + excelSourceFile;
        sqlColumnName = fileLocation + "excelDatabaseMapping\\cityQuarter.xlsx";

        outputFileDelete = fileLocation + exportFolder + "cityQuarterDelete.sql";
        outputFileUpdate = fileLocation + exportFolder + "cityQuarterUpdate.sql";
        outputFileInsert = fileLocation + exportFolder + "cityQuarterInsert.sql";
        dataType = "1";
    }
}
