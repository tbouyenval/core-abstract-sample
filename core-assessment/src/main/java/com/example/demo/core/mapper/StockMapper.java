package com.example.demo.core.mapper;

import com.example.demo.dto.in.stock.ShoeUpdate;
import com.example.demo.dto.out.stock.Stock;
import com.example.demo.dto.out.stock.StockState;
import com.example.demo.entities.ShoeEntity;
import com.example.demo.entities.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper( StockMapper.class );

    @Mapping(source = MappingValueEnum.ENTITY_STOCK_LIST, target = MappingValueEnum.DTO_SHOES)
    @Mapping(source = MappingValueEnum.ENTITY_STOCK_TOTAL, target = MappingValueEnum.DTO_STOCK_STATE, qualifiedByName = "getStockState")
    Stock stockEntityToStock(StockEntity stockEntity);

    @Mapping(source = MappingValueEnum.DTO_SHOE_NAME, target = MappingValueEnum.ENTITY_SHOE_NAME)
    @Mapping(source = MappingValueEnum.DTO_SHOE_SIZE, target = MappingValueEnum.ENTITY_SHOE_SIZE)
    @Mapping(source = MappingValueEnum.DTO_SHOE_COLOR, target = MappingValueEnum.ENTITY_SHOE_COLOR)
    @Mapping(source = MappingValueEnum.DTO_SHOE_QUANTITY, target = MappingValueEnum.ENTITY_SHOE_QUANTITY)
    ShoeEntity shoeInputToShoeEntity(ShoeUpdate shoeUpdate);


    @Named("getStockState")
    static StockState getStockState(int stockTotal) {
        return switch (stockTotal){
            case 0 : yield StockState.EMPTY;
            case 30 : yield StockState.FULL;
            default: yield StockState.SOME;
        };
    }
}
