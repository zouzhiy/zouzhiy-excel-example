package io.github.zouzhiy.excel.example.custom;

import io.github.zouzhiy.excel.context.RowContext;
import io.github.zouzhiy.excel.context.SheetContext;
import io.github.zouzhiy.excel.enums.ExcelType;
import io.github.zouzhiy.excel.handler.AbstractCellHandler;
import io.github.zouzhiy.excel.metadata.config.ExcelFieldConfig;
import io.github.zouzhiy.excel.metadata.result.CellResult;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;

/**
 * @author zouzhiy
 * @since 2022/7/15
 */
public class ItemCellHandler extends AbstractCellHandler<ItemVo> {

    @Override
    protected ItemVo getCellValue(SheetContext sheetContext, ExcelFieldConfig excelFieldConfig, CellResult firstCellResult) {
        String value = firstCellResult.getStringValue();
        String[] values = value.split(",", -1);

        return ItemVo.builder().firstName(values[0])
                .lastName(values[1])
                .age((values[2] == null || "null".equals(values[2]) || values[2].length() == 0) ? null : new BigDecimal(values[2]).intValue())
                .build();
    }

    @Override
    protected void setCellValue(RowContext rowContext, ExcelFieldConfig excelFieldConfig, Cell cell, ItemVo value) {
        cell.setCellValue(String.format("%s,%s,%s", value.getFirstName(), value.getLastName(), value.getAge()));
    }

    @Override
    public ExcelType getExcelType() {
        return ExcelType.STRING;
    }

    @Override
    protected ItemVo getBlankValue() {
        return ItemVo.builder().build();
    }
}
