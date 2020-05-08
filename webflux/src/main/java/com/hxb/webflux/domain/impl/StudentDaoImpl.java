package com.hxb.webflux.domain.impl;

import com.hxb.webflux.domain.IStudentDao;
import com.hxb.webflux.dto.StudentDTO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-05-31 17:05
 */
@Repository
public class StudentDaoImpl implements IStudentDao {

    private final Map<Integer,StudentDTO> studentDTOMap = new HashMap<>();

    public StudentDaoImpl (){
        this.studentDTOMap.put(11,new StudentDTO("Wu Lei",10,"Male"));
        this.studentDTOMap.put(12,new StudentDTO("Wu Lei",10,"Male"));
    }

    @Override
    public Mono<StudentDTO> getStudent(int id) {
        return Mono.justOrEmpty(this.studentDTOMap.get(id));
    }

    @Override
    public Flux<StudentDTO> getAllStudents() {
        return Flux.fromIterable(studentDTOMap.values());
    }

    @Override
    public Mono<Void> saveStudent(Mono<StudentDTO> studentDTOMono) {
        return studentDTOMono.doOnNext(studentDTO -> {
            int id = studentDTOMap.size() + 1;
            studentDTOMap.put(id, studentDTO);
        }).thenEmpty(Mono.empty());
    }
}
