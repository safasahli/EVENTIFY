package com.example.demo.entities;

import com.example.demo.security.user.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.user.Permission.*;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),  // Rôle sans permissions
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  MANAGER_READ,
                  MANAGER_UPDATE,
                  MANAGER_DELETE,
                  MANAGER_CREATE
          )
  ),
  MANAGER(
          Set.of(
                  MANAGER_READ,
                  MANAGER_UPDATE,
                  MANAGER_DELETE,
                  MANAGER_CREATE
          )
  );

  @Getter
  private final Set<Permission> permissions;

  // Cette méthode permet de retourner les autorités nécessaires à Spring Security
  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = permissions
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));  // Ajout du rôle comme autorité
    return authorities;
  }
}
