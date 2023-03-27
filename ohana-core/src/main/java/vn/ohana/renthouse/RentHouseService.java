package vn.ohana.renthouse;


import vn.ohana.entities.RentHouse;

import java.util.List;
import java.util.Optional;

public interface RentHouseService  {

    List<RentHouse> findAll();

    Optional<RentHouse> findById(Long id);

    RentHouse getById(Long id);

    RentHouse save(RentHouse rentHouse);

    void remove(Long id);
}
