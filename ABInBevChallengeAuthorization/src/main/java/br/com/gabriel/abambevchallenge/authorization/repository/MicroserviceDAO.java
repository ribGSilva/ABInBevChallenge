package br.com.gabriel.abambevchallenge.authorization.repository;


import br.com.gabriel.abambevchallenge.authorization.beans.vo.ClientCredentialsVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroserviceDAO extends MongoRepository<ClientCredentialsVO, String> {
}
