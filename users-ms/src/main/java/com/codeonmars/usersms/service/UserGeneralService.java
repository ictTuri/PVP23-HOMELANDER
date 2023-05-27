package com.codeonmars.usersms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.usersms.exception.UserNotFoundException;
import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.model.user.dto.FullUserContextDto;
import com.codeonmars.usersms.model.user.projection.SimpleUserProjection;
import com.codeonmars.usersms.remote.PropertiesApi;
import com.codeonmars.usersms.repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class UserGeneralService {

    public static final String CAN_NOT_FIND_USER_WITH_GIVEN_USERNAME = "Can not find user with given username!";
    public static final String YOU_ARE_NOT_TE_LOGGED_USER = "You are not te logged user!";
    private final UserRepository userRepository;
    private final PropertiesApi propertiesApi;
    private final Mapper dozer;

    public UserGeneralService(UserRepository userRepository, PropertiesApi propertiesApi, Mapper dozer) {
        this.userRepository = userRepository;
        this.propertiesApi = propertiesApi;
        this.dozer = dozer;
    }

    public void increaseUsedSlot() {
        var context = UserContextHolder.getContext();
        if (context.isPresent()) {
            var userContext = context.get();
            var user = userRepository.findByUsernameAndEmail(userContext.getUsername(), userContext.getEmail());
            user.ifPresent(UserEntity::increasePropertySlot);
            user.ifPresent(userRepository::save);
        }
    }

    public void decreaseUsedSlot() {
        var context = UserContextHolder.getContext();
        if (context.isPresent()) {
            var userContext = context.get();
            var user = userRepository.findByUsernameAndEmail(userContext.getUsername(), userContext.getEmail());
            user.ifPresent(UserEntity::decreasePropertySlot);
            user.ifPresent(userRepository::save);
        }
    }

    public SimpleUserProjection getSimpleUserData(String email) {
        return userRepository.getSimpleDataByEmail(email).orElse(null);
    }

    public FullUserContextDto getFullUserContext(String username) {
        var context = UserContextHolder.getContext();
        if (context.isPresent() && context.get().getUsername().equals(username)) {
            var fullUser = new FullUserContextDto();
            var user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                var userData = user.get();
                Map<String, Set<Long>> properties = propertiesApi.getUsersProperties(userData.getUsername(), userData.getEmail());
                dozer.map(userData, fullUser);
                fullUser.setPropertyOwned(properties.getOrDefault("own", null));
                fullUser.setPropertyRented(properties.getOrDefault("rents", null));
                return fullUser;
            }
            throw new UserNotFoundException(CAN_NOT_FIND_USER_WITH_GIVEN_USERNAME);
        }
        throw new UserNotFoundException(YOU_ARE_NOT_TE_LOGGED_USER);
    }


    /* SUPPORTING METHODS */
}
