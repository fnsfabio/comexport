package com.br.fns.comexport.repositories;

import com.br.fns.comexport.entity.role.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasRole('ROLE_USER')")
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Role> S save(S s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Role role);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteById(Long aLong);
}
