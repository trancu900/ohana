package vn.ohana.utility;

import vn.ohana.entities.StatusUtility;
import vn.ohana.utility.dto.UpdateUtilityParam;
import vn.ohana.utility.dto.UtilityResult;

import java.util.List;
import java.util.Optional;

public interface UtilityService {
    List<UtilityResult> findAll();

    Optional<UtilityResult> findById(Long id);


    void show(Long utilityId);

    void hidden(Long utilityId);

    List<UtilityResult> findAllById(Iterable<Long> longs);

    List<UtilityResult> findAllByStatus(StatusUtility status);


    List<UtilityResult> findAllByIdASC(List<Long> utilityIds);

    void deleteById(Long id);

    UtilityResult update(UpdateUtilityParam param);
}
