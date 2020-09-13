package com.ajeet.impactt.service.ifc;

import com.ajeet.impactt.service.dto.PhoneBookDto;

import java.util.List;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public interface IPhoneBookRepoService {
    List<PhoneBookDto> findAll();

    PhoneBookDto find(long phoneNumber);

    boolean isContactExists(long phoneNumber);

    boolean save(PhoneBookDto dto);

    void delete(long number);

    boolean saveAll(List<PhoneBookDto> request);
}
