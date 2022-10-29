package com.techelper.tropsmart_backend.user.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.customer.domain.services.comunications.CustomerResponse;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.DriverResponse;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.services.comunications.BlockedResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.FavoriteResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.UserResponse;

public interface IUserService extends ICrudService<User> {
    FavoriteResponse setFavourited(int userId, int favoriteId);
    BlockedResponse setBlocked(int userId, int blockedId);
    UserResponse findAllUsers();
    UserResponse findAllUsersByType(int userType);
    FavoriteResponse findFavoritesByUserId(int userId);
    BlockedResponse findBlockedsByUserId(int userId);
    FavoriteResponse findAllFavourites();
    BlockedResponse findAllBlockeds();
    UserResponse findUserByEmail(String email);
    UserResponse findUserById(int userId);
    FavoriteResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId);
    BlockedResponse findBlockByUserIdAndBlockedId(int userId, int blockedId);
    FavoriteResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId);
    BlockedResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId);

    CustomerResponse findCustomerByUserId(int userId);
    DriverResponse findDriverByUserId(int userId);

}

