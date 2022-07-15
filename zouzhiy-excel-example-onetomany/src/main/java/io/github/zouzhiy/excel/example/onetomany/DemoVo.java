package io.github.zouzhiy.excel.example.onetomany;

import io.github.zouzhiy.excel.annotation.ExcelField;
import io.github.zouzhiy.excel.handler.list.ListStringStringSplitHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class DemoVo {

    private final static Random random = new Random(System.currentTimeMillis());

    private String name;

    private String title;

    @ExcelField(cellHandler = ListStringStringSplitHandler.class)
    private List<String> valueList;

    public static List<DemoVo> getList() {
        int size = random.nextInt(500);
        List<DemoVo> demoVoList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            DemoVo demoVo = DemoVo.builder()
                    .name("name-" + random.nextInt(900))
                    .title("title-" + random.nextInt(111))
                    .valueList(getRandomValueList())
                    .build();
            demoVoList.add(demoVo);
        }
        return demoVoList;
    }

    private static List<String> getRandomValueList() {
        int size = random.nextInt(10);
        List<String> valueList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            valueList.add("" + random.nextDouble());
        }
        return valueList;
    }
}
