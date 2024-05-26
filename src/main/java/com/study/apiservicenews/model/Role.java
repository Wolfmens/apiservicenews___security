package com.study.apiservicenews.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client client;

    public GrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(roleType.name());
    }

    public static Role from(RoleType roleType) {
        var role = new Role();
        role.setRoleType(roleType);

        return role;
    }

}
