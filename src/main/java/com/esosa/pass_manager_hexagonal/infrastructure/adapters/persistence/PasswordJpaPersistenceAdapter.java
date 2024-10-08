package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence;

import com.esosa.pass_manager_hexagonal.domain.shared.CustomPage;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.PasswordEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers.CustomPageMapper;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers.PasswordEntityMapper;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers.UserEntityMapper;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.repositories.PasswordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

public class PasswordJpaPersistenceAdapter implements PasswordPersistencePort {

    private final PasswordRepository passwordRepository;
    private final PasswordEntityMapper passwordEntityMapper;
    private final UserEntityMapper userEntityMapper;

    public PasswordJpaPersistenceAdapter(PasswordRepository passwordRepository, PasswordEntityMapper passwordEntityMapper, UserEntityMapper userEntityMapper) {
        this.passwordRepository = passwordRepository;
        this.passwordEntityMapper = passwordEntityMapper;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public Password savePassword(Password password) {
        PasswordEntity _passwordEntity = passwordEntityMapper.toPasswordEntity(password);
        passwordRepository.save(_passwordEntity);
        return passwordEntityMapper.toPasswordDomain(_passwordEntity);
    }

    @Override
    public Optional<Password> getPassword(UUID passwordId) {
        return passwordRepository.findById(passwordId)
                .map(passwordEntityMapper::toPasswordDomain);
    }

    @Override
    public Password updatePassword(Password password) {
        return savePassword(password);
    }

    @Override
    public void deletePassword(UUID passwordId) {
        PasswordEntity _passwordEntity = passwordRepository.findById(passwordId)
                .orElseThrow();
        _passwordEntity.setDeleted(true);
        passwordRepository.save(_passwordEntity);
    }

    @Override
    public boolean existsPasswordById(UUID passwordId) {
        return passwordRepository.existsById(passwordId);
    }

    @Override
    public CustomPage<Password> getUserPasswords(User user, int pageNumber, int pageSize, String sortBy) {
        UserEntity _userEntity = userEntityMapper.toUserEntity(user);
        PageRequest _pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        Page<Password> _userPasswords = passwordRepository.findByUser( _pageRequest, _userEntity )
                .map(passwordEntityMapper::toPasswordDomain);

        return CustomPageMapper.buildCustomPage(_userPasswords);
    }

    @Override
    public boolean existsPasswordByNameAndUser(String name, User user) {
        UserEntity _userEntity = userEntityMapper.toUserEntity(user);
        return passwordRepository.existsByNameAndUser(name, _userEntity);
    }

}