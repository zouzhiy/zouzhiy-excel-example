package io.github.zouzhiy.excel.example.mulcolumn;

import io.github.zouzhiy.excel.context.RowContext;
import io.github.zouzhiy.excel.context.SheetContext;
import io.github.zouzhiy.excel.enums.ExcelType;
import io.github.zouzhiy.excel.handler.CellHandler;
import io.github.zouzhiy.excel.handler.ints.IntegerNumberHandler;
import io.github.zouzhiy.excel.handler.string.StringStringHandler;
import io.github.zouzhiy.excel.metadata.config.ExcelFieldConfig;
import io.github.zouzhiy.excel.metadata.result.CellResult;
import io.github.zouzhiy.excel.metadata.result.CellResultSet;
import io.github.zouzhiy.excel.utils.RegionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * @author zouzhiy
 * @since 2022/7/15
 */
public class ItemCellHandler implements CellHandler<ItemVo> {


    @Override
    public Class<ItemVo> getJavaType() {
        return ItemVo.class;
    }

    @Override
    public ExcelType getExcelType() {
        return ExcelType.STRING;
    }

    @Override
    public ItemVo read(SheetContext sheetContext, ExcelFieldConfig excelFieldConfig, CellResultSet cellResultSet) {
        if (cellResultSet == null) {
            return null;
        }

        if (cellResultSet.isNone()) {
            return null;
        }
        List<CellResult> cellResultList = cellResultSet.getCellResultListList().get(0);
        CellResult cellResultFirstName = cellResultList.size() > 0 ? cellResultList.get(0) : CellResult.none();
        CellResult cellResultSecondName = cellResultList.size() > 1 ? cellResultList.get(1) : CellResult.none();
        CellResult cellResultAge = cellResultList.size() > 2 ? cellResultList.get(2) : CellResult.none();

        CellHandler<String> stringStringHandler = sheetContext.getConfiguration().getCellHandlerRegistry().getCellHandler(StringStringHandler.class);

        String firstName = stringStringHandler.read(sheetContext, excelFieldConfig, CellResultSet.firstCellResult(cellResultFirstName));
        String secondName = stringStringHandler.read(sheetContext, excelFieldConfig, CellResultSet.firstCellResult(cellResultSecondName));
        CellHandler<Integer> integerCellHandler = sheetContext.getConfiguration().getCellHandlerRegistry().getCellHandler(IntegerNumberHandler.class);
        Integer age = integerCellHandler.read(sheetContext, excelFieldConfig, CellResultSet.firstCellResult(cellResultAge));

        return ItemVo.builder().firstName(firstName).lastName(secondName).age(age).build();
    }

    @Override
    public void write(RowContext rowContext, Integer columnIndex, ExcelFieldConfig excelFieldConfig, ItemVo value) {
        if (value == null) {
            return;
        }
        Row row = rowContext.getRowList().get(0);
        Cell cellFirstName = row.createCell(columnIndex);
        Cell cellLastName = row.createCell(columnIndex + 1);
        Cell cellAge = row.createCell(columnIndex + 2);
        if (value.getFirstName() != null) {
            cellFirstName.setCellValue(value.getFirstName());
        }
        if (value.getLastName() != null) {
            cellLastName.setCellValue(value.getLastName());
        }
        if (value.getAge() != null) {
            cellAge.setCellValue(value.getAge());
        }

        SheetContext sheetContext = rowContext.getSheetContext();
        CellStyle cellStyle = sheetContext.getDataCellStyle(excelFieldConfig, this.getDefaultExcelFormat());
        cellFirstName.setCellStyle(cellStyle);
        cellLastName.setCellStyle(cellStyle);
        cellAge.setCellStyle(cellStyle);

        int rowspan = rowContext.getRowspan();
        int rowIndex = row.getRowNum();
        RegionUtils.addMergedRegionIfPresent(sheetContext, cellStyle, rowIndex, rowIndex + rowspan - 1, columnIndex, columnIndex);
        RegionUtils.addMergedRegionIfPresent(sheetContext, cellStyle, rowIndex, rowIndex + rowspan - 1, columnIndex + 1, columnIndex + 1);
        RegionUtils.addMergedRegionIfPresent(sheetContext, cellStyle, rowIndex, rowIndex + rowspan - 1, columnIndex + 2, columnIndex + 2);
    }
}
