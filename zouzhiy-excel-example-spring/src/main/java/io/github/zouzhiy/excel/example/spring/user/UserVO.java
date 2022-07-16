package io.github.zouzhiy.excel.example.spring.user;

import io.github.zouzhiy.excel.annotation.ExcelClass;
import io.github.zouzhiy.excel.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@ExcelClass
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

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
}
