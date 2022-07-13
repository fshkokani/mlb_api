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
    //@PostMapping("/add")
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO); //Generate an id for the encoming data - before passing it to the db
        return playerRepository.save(player); //playerRepo does not accept playerDTO
    }

    @Override
    public Player update(PlayerDTO playerDTO, Integer encomingId) {

        Optional<Player> playerToUpdateOptional = this.playerRepository.findById(encomingId); //question if false what returns
        if (playerToUpdateOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id " + encomingId);
        }
        Player playerToUpdate = playerToUpdateOptional.get();


//       //You can repurpose using a previously defined method (DRY)

        playerToUpdate.setName(playerToUpdate.getName() ==null || playerToUpdate.getName().isEmpty() ?  playerToUpdate.getName() :playerToUpdate.getName());

        //another way

        if(playerToUpdate.getAge() == null){
            playerToUpdate.setAge(playerToUpdate.getAge());
        } else if(playerToUpdate.getAge()<18) {
            playerToUpdate.setAge(playerToUpdate.getAge());
        } else {
            playerToUpdate.setAge(playerToUpdate.getAge());
        }


        if (playerToUpdate.getRating() != null) {
            playerToUpdate.setRating(playerToUpdate.getRating());
        }
        if (playerToUpdate.getYearsOfExperience() != null){
            playerToUpdate.setYearsOfExperience(playerToUpdate.getYearsOfExperience());
        }

        return this.playerRepository.save(playerToUpdate);
    }


    @Override
    public void delete(Integer playerId) {

    }

    @Override
    // @GetMapping
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
