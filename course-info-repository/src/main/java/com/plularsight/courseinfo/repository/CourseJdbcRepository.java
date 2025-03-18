package com.plularsight.courseinfo.repository;

import com.plularsight.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class CourseJdbcRepository implements CourseRepository {
    private final DataSource dataSource;
    private static final String H2_DATABASE_URL =
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";

    private static final String INSERT_COURSE = """
            MERGE INTO Courses(id, name, length, url)
            VALUES(?,?,?,?)
            """;
    public CourseJdbcRepository(String databaseFile) {
        var jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.dataSource = jdbcDataSource;
    }
    @Override
    public void saveCourse(Course course) {
        try(var connection = dataSource.getConnection()) {
            var statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.title());
            statement.setString(3, course.duration());
            statement.setString(4, course.Url());
            statement.execute();
        } catch(SQLException ex) {
            throw new RepositoryException("Failed to save" + course, ex);
        }

    }

    @Override
    public List<Course> gettAllCourses() {
        try(var connection = dataSource.getConnection()) {
            var statement = connection.createStatement();
            var results = statement.executeQuery("SELECT * FROM COURSES");
            var courseList = new ArrayList<Course>();
            while(results.next()) {
                courseList.add(new Course(results.getString(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4)));
            }
            return Collections.unmodifiableList(courseList);
        } catch(SQLException ex) {
            throw new RepositoryException("Failed to save" + "", ex);
        }
    }
}
