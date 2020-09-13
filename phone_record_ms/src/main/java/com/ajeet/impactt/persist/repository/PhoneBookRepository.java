package com.ajeet.impactt.persist.repository;

import com.ajeet.impactt.persist.entity.PhoneBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Repository
public interface PhoneBookRepository extends CrudRepository<PhoneBook, Long> {
}
