package org.example.market.year.district;

import javafx.util.Pair;
import org.example.utils.FilePos;

public class FilePosYearDistrict extends FilePos {
    static {
        /*------------------------每次调整--------------------------------*/
        newQuarter = false;
        excelSourceFile = "数据更新_市场信息_1025.xlsx";
        year = "2022";
        quarter = "3";
        yearBefore = "2022";
        quarterBefore = "2";
        exportFolder = "221103\\";

        sheetNumUpdate = 3;
        sheetNumDelete = 1;
        sheetNumInsert = 3;

        rowNumUpdate = 1;
        rowNumDelete = 1;
        rowNumInsert = 1;
        /*------------------------每次调整--------------------------------*/

        datePair = new Pair<>("年度", "季度");

        tableName = "t_market_overview_year";

        fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

        updatedFile = fileLocation + excelSourceFile;
        sqlColumnName = fileLocation + "excelDatabaseMapping\\districtYear.xlsx";

        outputFileDelete = fileLocation + exportFolder + "districtYearDelete.sql";
        outputFileUpdate = fileLocation + exportFolder + "districtYearUpdate.sql";
        outputFileInsert = fileLocation + exportFolder + "districtYearInsert.sql";

    }
}
