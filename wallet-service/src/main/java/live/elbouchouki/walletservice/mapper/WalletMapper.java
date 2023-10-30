package live.elbouchouki.walletservice.mapper;

import live.elbouchouki.core.dto.shared.PagingResponse;
import live.elbouchouki.core.dto.wallet.WalletCreateRequest;
import live.elbouchouki.core.dto.wallet.WalletResponse;
import live.elbouchouki.walletservice.model.Wallet;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface WalletMapper {
    Wallet toModel(WalletCreateRequest request);

    List<Wallet> toModelList(List<WalletCreateRequest> requestList);

    WalletResponse toResponse(Wallet customer);

    List<WalletResponse> toResponseList(List<Wallet> customerList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(WalletCreateRequest request, @MappingTarget Wallet customer);

    @Mapping(target = "page", expression = "java(model.getNumber())")
    @Mapping(target = "size", expression = "java(model.getSize())")
    @Mapping(target = "totalPages", expression = "java(model.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(model.getNumberOfElements())")
    @Mapping(target = "records", expression = "java(toResponseList(model.getContent()))")
    PagingResponse<WalletResponse> toPagingResponse(Page<Wallet> model);
}
