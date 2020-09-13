
package com.ajeet.impactt.service.ifc;

import com.ajeet.impactt.service.dto.CallDataDto;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface ICDRFilter {

    List<CallDataDto> run(List<CallDataDto> dtos);
}
