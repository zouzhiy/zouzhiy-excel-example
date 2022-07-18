package io.github.zouzhiy.excel.example.spring.onetomany2;

import io.github.zouzhiy.excel.context.RowContext;
import io.github.zouzhiy.excel.context.SheetContext;
import io.github.zouzhiy.excel.enums.ExcelType;
import io.github.zouzhiy.excel.handler.CellHandler;
import io.github.zouzhiy.excel.handler.bigdecimal.BigDecimalNumberHandler;
import io.github.zouzhiy.excel.handler.string.StringStringHandler;
import io.github.zouzhiy.excel.metadata.config.ExcelFieldConfig;
import io.github.zouzhiy.excel.metadata.result.CellResult;
import io.github.zouzhiy.excel.metadata.result.CellResultSet;
import io.github.zouzhiy.excel.utils.RegionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OneToManyItemStringCellHandler implements CellHandler<OneToManyItemVO> {

    @Override
    public Class<OneToManyItemVO> getJavaType() {
        return OneToManyItemVO.class;
    }

    @Override
    public ExcelType getExcelType() {
        return ExcelType.STRING;
    }

    @Override
    public OneToManyItemVO read(SheetContext sheetContext, ExcelFieldConfig excelFieldConfig, CellResultSet cellResultSet) {
        if (cellResultSet == null) {
            return null;
        }

        if (cellResultSet.isNone()) {
            return null;
        }
        List<CellResult> cellResultList = cellResultSet.getCellResultListList().get(0);
        CellResult cellResultFirst = cellResultList.size() > 0 ? cellResultList.get(0) : CellResult.none();
        CellResult cellResultSecond = cellResultList.size() > 1 ? cellResultList.get(1) : CellResult.none();

        CellHandler<String> stringStringHandler = sheetContext.getConfiguration().getCellHandlerRegistry().getCellHandler(StringStringHandler.class);
        String itemName = stringStringHandler.read(sheetContext, excelFieldConfig, CellResultSet.firstCellResult(cellResultFirst));

        CellHandler<BigDecimal> bigDecimalCellHandler = sheetContext.getConfiguration().getCellHandlerRegistry().getCellHandler(BigDecimalNumberHandler.class);
        BigDecimal itemScore = bigDecimalCellHandler.read(sheetContext, excelFieldConfig, CellResultSet.firstCellResult(cellResultSecond));

        return OneToManyItemVO.builder().itemName(itemName).itemScore(itemScore).build();
    }

    @Override
    public void write(RowContext rowContext, Integer columnIndex, ExcelFieldConfig excelFieldConfig, OneToManyItemVO value) {
        if (value == null) {
            return;
        }
        Row row = rowContext.getRowList().get(0);
        Cell cellFirst = row.createCell(columnIndex);
        Cell cellSecond = row.createCell(columnIndex + 1);

        if (value.getItemName() != null) {
            cellFirst.setCellValue(value.getItemName());
        }
        if (value.getItemScore() != null) {
            cellSecond.setCellValue(value.getItemScore().doubleValue());
        }


        SheetContext sheetContext = rowContext.getSheetContext();
        CellStyle cellStyle = sheetContext.getDataCellStyle(excelFieldConfig, this.getDefaultExcelFormat());
        cellFirst.setCellStyle(cellStyle);
        cellSecond.setCellStyle(cellStyle);

        int rowspan = rowContext.getRowspan();
        int rowIndex = row.getRowNum();
        RegionUtils.addMergedRegionIfPresent(sheetContext, cellStyle, rowIndex, rowIndex + rowspan - 1, columnIndex, columnIndex);
        RegionUtils.addMergedRegionIfPresent(sheetContext, cellStyle, rowIndex, rowIndex + rowspan - 1, columnIndex + 1, columnIndex + 1);
    }
}
