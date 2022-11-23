package org.example.data.traffic.city;

import org.example.utils.FilePos;

public class FilePosTrafficCity extends FilePos {
    public FilePosTrafficCity() {

        /*------------------------每次调整--------------------------------*/
        super.newQuarter = false;
        super.excelSourceFile = "城市行政区界面数据220730.xlsx";
        super.year = "2022";
        super.quarter = "3";
        super.yearBefore = "2022";
        super.quarterBefore = "2";
        super.exportFolder = "221117\\";

        super.sheetNum = 0;
        super.sheetNumUpdate = 3;
        super.sheetNumDelete = 1;
        super.sheetNumInsert = 3;

        super.rowNumUpdate = 1;
        super.rowNumDelete = 1;
        super.rowNumInsert = 1;
        /*------------------------每次调整--------------------------------*/
        super.tableName = "t_traffic_data";
        super.fileLocation = "C:\\Users\\hugo.gao\\OneDrive - JLL\\Documents\\data\\";

        super.updatedFile = fileLocation + excelSourceFile;
        super.sqlColumnName = fileLocation + "excelDatabaseMapping\\trafficCity.xlsx";

        super.outputFile = fileLocation + exportFolder;
        super.outputFileDelete = fileLocation + exportFolder + "trafficCityDelete.sql";
        super.outputFileUpdate = fileLocation + exportFolder + "trafficCityUpdate.sql";
        super.outputFileInsert = fileLocation + exportFolder + "trafficCityInsert.sql";
    }




}
