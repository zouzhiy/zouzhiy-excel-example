package io.github.zouzhiy.excel.example.spring.foot;

import io.github.zouzhiy.excel.context.SheetContext;
import io.github.zouzhiy.excel.write.RowFootWrite;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CustomRowFootWrite implements RowFootWrite {

    @Override
    public int write(SheetContext sheetContext, List<?> dataList) {
        BigDecimal totalScore = BigDecimal.ZERO;

        for (Object item : dataList) {
            FootVO footVO = (FootVO) item;
            BigDecimal score = footVO.getScore();
            if (score != null) {
                totalScore = totalScore.add(score);
            }
        }
        BigDecimal averageScore = totalScore.divide(new BigDecimal(dataList.size()), RoundingMode.HALF_DOWN);

        Sheet sheet = sheetContext.getSheet();
        int rowNum = sheet.getLastRowNum();

        int totalRowNum = rowNum + 1;
        int averageRowNum = rowNum + 2;

        Row totalRow = sheet.createRow(totalRowNum);
        Cell totalCellHead = totalRow.createCell(1);
        totalCellHead.setCellValue("合计");
        Cell totalCellValue = totalRow.createCell(2);
        totalCellValue.setCellValue(totalScore.doubleValue());

        Row averageRow = sheet.createRow(averageRowNum);
        Cell averageCellHead = averageRow.createCell(1);
        averageCellHead.setCellValue("平均值");
        Cell averageCellValue = averageRow.createCell(2);
        averageCellValue.setCellValue(averageScore.doubleValue());

        return 2;
    }
}
