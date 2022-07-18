package io.github.zouzhiy.excel.example.spring.dict;

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
@RequestMapping("dict-demo")
public class DictDemoExportController {

    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @GetMapping("list/export")
    public void exportList(HttpServletResponse response) throws IOException {
        List<DictDemoVO> voList = this.listVo();

        response.addHeader("Content-Disposition"
                , "attachment; filename*=utf-8''" + URLEncoder.encode("列表信息（字典扩展）.xlsx", StandardCharsets.UTF_8.name()));
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .sheet()
                .title("列表信息（字典扩展）")
                .titleRowStartIndex(0)
                .dataRowStartIndex(2)
                .write(voList, DictDemoVO.class);
    }


    private List<DictDemoVO> listVo() {
        Random random = new Random(System.currentTimeMillis());
        List<DictDemoVO> voList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DictDemoVO vo = DictDemoVO.builder()
                    .username("姓名-" + i)
                    .tel(Math.abs(random.nextLong()) + "")
                    .age(10 + i)
                    .birthDay(LocalDate.of(2022, 7, random.nextInt(29) + 1))
                    .score(BigDecimal.valueOf(random.nextDouble()))
                    .gender(random.nextInt(3))
                    .build();
            voList.add(vo);
        }
        return voList;
    }
}
