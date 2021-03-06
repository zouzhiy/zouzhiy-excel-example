package io.github.zouzhiy.excel.example.spring.dict;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
