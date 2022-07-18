package io.github.zouzhiy.excel.example.spring.onetomany2;

import io.github.zouzhiy.excel.annotation.ExcelClass;
import io.github.zouzhiy.excel.annotation.ExcelField;
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
public class OneToManyCustomVO {

    private String username;

    private String tel;

    private Integer age;

    private LocalDate birthDay;

    private BigDecimal score;

    @ExcelField(colspan = 2, cellHandler = ListOneToManyItemStringSplitHandler.class)
    private List<OneToManyItemVO> itemList;

}

