package zxf.springboot.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class MyAuthentication implements Authentication {
    private final MyUser myUser;

    public MyAuthentication(MyUser myUser) {
        this.myUser = myUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return myUser;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return myUser.getName();
    }

    public static class MyUser implements Serializable {
        private final String name;
        private Integer age = 1;

        public MyUser(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
