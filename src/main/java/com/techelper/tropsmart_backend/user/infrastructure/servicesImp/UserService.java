package com.techelper.tropsmart_backend.user.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.configuration.application.exception.ResourceNotFoundException;
import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import com.techelper.tropsmart_backend.customer.domain.services.comunications.CustomerResponse;
import com.techelper.tropsmart_backend.customer.domain.services.outputs.CustomerOutput;
import com.techelper.tropsmart_backend.driver.domain.models.Driver;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.DriverResponse;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.DriverOutput;
import com.techelper.tropsmart_backend.user.domain.models.Block;
import com.techelper.tropsmart_backend.user.domain.models.Favorite;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IBlockRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IFavoriteRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import com.techelper.tropsmart_backend.user.domain.services.IUserService;
import com.techelper.tropsmart_backend.user.domain.services.comunications.BlockedResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.FavoriteResponse;
import com.techelper.tropsmart_backend.user.domain.services.comunications.UserResponse;
import com.techelper.tropsmart_backend.user.domain.services.outputs.BlockedOutput;
import com.techelper.tropsmart_backend.user.domain.services.outputs.FavoriteOutput;
import com.techelper.tropsmart_backend.user.domain.services.outputs.UserOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFavoriteRepository favoriteRepository;

    @Autowired
    private IBlockRepository blockRepository;


    @Override
    public FavoriteResponse setFavourited(int userId, int favouriteId)
    {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserFavourite = userRepository.findById(favouriteId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",favouriteId));

            if(getUser.getPerson().getPersonType()==2 || getUserFavourite.getPerson().getPersonType()==1)
                return new FavoriteResponse("Only customers can add driver as favourite");

            Favorite newFavourite = new Favorite();
            newFavourite.setUser(getUser);
            newFavourite.setFavorited(getUserFavourite);
            newFavourite.setCreatedAt(new Date());
            newFavourite = favoriteRepository.save(newFavourite);

            return new FavoriteResponse(toFavoriteOutput(newFavourite));
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while saving the favourite: : "+e.getMessage());
        }
    }

    @Override
    public BlockedResponse setBlocked(int userId, int blockedId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserBlocked = userRepository.findById(blockedId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",blockedId));

            Block newBlock =  new Block();
            newBlock.setUser(getUser);
            newBlock.setBlocked(getUserBlocked);
            newBlock.setCreatedAt(new Date());

            newBlock = blockRepository.save(newBlock);
            return new BlockedResponse(toBlockedOutput(newBlock));
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while saving the blocked: : "+e.getMessage());
        }
    }

    @Override
    public UserResponse findAllUsers() {
        try
        {
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                userOutputList.add(toUserOutput(u));
            }
            return new UserResponse(userOutputList);
        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while getting the user list : "+e.getMessage());
        }
    }


    @Override
    public FavoriteResponse findFavoritesByUserId(int userId) {
        try
        {
            List<Favorite> favoriteList = favoriteRepository.findFavouritesByUserId(userId);
            List<FavoriteOutput> favouriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                favouriteOutputList.add(toFavoriteOutput(f));
            }
            return new FavoriteResponse(favouriteOutputList);
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while getting the favourite list : "+e.getMessage());

        }
    }

    @Override
    public BlockedResponse findBlockedsByUserId(int userId) {
        try
        {
            List<Block> blockList = blockRepository.findBlockedsByUserId(userId);
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                blockedOutputList.add(toBlockedOutput(b));
            }
            return new BlockedResponse(blockedOutputList);
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while getting the blocked list : "+e.getMessage());
        }

    }

    @Override
    public FavoriteResponse findAllFavourites() {
        try
        {
            List<Favorite> favoriteList = favoriteRepository.findAll();
            List<FavoriteOutput> favoriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                favoriteOutputList.add(toFavoriteOutput(f));
            }
            return new FavoriteResponse(favoriteOutputList);
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while getting the favourite list : "+e.getMessage());

        }
    }

    @Override
    public BlockedResponse findAllBlockeds() {
        try
        {
            List<Block> blockList = blockRepository.findAll();
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                blockedOutputList.add(toBlockedOutput(b));
            }
            return new BlockedResponse(blockedOutputList);
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while getting the blocked list : "+e.getMessage());
        }
    }

    @Override
    public FavoriteResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId) {
        try
        {
            Favorite getFavourite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favouriteId);
            return new FavoriteResponse(toFavoriteOutput(getFavourite));
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while getting the favourite relation : "+e.getMessage());
        }
    }

    @Override
    public BlockedResponse findBlockByUserIdAndBlockedId(int userId, int blockedId) {
        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            return new BlockedResponse(toBlockedOutput(getBlock));
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while getting the blocked relation : "+e.getMessage());
        }
    }

    @Override
    public UserResponse findAllUsersByType(int userType) {

        try{
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                if(u.getPerson().getPersonType()==userType) {
                    userOutputList.add(toUserOutput(u));
                }

            }
            return new UserResponse(userOutputList);
        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while getting the user : "+e.getMessage());
        }
    }


    @Override
    public FavoriteResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId) {
        try
        {
            Favorite getFavorite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favoriteId);
            favoriteRepository.deleteById(getFavorite.getId());
            return new FavoriteResponse(toFavoriteOutput(getFavorite));
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while deleting the favourite relation : "+e.getMessage());
        }
    }

    @Override
    public BlockedResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId) {
        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            blockRepository.deleteById(getBlock.getId());
            return new BlockedResponse(toBlockedOutput(getBlock));
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while deleting the blocked relation : "+e.getMessage());
        }
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        try
        {
            User getUser = userRepository.findPersonByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("email","user",email));

            return new UserResponse(toUserOutput(getUser));
        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while getting the user : "+e.getMessage());
        }

    }

    @Override
    public UserResponse findUserById(int userId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
            return new UserResponse(toUserOutput(getUser));
        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while updating the user"+e.getMessage());
        }
    }

    @Override
    public CustomerResponse findCustomerByUserId(int userId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("userId","user",userId));
            Customer getCustomer = getUser.getPerson().getCustomer();
            return new CustomerResponse(new CustomerOutput(getCustomer.getPerson().getUser().getId(),getCustomer.getPerson().getFirstName(),getCustomer.getPerson().getLastName(),getCustomer.getCredits(),getCustomer.getPerson().getUser().getEmail(), getCustomer.getPerson().getPersonType(), getCustomer.getId()));
        }
        catch (Exception e)
        {
            return new CustomerResponse("An error ocurred while getting customer: "+e.getMessage());
        }
    }

    @Override
    public DriverResponse findDriverByUserId(int userId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("Id","user",userId));

            Driver getDriver = getUser.getPerson().getDriver();
            return new DriverResponse(new DriverOutput(getDriver.getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(), getDriver.getId()));

        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver: "+e.getMessage());

        }
    }


    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> findById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    public UserOutput toUserOutput(User user){
        UserOutput newUserOutput = new UserOutput();
        newUserOutput.setId(user.getId());
        newUserOutput.setEmail(user.getEmail());
        newUserOutput.setFirstName(user.getPerson().getFirstName());
        newUserOutput.setLastName(user.getPerson().getLastName());
        newUserOutput.setPassword(user.getPassword());

        if(user.getPerson().getPersonType()==1)
            newUserOutput.setRole("Customer");
        else if(user.getPerson().getPersonType()==2)
            newUserOutput.setRole("Driver");

        if(user.getPerson().getPersonType()==1)
            newUserOutput.setRoleId(user.getPerson().getCustomer().getId());
        if(user.getPerson().getPersonType()==2)
            newUserOutput.setRoleId(user.getPerson().getDriver().getId());

        return newUserOutput;
    }

    public FavoriteOutput toFavoriteOutput(Favorite favorite)
    {
        FavoriteOutput newFavouriteOutput = new FavoriteOutput();
        newFavouriteOutput.setUser(favorite.getUser().getPerson().getFirstName()+" "+favorite.getUser().getPerson().getLastName());
        newFavouriteOutput.setFavourited(favorite.getFavorited().getPerson().getFirstName()+" "+favorite.getFavorited().getPerson().getLastName());
        newFavouriteOutput.setSince(favorite.getCreatedAt());
        return newFavouriteOutput;
    }

    public BlockedOutput toBlockedOutput(Block block)
    {
        BlockedOutput newBlockedOutput = new BlockedOutput();
        newBlockedOutput.setUser(block.getUser().getPerson().getFirstName()+" "+block.getBlocked().getPerson().getLastName());
        newBlockedOutput.setBlocked(block.getBlocked().getPerson().getFirstName()+" "+block.getBlocked().getPerson().getLastName());
        newBlockedOutput.setSince(block.getCreatedAt());
        return newBlockedOutput;
    }
}
