package io.github.zouzhiy.excel.example.normal;

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

    private String name;

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
