package com.mlb.mlb_api.service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.repositories.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO); //Generate an id for the encoming data - before passing it to the db
        return playerRepository.save(player); //playerRepo does not accept playerDTO
    }

    @Override
    public Player update(PlayerDTO playerDTO) {

//        Optional<Player> playerToUpdateOptional = this.playerRepository.findById(encomingId); //question if false what returns
//        if (playerToUpdateOptional.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id " + player.getId());
//        }
//        Player playerToUpdate = playerToUpdateOptional.get();
//
//
////        //using a previously defined method (DRY)
//        // getPlayerById(player.getId());
//
//        playerToUpdate.setName(player.getName() ==null || player.getName().isEmpty() ?  playerToUpdate.getName() :player.getName());
//
//        //another way
//
//        if(player.getAge() == null){
//            playerToUpdate.setAge(playerToUpdate.getAge());
//        } else if(player.getAge()<18) {
//            playerToUpdate.setAge(playerToUpdate.getAge());
//        } else {
//            playerToUpdate.setAge(player.getAge());
//        }
//
//
//        if (player.getRating() != null) {
//            playerToUpdate.setRating(player.getRating());
//        }
//        if (player.getYearsOfExperience() != null){
//            playerToUpdate.setYearsOfExperience(player.getYearsOfExperience());
//        }
//
//        return this.playerRepository.save(playerToUpdate);
//    }
//
//
//
//
//
        return null;
    }

    @Override
    public void delete(Integer playerId) {

    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Integer playerId) {
        return null;
    }

    @Override
    public Iterable saveAll(Iterable<PlayerDTO> playerDTOS) {
        return null;
    }

}
