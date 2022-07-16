package io.github.zouzhiy.excel.example.spring.dict;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import io.github.zouzhiy.excel.example.spring.user.UserVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
public class DictDemoImportController {

    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @PostMapping("list/import")
    public List<DictDemoVO> exportList(@RequestPart MultipartFile file) throws IOException {
        return zouzhiyExcelFactory
                .read(file.getInputStream())
                .sheet()
                .dataRowStartIndex(2)
                .read(DictDemoVO.class);
    }

}
