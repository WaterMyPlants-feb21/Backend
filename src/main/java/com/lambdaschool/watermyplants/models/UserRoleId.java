package com.lambdaschool.watermyplants.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {

    private long user;

    private long role;

    public UserRoleId() {
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserRoleId that = (UserRoleId) o;
        return user == that.user &&
                role == that.role;
    }

    @Override
    public int hashCode()
    {
        return 16;
    }
}
