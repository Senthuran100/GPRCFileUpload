package com.senthuran.demo.repository;

import com.senthuran.demo.model.File;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepo extends JpaRepository<File,Integer> {

}
