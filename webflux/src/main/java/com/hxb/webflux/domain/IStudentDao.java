package com.hxb.webflux.domain;

import com.hxb.webflux.dto.StudentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-05-31 17:03
 */
public interface IStudentDao {

    Mono<StudentDTO> getStudent(int id);

    Flux<StudentDTO> getAllStudents();

    Mono<Void> saveStudent (Mono<StudentDTO> studentDTOMono);
}
