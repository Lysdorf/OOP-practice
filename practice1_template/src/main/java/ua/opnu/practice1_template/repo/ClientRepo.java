package ua.opnu.practice1_template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.opnu.practice1_template.entity.ClientEntity;

@Repository
public interface ClientRepo extends JpaRepository<ClientEntity, Long>{

}