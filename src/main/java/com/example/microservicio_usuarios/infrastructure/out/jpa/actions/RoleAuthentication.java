package com.example.microservicio_usuarios.infrastructure.out.jpa.actions;

import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.RolEntity;
import com.example.microservicio_usuarios.infrastructure.out.jpa.repository.IRolRepository;
import com.example.microservicio_usuarios.infrastructure.out.jpa.exception.RoleNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public class RoleAuthentication {

    public static RolEntity getRoleWithAuthentication(IRolRepository roleRepository) {
        //Gets the session context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //The role is extracted from the session
        String roleName = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new RoleNotFoundException("Role not found in authentication context"));

        Long idRole = provideRoleByName(roleName);

        return roleRepository.findById(idRole).orElseThrow(() -> new RoleNotFoundException("Role not found in repository"));
    }

    public static Long provideRoleByName(String roleName) {
        Long idRole = 3L;
        if (roleName.equalsIgnoreCase("ADMIN")) {
            idRole = 2L;
        } else if (roleName.equalsIgnoreCase("OWNER")) {
            idRole = 3L;
        }
        return idRole;
    }

}
