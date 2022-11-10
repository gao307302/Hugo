package org.example.utils;

import org.apache.poi.ss.usermodel.Cell;

public class CommonUtils {
    public static boolean nullCellCheck(Cell cell) {
        if(cell.getStringCellValue() == null
                || cell.getStringCellValue() == ""
                || cell.getStringCellValue() == "N/A"
                || cell.getStringCellValue() == "#N/A"
                || cell.getStringCellValue() == "-") {
            return true;
        } else {
            return false;
        }
    }
    public static boolean nullStringCheck(String str) {
        if(str == null
                || str.equals("")
                || str.equals("N/A")
                || str.equals("#N/A")
                || str.equals("-")) {
            return true;
        } else {
            return false;
        }
    }
}
