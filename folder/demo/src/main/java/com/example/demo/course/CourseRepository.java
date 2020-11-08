package com.example.demo.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

//    public List<Course> getCoursesByTopic(String topicId);
    public List<Course> findByName(String name); //tìm kiếm tên bất kì
    public List<Course> findByTopicId(String topicId);
}
