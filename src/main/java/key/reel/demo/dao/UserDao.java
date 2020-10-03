package key.reel.demo.dao;

import key.reel.demo.entity.Role;
import key.reel.demo.entity.User;
import key.reel.demo.ventity.NameRoles;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Role> getRoles() {
        return jdbcTemplate.query(
                "SELECT id, role FROM role",
                new MapSqlParameterSource(),
                (ResultSet rs) -> {
                    List<Role> result = new ArrayList<>();
                    while (rs.next()) {
                        result.add(toRole(rs));
                    }
                    return result;
                }
        );
    }

    public Role getRoleById(int inputId) {
        return jdbcTemplate.query(
                "SELECT id, role FROM role WHERE id = :myid",
                new MapSqlParameterSource().addValue("myid", inputId),
                (ResultSet rs ) -> {
                    if(rs.next()){
                        return toRole(rs);
                    }
                    else return null;
                }
        );
    }


    public NameRoles getNameRolesByLogin(String inputLogin) {
        return jdbcTemplate.query(
                "SELECT role, name FROM (roles JOIN ur USING (id)) JOIN users USING (login) where login = :mylogin",
                new MapSqlParameterSource().addValue("mylogin", inputLogin),
                (ResultSet rs ) -> {
                    if(rs.next()){
                        return (toNameRoles(rs));
                    }
                    else return null;
                }
        );
    }

    public List<String> getAllUsers() {
        return jdbcTemplate.query(
                "SELECT login FROM users",
                new MapSqlParameterSource(),
                (ResultSet rs) -> {
                    List<String> result = new ArrayList<>();
                    while (rs.next()) {
                        result.add(toLogin(rs));
                    }
                    return result;
                }
        );
    }

    public String getUserNameByLogin(int inputLogin) {
        return jdbcTemplate.query(
                "SELECT name FROM users WHERE login = :mylogin",
                new MapSqlParameterSource().addValue("mylogin", inputLogin),
                (ResultSet rs ) -> {
                    if(rs.next()){
                        return toUserName(rs);
                    }
                    else return null;
                }
        );
    }

    public int addNewUser(String inputLogin, String inputName, String inputPassword) {
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("mylogin", inputLogin);
        param.addValue("myname", inputName);
        param.addValue("mypassword", inputPassword);
        return jdbcTemplate.update(
                "INSERT INTO users (login, name, password) values (:mylogin,:myname,:mypassword)", param);

    }

    public int removeUserByLogin(String inputLogin) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE login = :mylogin",
                new MapSqlParameterSource().addValue("mylogin", inputLogin) );

    }

    private static String toUserName(ResultSet rsr) throws SQLException {
        return rsr.getString("name");
    }

    private static String toRoleName(ResultSet rsr) throws SQLException {
        return rsr.getString("role");
    }

    private static String toLogin(ResultSet rsr) throws SQLException {
        return rsr.getString("login");
    }

    private static Role toRole(ResultSet rsr) throws SQLException {
        return new Role(
                rsr.getInt("id"),
                rsr.getString("role")
        );
    }
    private static NameRoles toNameRoles(ResultSet rsr) throws SQLException {
        return new NameRoles(
                rsr.getArray("name"),
                rsr.getArray("roles")
        );
    }
    private static User toUser(ResultSet rsr) throws SQLException {
        return new User(
                rsr.getString("login"),
                rsr.getString("name"),
                rsr.getString("password")
        );
    }
}
