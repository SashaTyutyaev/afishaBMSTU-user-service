package afishaBMSTU.user_service.mapper;

import afishaBMSTU.user_service.dto.PhoneDto;
import afishaBMSTU.user_service.model.phone.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneMapper {
    PhoneDto toPhoneDto(Phone phone);
}