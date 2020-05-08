package com.channelsoft.druid.controller;

import com.channelsoft.druid.domain.UserDao;
import com.channelsoft.druid.dto.UserDTO;
import com.channelsoft.druid.utils.ScriptUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-06-06 10:09
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(1); //最大支持3600个好友
    }*/

    @GetMapping("/getUser")
    public String getUser (HttpServletRequest request) throws FileNotFoundException, SQLException, ScriptException {
        log.info("1111" + request.getContextPath());
        DataSource dataSource = jdbcTemplate.getDataSource();
        assert dataSource != null;
        Connection connection = dataSource.getConnection();
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        Resources.setCharset(Charset.forName("UTF-8"));
        scriptRunner.setLogWriter(new PrintWriter("D:\\tmp\\gls\\webrtc\\sql.log"));//设置是否输出日志
        scriptRunner.runScript(new FileReader(new File("D:\\tmp\\gls\\webrtc\\init.sql")));
        //scriptRunner.runScript(Resources.getResourceAsReader("sql/CC21-01.sql"));
        //ScriptUtils.executeSqlScript(connection,new InputStreamResource(new FileInputStream("D:\\tmp\\gls\\webrtc\\init.sql")));
        // 关闭连接
        //DataSourceUtils.releaseConnection(connection,dataSource);
        scriptRunner.closeConnection();
        connection.close();

        List<UserDTO> userDTOS = userDao.getUser();
        ListIterator<UserDTO> iterator = userDTOS.listIterator();
        while (iterator.hasNext()) {
            UserDTO userDTO = iterator.next();
            System.out.println(userDTO.toString());
        }
        return "aaa";
    }

    @GetMapping("/getUser1")
    public String getUser1 () throws IOException {
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader;
        try {
            Process process = Runtime.getRuntime().exec("D:\\tmp\\gls\\webrtc\\test.bat");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "getUser1";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(UserDTO userDTO) {
        log.debug("进入szveUser方法!");
        log.info(userDTO.toString());
        List<String> books = userDTO.getBooks();
        log.info("总共书籍信息为：{}",books.size());
        log.info("books.get(2) = {}",books.get(2));
        return userDTO.getId();
    }
}
