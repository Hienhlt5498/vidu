package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Course getCourse(String id) {

//        return courses.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//
        return courseRepository.findById(id).get();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
//        for (int i = 0; i < courses.size(); i++) {
//            Course t = courses.get(i);
//            if (t.getId().equals(id)) {
//                courses.set(i, course);
//                return;
//            }
//        }
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
//        courses.removeIf(t ->t.getId().equals(id));
        courseRepository.deleteById(id);
    }
}
