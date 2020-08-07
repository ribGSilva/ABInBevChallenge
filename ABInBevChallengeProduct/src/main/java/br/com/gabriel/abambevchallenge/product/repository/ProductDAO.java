package br.com.gabriel.abambevchallenge.product.repository;


import br.com.gabriel.abambevchallenge.product.beans.vo.ProductVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<ProductVO, String> {
}
