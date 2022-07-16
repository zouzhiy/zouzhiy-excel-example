package io.github.zouzhiy.excel.example.spring.dict;

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
public class DictDemoVO {

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

    /**
     * 性别
     * <pre>
     *     0 未知性别
     *     1 男性
     *     2 女性
     * </pre>
     */
    @ExcelField( title = "性别", cellHandler = GenderHandler.class)
    private Integer gender;
}
