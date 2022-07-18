package io.github.zouzhiy.excel.example.spring.foot;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("foot")
public class FootExportContrller {

    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @GetMapping("list/export")
    public void exportUserList(HttpServletResponse response) throws IOException {
        List<FootVO> voList = this.listVo();

        response.addHeader("Content-Disposition"
                , "attachment; filename*=utf-8''" + URLEncoder.encode("列表导出（表尾）.xlsx", StandardCharsets.UTF_8.name()));
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .sheet()
                .title("列表导出（表尾）")
                .titleRowStartIndex(0)
                .dataRowStartIndex(2)
                .write(voList, FootVO.class);
    }


    private List<FootVO> listVo() {
        Random random = new Random(System.currentTimeMillis());
        List<FootVO> voList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FootVO vo = FootVO.builder()
                    .username("姓名-" + i)
                    .age(random.nextInt(30))
                    .score(BigDecimal.valueOf(random.nextDouble()))
                    .build();
            voList.add(vo);
        }
        return voList;
    }
}
