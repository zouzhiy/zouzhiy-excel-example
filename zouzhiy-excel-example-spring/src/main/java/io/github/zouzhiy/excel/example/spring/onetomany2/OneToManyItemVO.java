package io.github.zouzhiy.excel.example.spring.onetomany2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OneToManyItemVO {

    private String itemName;

    private BigDecimal itemScore;

}
