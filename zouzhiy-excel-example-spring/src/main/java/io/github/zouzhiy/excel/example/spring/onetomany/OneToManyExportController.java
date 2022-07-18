package io.github.zouzhiy.excel.example.spring.onetomany;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("one-to-many")
public class OneToManyExportController {

    private final Random random = new Random(System.currentTimeMillis());
    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @GetMapping("list/export")
    public void exportList(HttpServletResponse response) throws IOException {
        List<OneToManyVO> voList = this.listVo();

        response.addHeader("Content-Disposition"
                , "attachment; filename*=utf-8''" + URLEncoder.encode("列表信息（一对多）.xlsx", StandardCharsets.UTF_8.name()));
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .sheet()
                .title("列表信息（一对多）")
                .titleRowStartIndex(0)
                .dataRowStartIndex(2)
                .write(voList, OneToManyVO.class);
    }

    private List<OneToManyVO> listVo() {
        List<OneToManyVO> voList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OneToManyVO vo = OneToManyVO.builder()
                    .username("姓名-" + i)
                    .tel(Math.abs(random.nextLong()) + "")
                    .age(10 + i)
                    .birthDay(LocalDate.of(2022, 7, random.nextInt(29) + 1))
                    .score(BigDecimal.valueOf(random.nextDouble()))
                    .positionList(this.listPosition(i))
                    .build();
            voList.add(vo);
        }
        return voList;
    }

    private List<String> listPosition(int sno) {
        int size = random.nextInt(5);
        List<String> positionList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            positionList.add("position-" + sno + "-" + i);
        }
        return positionList;
    }
}
