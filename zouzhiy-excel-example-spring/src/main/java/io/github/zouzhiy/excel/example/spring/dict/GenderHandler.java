package io.github.zouzhiy.excel.example.spring.dict;

import io.github.zouzhiy.excel.context.RowContext;
import io.github.zouzhiy.excel.context.SheetContext;
import io.github.zouzhiy.excel.enums.ExcelType;
import io.github.zouzhiy.excel.handler.AbstractCellHandler;
import io.github.zouzhiy.excel.metadata.config.ExcelFieldConfig;
import io.github.zouzhiy.excel.metadata.result.CellResult;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

@Component
public class GenderHandler extends AbstractCellHandler<Integer> {

    private final static String UNKNOWN_STRING = "未知";
    private final static String MALE_STRING = "男";
    private final static String FEMALE_STRING = "女";

    private final static int UNKNOWN_INT = 0;
    private final static int MALE_INT = 1;
    private final static int FEMALE_INT = 2;

    @Override
    protected Integer getCellValue(SheetContext sheetContext, ExcelFieldConfig excelFieldConfig, CellResult firstCellResult) {
        String genderStr = firstCellResult.getStringValue();
        if (genderStr == null) {
            return null;
        }
        switch (genderStr) {
            case MALE_STRING:
                return MALE_INT;
            case FEMALE_STRING:
                return FEMALE_INT;
            default:
                return UNKNOWN_INT;
        }
    }

    @Override
    protected void setCellValue(RowContext rowContext, ExcelFieldConfig excelFieldConfig, Cell cell, Integer value) {
        switch (value) {
            case MALE_INT:
                cell.setCellValue(MALE_STRING);
                break;
            case FEMALE_INT:
                cell.setCellValue(FEMALE_STRING);
                break;
            default:
                cell.setCellValue(UNKNOWN_STRING);
                break;
        }
    }

    @Override
    public ExcelType getExcelType() {
        return ExcelType.STRING;
    }
}
