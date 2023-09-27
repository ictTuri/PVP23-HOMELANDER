package com.codeonmars.usersms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.usersms.exception.UserNotFoundException;
import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.model.user.dto.FullUserContextDto;
import com.codeonmars.usersms.model.user.projection.SimpleUserProjection;
import com.codeonmars.usersms.remote.FilesApi;
import com.codeonmars.usersms.remote.PropertiesApi;
import com.codeonmars.usersms.repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserGeneralService {

    public static final String CAN_NOT_FIND_USER_WITH_GIVEN_USERNAME = "Can not find user with given username!";
    public static final String YOU_ARE_NOT_THE_LOGGED_USER = "You are not the logged user!";
    private final UserRepository userRepository;
    private final PropertiesApi propertiesApi;
    private final FilesApi filesApi;
    private final Mapper dozer;

    public UserGeneralService(UserRepository userRepository, PropertiesApi propertiesApi, FilesApi filesApi, Mapper dozer) {
        this.userRepository = userRepository;
        this.propertiesApi = propertiesApi;
        this.filesApi = filesApi;
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

    public FullUserContextDto getFullUserContext(String email) {
        var context = UserContextHolder.getContext();
        if (context.isPresent() && context.get().getEmail().equals(email)) {
            var loggedUser = context.get();
            var fullUser = new FullUserContextDto();
            var user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                var userData = user.get();
                Map<String, Set<Long>> properties = propertiesApi.getUsersProperties(userData.getUsername(), userData.getEmail());
                dozer.map(userData, fullUser);
                fullUser.setPropertyOwned(properties.getOrDefault("own", null));
                fullUser.setPropertyRented(properties.getOrDefault("rents", null));
                fullUser.setCanAddProperty(loggedUser.canAddProperties());
                fullUser.setCanAddTenant(loggedUser.canAddTenants());
                return fullUser;
            }
            throw new UserNotFoundException(CAN_NOT_FIND_USER_WITH_GIVEN_USERNAME);
        }
        throw new UserNotFoundException(YOU_ARE_NOT_THE_LOGGED_USER);
    }

    public void setProfileUUID(String uuid) {
        var context = UserContextHolder.getContext();
        if (context.isPresent()) {
            var userContext = context.get();
            var userEntity = userRepository.findByUsernameAndEmail(userContext.getUsername(), userContext.getEmail());
            if (userEntity.isPresent()) {
                var user = userEntity.get();
                var oldUUID = Optional.of(user.getProfilePicUUID()).orElse(null);
                user.setProfilePicUUID(uuid);
                if (!oldUUID.isEmpty()) {
                    filesApi.unlinkFile(oldUUID);
                }
                userRepository.save(user);
            }
        }
    }

    /* SUPPORTING METHODS */
}
