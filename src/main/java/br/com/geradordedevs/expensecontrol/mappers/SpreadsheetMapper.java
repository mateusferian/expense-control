package br.com.geradordedevs.expensecontrol.mappers;

import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class SpreadsheetMapper {

    @Autowired
    private final ModelMapper mapper;
    public SpreadsheetResponseDTO toDto(SpreadsheetEntity entity){
        log.info("converting entity{} to dto", entity);
        return  mapper.map(entity, SpreadsheetResponseDTO.class);
    }

    public List<SpreadsheetResponseDTO> toDtoList(Iterable<SpreadsheetEntity> lista){
        log.info("converting entity list{} to dto list", lista);
        List<SpreadsheetEntity> resultado = new ArrayList<>();
        lista.forEach(resultado::add);
        return  resultado.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
