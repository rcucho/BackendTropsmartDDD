package com.techelper.tropsmart_backend.user.application.controllers;

import com.techelper.tropsmart_backend.customer.domain.services.comunications.CustomerResponse;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.DriverResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.BlockedResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.FavoriteResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.UserResponse;
import com.techelper.tropsmart_backend.user.infrastructure.servicesImp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> findAllUsers()
    {
        UserResponse result = userService.findAllUsers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("{userId}/favorites/{userfavoritedId}")
    public ResponseEntity<FavoriteResponse> setUserFavorited(@PathVariable(value = "userId")int userId, @PathVariable(value = "userfavoritedId")int userFavouritedId)
    {
        FavoriteResponse result = userService.setFavourited(userId, userFavouritedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<BlockedResponse> setUserBlocked(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        BlockedResponse result = userService.setBlocked(userId, userBlockedId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable(value = "userId")int userId)
    {
        UserResponse result = userService.findUserById(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //========================================================================

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<FavoriteResponse> findFavoritesByUserId(@PathVariable(value = "userId")int userId)
    {
        FavoriteResponse result = userService.findFavoritesByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/blockeds")
    public ResponseEntity<BlockedResponse> findBlockedsByUserId(@PathVariable(value = "userId")int userId)
    {
        BlockedResponse result = userService.findBlockedsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity<FavoriteResponse> findAllFavourites()
    {
        FavoriteResponse result = userService.findAllFavourites();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/blockeds")
    public ResponseEntity<BlockedResponse> findAllBlockeds()
    {
        BlockedResponse result = userService.findAllBlockeds();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{userId}/customers")
    public ResponseEntity<CustomerResponse> findCustomerByUserId(@PathVariable(value = "userId")int userId)
    {
        CustomerResponse result = userService.findCustomerByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{userId}/drivers")
    public ResponseEntity<DriverResponse> findDriverByUserId(@PathVariable(value = "userId")int userId)
    {
        DriverResponse result = userService.findDriverByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/type/{userType}")
    public ResponseEntity<UserResponse> findUsersTypeCustomers(@PathVariable(value="userType")int userType)
    {
        UserResponse result = userService.findAllUsersByType(userType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //====================================================================

    @DeleteMapping("{userId}/favorites/{userFavoritedId}")
    public ResponseEntity<FavoriteResponse> deleteFavoriteByUserIdAndUserFavoritedId(@PathVariable(value="userId")int userId, @PathVariable(value = "userFavorited")int userFavoritedId)
    {
        FavoriteResponse result = userService.deleteFavoriteByUserIdAndFavoriteId(userId, userFavoritedId);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("{userId}/blockeds/{userBlockedId}")
    public ResponseEntity<BlockedResponse> deleteBlockedByUserIdAndBlockedId(@PathVariable(value = "userId")int userId, @PathVariable(value = "userBlockedId")int userBlockedId)
    {
        BlockedResponse result = userService.deleteBlockByUserIdAndBlockId(userId, userBlockedId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
