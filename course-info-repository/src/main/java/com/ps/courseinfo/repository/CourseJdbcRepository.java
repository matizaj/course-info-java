package com.ps.courseinfo.repository;

import com.ps.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CourseJdbcRepository implements CourseRepository{
    private final DataSource dataSource;
    private static final String h2_db_url = "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";
    private static final String INSERT_COURSE = """
            MERGE INTO Courses(id, name, length, url)
            VALUES (?,?,?,?)
            """;

    public CourseJdbcRepository(String dbFile){
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(h2_db_url.formatted(dbFile));
        this.dataSource = jdbcDataSource;
    }
    @Override
    public void save(Course course) {
        try (var connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setLong(3, course.length());
            statement.setString(4, course.url());
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("failed to save! ", e);
        }

    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try (var connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM COURSES");
            while(result.next()) {
                var course = new Course(result.getString(1),
                        result.getString(2),
                        result.getLong(3),
                        result.getString(4) );
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new RepositoryException("failed to retrieve courses! ", e);
        }
        return Collections.unmodifiableList(courses);
    }
}
