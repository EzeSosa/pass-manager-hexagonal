package com.esosa.pass_manager_hexagonal.domain.ports.input.password;

import com.esosa.pass_manager_hexagonal.domain.extras.CustomPage;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface GetUserPasswordsUseCase {
    CustomPage<Password> getUserPasswords(User user, int pageNumber, int pageSize, String sortBy);
}