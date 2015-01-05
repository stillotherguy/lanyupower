package cn.lanyu.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import cn.lanyu.auth.Authority;
import cn.lanyu.insurance.Insurance;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

@Entity
@Table(name = "user")
public class User implements UserDetails, CredentialsContainer {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	@Id
	@GeneratedValue
	private long id;
	private String name;
    private String password;
    private String username;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Insurance> insurances;
    @Enumerated(EnumType.STRING)
	private Authority authority;

    public User(String username, String password, Authority authority) {

        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public User() {
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = Lists.newArrayList();
		authorities.add(new SimpleGrantedAuthority(authority.toString()));
        return authorities;
    }

    @Override
	public String getPassword() {
        return password;
    }

    @Override
	public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
    public void eraseCredentials() {
        password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return username.equals(((User) rhs).username);
        }
        return false;
    }

    @Override
    public int hashCode() {
    	if(StringUtils.isEmpty(username)){
    		return super.hashCode();
    	}
        return username.hashCode();
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
        return name;
    }
}
