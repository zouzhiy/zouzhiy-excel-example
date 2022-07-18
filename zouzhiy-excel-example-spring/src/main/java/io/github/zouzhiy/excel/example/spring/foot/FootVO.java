package io.github.zouzhiy.excel.example.spring.foot;

import io.github.zouzhiy.excel.annotation.ExcelClass;
import io.github.zouzhiy.excel.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelClass(rowFootWrite = CustomRowFootWrite.class)
public class FootVO {

    @ExcelField(title = "姓名")
    private String username;


    @ExcelField(title = "年龄")
    private Integer age;


    @ExcelField(title = "分数")
    private BigDecimal score;

}
