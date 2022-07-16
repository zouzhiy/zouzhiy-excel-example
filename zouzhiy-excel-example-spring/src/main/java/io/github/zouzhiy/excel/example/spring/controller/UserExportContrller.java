package io.github.zouzhiy.excel.example.spring.controller;

import io.github.zouzhiy.excel.builder.ZouzhiyExcelFactory;
import io.github.zouzhiy.excel.example.spring.vo.UserVO;
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
@RequestMapping("user")
public class UserExportContrller {

    @Resource
    private ZouzhiyExcelFactory zouzhiyExcelFactory;

    @GetMapping("list/export")
    public void exportUserList(HttpServletResponse response) throws IOException {
        List<UserVO> userVOList = this.listUser();

        response.addHeader("Content-Disposition"
                , "attachment; filename*=utf-8''" + URLEncoder.encode("用户信息.xlsx", StandardCharsets.UTF_8.name()));
        zouzhiyExcelFactory
                .write(response.getOutputStream())
                .sheet()
                .title("用户信息")
                .titleRowStartIndex(0)
                .dataRowStartIndex(2)
                .write(userVOList, UserVO.class);
    }


    private List<UserVO> listUser() {
        Random random = new Random(System.currentTimeMillis());
        List<UserVO> userVOList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserVO userVO = UserVO.builder()
                    .username("姓名-" + i)
                    .tel(Math.abs(random.nextLong()) + "")
                    .age(10 + i)
                    .birthDay(LocalDate.of(2022, 7, random.nextInt(29) + 1))
                    .score(BigDecimal.valueOf(random.nextDouble()))
                    .build();
            userVOList.add(userVO);
        }
        return userVOList;
    }
}
