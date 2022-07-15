package io.github.zouzhiy.excel.example.custom;

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

    private ItemVo item;


    public static List<DemoVo> getList() {
        int size = random.nextInt(500);
        List<DemoVo> demoVoList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            DemoVo demoVo = DemoVo.builder()
                    .name("name-" + random.nextInt(900))
                    .title("title-" + random.nextInt(111))
                    .item(ItemVo.newInstance())
                    .build();
            demoVoList.add(demoVo);
        }
        return demoVoList;
    }

}
