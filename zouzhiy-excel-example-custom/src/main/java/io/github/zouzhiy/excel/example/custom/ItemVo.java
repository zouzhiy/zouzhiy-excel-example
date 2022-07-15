package io.github.zouzhiy.excel.example.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * @author zouzhiy
 * @since 2022/7/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemVo {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    private String firstName;

    private String lastName;

    private Integer age;

    public static ItemVo newInstance() {
        return ItemVo
                .builder()
                .firstName(RANDOM.nextBoolean() ? null : "firstName-" + RANDOM.nextDouble())
                .lastName(RANDOM.nextBoolean() ? null : "lastName-" + RANDOM.nextDouble())
                .age(RANDOM.nextBoolean() ? null : RANDOM.nextInt(100))
                .build();
    }
}
