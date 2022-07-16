package io.github.zouzhiy.excel.example.spring.user;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import io.github.zouzhiy.excel.example.spring.user.UserVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserImportContrller {

    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @PostMapping("list/import")
    public List<UserVO> importUserList(@RequestPart MultipartFile file) throws IOException {

        return zouzhiyExcelFactory
                .read(file.getInputStream())
                .sheet()
                .dataRowStartIndex(2)
                .read(UserVO.class);
    }
}
