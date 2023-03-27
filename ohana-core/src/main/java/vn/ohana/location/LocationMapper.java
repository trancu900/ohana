package vn.ohana.location;

import org.springframework.stereotype.Component;
import vn.ohana.entities.Location;
import vn.ohana.location.dto.LocationParam;
import vn.ohana.location.dto.LocationResult;

@Component
public class LocationMapper {

    public Location toLocation(LocationParam locationParam) {
        Location result = new Location();
        result.setProvinceId(locationParam.getProvinceId());
        result.setProvinceName(locationParam.getProvinceName());
        result.setProvinceUnsignedName(locationParam.getProvinceUnsignedName());

        result.setDistrictId(locationParam.getDistrictId());
        result.setDistrictName(locationParam.getDistrictName());
        result.setDistrictUnsignedName(locationParam.getDistrictUnsignedName());

        result.setWardId(locationParam.getWardId());
        result.setWardName(locationParam.getWardName());
        result.setWardUnsignedName(locationParam.getWardUnsignedName());

        result.setLocationDetail(locationParam.getLocationDetail());
        result.setLocationUnsignedDetail(locationParam.getLocationUnsignedDetail());
        return result;
    }

    public LocationResult toLocationResult(Location location) {
        LocationResult locationResult = new LocationResult();
        locationResult.setProvinceId(location.getProvinceId());
        locationResult.setProvinceName(location.getProvinceName());
        locationResult.setDistrictId(location.getDistrictId());
        locationResult.setDistrictName(location.getDistrictName());
        locationResult.setWardId(location.getWardId());
        locationResult.setWardName(location.getWardName());
        locationResult.setLocationDetail(location.getLocationDetail());
        return locationResult;
    }

}
