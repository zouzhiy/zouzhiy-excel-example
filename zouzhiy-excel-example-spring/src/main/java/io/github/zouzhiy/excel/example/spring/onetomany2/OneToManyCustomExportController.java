package io.github.zouzhiy.excel.example.spring.onetomany2;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("one-to-many-custom")
public class OneToManyCustomExportController {

    private final Random random = new Random(System.currentTimeMillis());
    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @GetMapping("list/export")
    public void exportList(HttpServletResponse response) throws IOException {

        List<OneToManyCustomVO> voList = this.listVo();

        String exportTemplateFilePath = "template/export/列表信息（一对多自定义）-template.xlsx";
        InputStream exportTemplateInputStream = this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath);


        response.addHeader("Content-Disposition"
                , "attachment; filename*=utf-8''" + URLEncoder.encode("列表信息（一对多自定义模板导出）.xlsx", StandardCharsets.UTF_8.name()));
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .template(exportTemplateInputStream)
                .sheet()
                .title("列表信息（一对多自定义模板导出）")
                .titleRowStartIndex(0)
                .headRowStartIndex(-1)
                .dataRowStartIndex(3)
                .write(voList, OneToManyCustomVO.class);
    }

    private List<OneToManyCustomVO> listVo() {
        List<OneToManyCustomVO> voList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OneToManyCustomVO vo = OneToManyCustomVO.builder()
                    .username("姓名-" + i)
                    .tel(Math.abs(random.nextLong()) + "")
                    .age(10 + i)
                    .birthDay(LocalDate.of(2022, 7, random.nextInt(29) + 1))
                    .score(BigDecimal.valueOf(random.nextDouble()))
                    .itemList(this.listItem(i))
                    .build();
            voList.add(vo);
        }
        return voList;
    }

    private List<OneToManyItemVO> listItem(int sno) {
        int size = random.nextInt(5) + 2;
        List<OneToManyItemVO> itemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            OneToManyItemVO item = OneToManyItemVO.builder()
                    .itemName("itemName-" + sno + "-" + i)
                    .itemScore(BigDecimal.valueOf(random.nextDouble()))
                    .build();
            itemList.add(item);
        }
        return itemList;
    }
}
