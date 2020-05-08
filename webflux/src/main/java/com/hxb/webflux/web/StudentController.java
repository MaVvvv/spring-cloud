package com.hxb.webflux.web;

import com.hxb.webflux.domain.IStudentDao;
import com.hxb.webflux.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-05-31 17:14
 */
@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    private IStudentDao studentDaoImpl;

    @GetMapping(value = "/show/{id}",produces = "application/json")
    public Mono<StudentDTO> getStudentById (@PathVariable("id") int id) {
        return studentDaoImpl.getStudent(id);
    }

    /**
     * 
     *
     * @param
     * @return 
     * @author Ma_wei
     * @since 2019/5/31
     */
    @GetMapping(value = "/show/all")
    public Flux<StudentDTO> getAllStudent () {
        return studentDaoImpl.getAllStudents();
    }

    @PostMapping(value = "/create",produces = "application/json;charset=UTF-8")
    public Mono<Void> createStudent (@RequestBody StudentDTO studentDTO) {
        System.out.println(studentDTO);

        //Mono<StudentDTO> mono = Mono.just(studentDTO);
        //return studentDaoImpl.saveStudent(mono);
        return null;
    }
}
