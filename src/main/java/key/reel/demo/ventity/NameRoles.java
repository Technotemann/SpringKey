package key.reel.demo.ventity;

import java.sql.Array;
import java.sql.SQLException;

public class NameRoles {
    private String name;
    private String[] roles;

    public NameRoles(Array roles, Array name) throws SQLException {
        String[] samenames =(String[]) name.getArray();
        this.name = samenames[0];
        this.roles = (String[]) roles.getArray();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
