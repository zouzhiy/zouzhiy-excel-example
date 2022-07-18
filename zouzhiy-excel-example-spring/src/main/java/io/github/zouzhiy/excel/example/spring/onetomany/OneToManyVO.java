package io.github.zouzhiy.excel.example.spring.onetomany;

import io.github.zouzhiy.excel.annotation.ExcelClass;
import io.github.zouzhiy.excel.annotation.ExcelField;
import io.github.zouzhiy.excel.handler.list.ListStringStringSplitHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelClass
public class OneToManyVO {

    @ExcelField(title = "姓名")
    private String username;

    @ExcelField(title = "手机号码")
    private String tel;

    @ExcelField(title = "年龄")
    private Integer age;

    @ExcelField(title = "出生日期")
    private LocalDate birthDay;

    @ExcelField(title = "分数")
    private BigDecimal score;

    @ExcelField(title = "职务", cellHandler = ListStringStringSplitHandler.class)
    private List<String> positionList;

}
