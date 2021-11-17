package com.email.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.email.model.Email;
import javax.persistence.*;

public interface EmailDao extends JpaRepository<Email,String>
 {

}
