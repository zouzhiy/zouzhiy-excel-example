package io.github.zouzhiy.excel.example.spring.onetomany2;

import io.github.zouzhiy.excel.enums.ExcelType;
import io.github.zouzhiy.excel.handler.list.AbstractListSplitHandler;
import org.springframework.stereotype.Component;

@Component
public class ListOneToManyItemStringSplitHandler extends AbstractListSplitHandler<OneToManyItemVO> {

    @Override
    public ExcelType getExcelType() {
        return ExcelType.STRING;
    }
}
