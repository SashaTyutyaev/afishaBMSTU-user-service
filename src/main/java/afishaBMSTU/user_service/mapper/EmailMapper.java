package afishaBMSTU.user_service.mapper;

import afishaBMSTU.user_service.dto.EmailDto;
import afishaBMSTU.user_service.model.email.Email;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMapper {
    EmailDto toEmailDto(Email email);
}