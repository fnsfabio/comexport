package com.br.fns.comexport.repositories;

import com.br.fns.comexport.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasRole('ROLE_USER')")
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends User> S save(S s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteById(Long aLong);
}
