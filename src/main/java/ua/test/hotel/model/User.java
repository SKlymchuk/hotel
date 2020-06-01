package ua.test.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString(exclude = "applications")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String hashPassword;
    private boolean active;
    private double cashAccount;

    @OneToMany(mappedBy = "user")
    private List<Application> applications;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getHashPassword() {
        return this.hashPassword;
    }

    public boolean isActive() {
        return this.active;
    }

    public double getCashAccount() {
        return this.cashAccount;
    }

    public List<Application> getApplications() {
        return this.applications;
    }

    public Role getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCashAccount(double cashAccount) {
        this.cashAccount = cashAccount;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$hashPassword = this.getHashPassword();
        final Object other$hashPassword = other.getHashPassword();
        if (this$hashPassword == null ? other$hashPassword != null : !this$hashPassword.equals(other$hashPassword))
            return false;
        if (this.isActive() != other.isActive()) return false;
        if (Double.compare(this.getCashAccount(), other.getCashAccount()) != 0) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        return this$role == null ? other$role == null : this$role.equals(other$role);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $hashPassword = this.getHashPassword();
        result = result * PRIME + ($hashPassword == null ? 43 : $hashPassword.hashCode());
        result = result * PRIME + (this.isActive() ? 79 : 97);
        final long $cashAccount = Double.doubleToLongBits(this.getCashAccount());
        result = result * PRIME + (int) ($cashAccount >>> 32 ^ $cashAccount);
        final Object $applications = this.getApplications();
        result = result * PRIME + ($applications == null ? 43 : $applications.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }
}
