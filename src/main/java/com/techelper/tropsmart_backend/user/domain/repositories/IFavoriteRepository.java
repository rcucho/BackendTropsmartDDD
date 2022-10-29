package com.techelper.tropsmart_backend.user.domain.repositories;

import com.techelper.tropsmart_backend.user.domain.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query("select f from Favorite f where f.user.id = (:uid)")
    List<Favorite> findFavouritesByUserId(@Param("uid") int userId);

    @Query("select f from Favorite f where f.user.id = (:uid) and f.favorited.id = (:fid)")
    Favorite findFavouriteByUserAndFavouriteId(@Param("uid")int userId, @Param("fid")int favouriteId);
}