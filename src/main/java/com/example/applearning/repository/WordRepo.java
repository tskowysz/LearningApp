package com.example.applearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface WordRepo extends JpaRepository<Words,Long> {

}
