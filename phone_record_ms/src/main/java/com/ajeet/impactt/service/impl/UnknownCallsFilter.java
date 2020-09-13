
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.service.dto.CallDataDto;
import com.ajeet.impactt.service.ifc.ICDRFilter;
import com.ajeet.impactt.service.ifc.IPhoneBookRepoService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class UnknownCallsFilter implements ICDRFilter {

    @Autowired
    private IPhoneBookRepoService phoneBookRepoService;

    @Override
    public List<CallDataDto> run(List<CallDataDto> dtos) {
        return dtos.stream().filter(f -> phoneBookRepoService.isContactExists(f.getDestinationNumber())).collect(Collectors.toList());
    }
}
