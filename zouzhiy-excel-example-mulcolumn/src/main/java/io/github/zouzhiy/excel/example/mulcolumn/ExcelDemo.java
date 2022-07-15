package io.github.zouzhiy.excel.example.mulcolumn;


import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 数据跨列适合模板导出
 *
 * @author zouzhiy
 * @since 2022/7/15
 */
public class ExcelDemo {

    private final ZouzhiyExcelFactory zouzhiyExcelFactory = ZouzhiyExcelFactoryBuilder
            .builder()
            .register(new ItemCellHandler())
            .build();


    @Test
    void exportNoTemplate() {
        String rootPath = this.getClass().getResource("/").getPath();

        // 无标题，无表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .sheet()
                .dataRowStartIndex(0)
                .write(DemoVo.getList(), DemoVo.class);
        // 无标题，有表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .sheet()
                .dataRowStartIndex(1)
                .write(DemoVo.getList(), DemoVo.class);
        // 有标题，无表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .sheet()
                .title("有标题无表头")
                .titleRowStartIndex(0)
                .headRowStartIndex(-1)
                .dataRowStartIndex(1)
                .write(DemoVo.getList(), DemoVo.class);

        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .sheet()
                .title("有标题有表头")
                .dataRowStartIndex(3)
                .write(DemoVo.getList(), DemoVo.class);
    }

    @Test
    void exportWithTemplate() {
        String exportTemplateFilePath = "template/export.xlsx";

        String rootPath = this.getClass().getResource("/").getPath();
        // 不覆盖标题，不覆盖标题表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .template(this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath))
                .sheet()
                .titleRowStartIndex(-1)
                .headRowStartIndex(-1)
                .dataRowStartIndex(3)
                .write(DemoVo.getList(), DemoVo.class);

        // 覆盖标题，不覆盖标题表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .template(this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath))
                .sheet()
                .title("覆盖标题")
                .titleRowStartIndex(0)
                .headRowStartIndex(-1)
                .dataRowStartIndex(3)
                .write(DemoVo.getList(), DemoVo.class);

        // 不覆盖标题，覆盖表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .template(this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath))
                .sheet()
                .titleRowStartIndex(-1)
                .headRowStartIndex(1)
                .dataRowStartIndex(3)
                .write(DemoVo.getList(), DemoVo.class);

        // 覆盖标题，覆盖标题表头
        zouzhiyExcelFactory.write(new File(rootPath + File.separator + System.currentTimeMillis() + ".xlsx"))
                .template(this.getClass().getClassLoader().getResourceAsStream(exportTemplateFilePath))
                .sheet()
                .title("覆盖标题，覆盖表头")
                .dataRowStartIndex(3)
                .write(DemoVo.getList(), DemoVo.class);
    }

    @Test
    void importExcel() {
        String inputFilePath = "template/import.xlsx";

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(inputFilePath);

        List<DemoVo> demoVoList = zouzhiyExcelFactory.read(inputStream).sheet(0).dataRowStartIndex(3).read(DemoVo.class);
        for (DemoVo demoVo : demoVoList) {
            System.out.println(demoVo);
        }
    }
}
