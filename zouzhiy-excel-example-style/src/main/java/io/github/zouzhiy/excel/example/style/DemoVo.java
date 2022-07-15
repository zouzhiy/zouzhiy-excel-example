package io.github.zouzhiy.excel.example.style;

import io.github.zouzhiy.excel.annotation.ExcelClass;
import io.github.zouzhiy.excel.annotation.ExcelField;
import io.github.zouzhiy.excel.annotation.ExcelFont;
import io.github.zouzhiy.excel.annotation.ExcelStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zouzhiy
 * @since 2022/7/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelClass(titleStyle = @ExcelStyle(borderLeft = BorderStyle.THIN, rotation = 45, wrapText = true, font = @ExcelFont(fontHeightInPoints = 32, bold = true, italic = true)))
public class DemoVo {

    @ExcelField(dataStyle = @ExcelStyle(font = @ExcelFont(bold = true, italic = true)), headStyle = @ExcelStyle(font = @ExcelFont(italic = true)))
    private String name;

    @ExcelField(dataStyle = @ExcelStyle(font = @ExcelFont(bold = true, italic = true, color = Font.COLOR_RED)), headStyle = @ExcelStyle(font = @ExcelFont(italic = true)))
    private String title;

    public static List<DemoVo> getList() {
        Random random = new Random(System.currentTimeMillis());
        int size = random.nextInt(5000);
        List<DemoVo> demoVoList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            DemoVo demoVo = DemoVo.builder()
                    .name("name-" + random.nextInt(900))
                    .title("title-" + random.nextInt(111))
                    .build();
            demoVoList.add(demoVo);
        }
        return demoVoList;
    }
}
