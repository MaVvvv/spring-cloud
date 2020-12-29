package com.mxw.hxb.springboot.test;

/**
 * 班级
 *
 * @author Ma_wei
 * @since 2020/7/31 17:17
 */
public class ClassGroup {

    private Long studentId;

    private Long teacherId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ClassGroup{" +
                "studentId=" + studentId +
                ", teacherId=" + teacherId +
                '}';
    }
}
