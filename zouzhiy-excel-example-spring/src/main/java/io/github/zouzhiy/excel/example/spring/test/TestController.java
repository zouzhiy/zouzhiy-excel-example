package io.github.zouzhiy.excel.example.spring.test;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author zouzhiy
 * @since 2022/7/15
 */
@RestController
@RequestMapping("test")
public class TestController {

    private final Random random = new Random(System.currentTimeMillis());
    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @PostMapping("import")
    public List<DemoVo> importFile(MultipartFile multipartFile) throws IOException {
        return zouzhiyExcelFactory
                .read(multipartFile.getInputStream())
                .sheet()
                .dataRowStartIndex(2)
                .read(DemoVo.class);
    }

    @GetMapping("export/no-template1")
    public void exportDataByNoTemplate(HttpServletResponse response) throws IOException {
        List<DemoVo> demoVoList = Collections.emptyList();

        response.addHeader("Content-Disposition", "attachment; filename*=utf-8''" + "export.xlsx");
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .sheet()
                .title("export")
                .titleRowStartIndex(0)
                .dataRowStartIndex(2)
                .write(demoVoList, DemoVo.class);

    }

    @GetMapping("export/no-template2")
    public void exportDataByTemplate4(HttpServletResponse response) throws IOException {
        List<DemoVo> demoVoList = this.getList();

        response.addHeader("Content-Disposition", "attachment; filename*=utf-8''" + "export.xlsx");
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .sheet()
                .title("export")
                .titleRowStartIndex(0)
                .dataRowStartIndex(2)
                .write(demoVoList, DemoVo.class);

    }

    @GetMapping("export/template1")
    public void exportDataByTemplate1(HttpServletResponse response) throws IOException {
        List<DemoVo> demoVoList = this.getList();

        String exportTemplateFilePath = "template/export/export1.xlsx";
        InputStream exportTemplateInputStream = this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath);

        response.addHeader("Content-Disposition", "attachment; filename*=utf-8''" + "export.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .template(exportTemplateInputStream)
                .sheet()
                .titleRowStartIndex(-1)
                .headRowStartIndex(-1)
                .dataRowStartIndex(2)
                .write(demoVoList, DemoVo.class);

    }

    @GetMapping("export/template2")
    public void exportDataByTemplate2(HttpServletResponse response) throws IOException {
        List<DemoVo> demoVoList = this.getList();

        String exportTemplateFilePath = "template/export/export2.xls";
        InputStream exportTemplateInputStream = this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath);

        response.addHeader("Content-Disposition", "attachment; filename*=utf-8''" + "import.xls");
        response.setContentType("application/vnd.ms-excel");
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .template(exportTemplateInputStream)
                .sheet()
                .titleRowStartIndex(-1)
                .headRowStartIndex(-1)
                .dataRowStartIndex(2)
                .write(demoVoList, DemoVo.class);

    }

    @GetMapping("export/template3")
    public void exportDataByTemplate3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DemoVo> demoVoList = Collections.emptyList();

        RequestContextHolder.resetRequestAttributes();


        String exportTemplateFilePath = "template/export/export2.xls";
        InputStream exportTemplateInputStream = this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath);

        response.addHeader("Content-Disposition", "attachment; filename*=utf-8''" + "import.xls");
        response.setContentType("application/vnd.ms-excel");
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .template(exportTemplateInputStream)
                .sheet()
                .titleRowStartIndex(-1)
                .headRowStartIndex(-1)
                .dataRowStartIndex(2)
                .write(demoVoList, DemoVo.class);

    }

    private List<DemoVo> getList() {
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
