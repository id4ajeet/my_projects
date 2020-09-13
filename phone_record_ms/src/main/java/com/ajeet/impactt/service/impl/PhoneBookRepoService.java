
package com.ajeet.impactt.service.impl;

import com.ajeet.impactt.persist.entity.PhoneBook;
import com.ajeet.impactt.persist.repository.PhoneBookRepository;
import com.ajeet.impactt.service.dto.PhoneBookDto;
import com.ajeet.impactt.service.ifc.IEntityDtoMapper;
import com.ajeet.impactt.service.ifc.IPhoneBookRepoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class PhoneBookRepoService implements IPhoneBookRepoService {

    private static Logger log = LoggerFactory.getLogger(PhoneBookRepoService.class);

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private IEntityDtoMapper entityDtoMapper;

    @Override
    @Transactional
    public List<PhoneBookDto> findAll() {
        List<PhoneBookDto> phoneBooks = new ArrayList<>();
        phoneBookRepository.findAll().forEach(book -> {
            PhoneBookDto bookDto = entityDtoMapper.map(book);
            phoneBooks.add(bookDto);
        });

        return phoneBooks;
    }

    @Override
    @Transactional
    public PhoneBookDto find(long phoneNumber) {
        Optional<PhoneBook> bookOpt = phoneBookRepository.findById(phoneNumber);
        if (bookOpt.isPresent()) {
            return entityDtoMapper.map(bookOpt.get());
        }
        return null;
    }

    @Override
    @Transactional
    public boolean isContactExists(long phoneNumber) {
        boolean present = phoneBookRepository.findById(phoneNumber).isPresent();
        if (!present) {
            log.debug("Filtering record for Caller Number : {}", phoneNumber);
        }
        return present;
    }

    @Override
    @Transactional
    public boolean save(PhoneBookDto dto) {
        Optional<PhoneBook> bookOpt = phoneBookRepository.findById(dto.getNumber());
        PhoneBook phoneBook;
        if (bookOpt.isPresent()) {
            PhoneBook book = bookOpt.get();
            phoneBook = entityDtoMapper.map(dto, book);
        } else {
            phoneBook = entityDtoMapper.map(dto);
        }
        PhoneBook save = phoneBookRepository.save(phoneBook);
        return save != null;
    }

    @Override
    @Transactional
    public void delete(long number) {
        phoneBookRepository.deleteById(number);
    }

    @Override
    public boolean saveAll(List<PhoneBookDto> request) {
        List<PhoneBook> data = request.stream().map(entityDtoMapper::map).collect(Collectors.toList());
        Iterable<PhoneBook> pdata = phoneBookRepository.saveAll(data);
        return pdata != null;
    }
}
