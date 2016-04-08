package com.arhs.cube.thats.my.spot.repository;

import com.arhs.cube.thats.my.spot.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
