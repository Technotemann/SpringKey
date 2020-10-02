package key.reel.demo.dao;

import key.reel.demo.entity.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        return jdbcTemplate.query(
                "SELECT id, name FROM user",
                new MapSqlParameterSource(),
                (ResultSet rs) -> {
                    List<User> result = new ArrayList<>();
                    while (rs.next()) {
                        result.add(toUser(rs));
                    }
                    return result;
                }
        );
    }

    public User getUsersById(int inputId) {
        return jdbcTemplate.query(
                "SELECT id, name FROM user WHERE id = :myid",
                new MapSqlParameterSource().addValue("myid", inputId),
                (ResultSet rs ) -> {
                    if(rs.next()){
                        return toUser(rs);
                    }
                    else return null;
                }
        );
    }

    private static User toUser(ResultSet rsr) throws SQLException {
        return new User(
                rsr.getInt("id"),
                rsr.getString("name")
        );
    }
}
