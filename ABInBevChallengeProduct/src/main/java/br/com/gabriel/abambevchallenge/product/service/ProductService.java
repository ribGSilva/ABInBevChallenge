package br.com.gabriel.abambevchallenge.product.service;

import br.com.gabriel.abambevchallenge.product.beans.to.ProductTO;
import br.com.gabriel.abambevchallenge.product.beans.vo.ProductVO;
import br.com.gabriel.abambevchallenge.product.exceptions.ProductException;
import br.com.gabriel.abambevchallenge.product.repository.ProductDAO;
import br.com.gabriel.abambevchallenge.product.utils.ExceptionEnum;
import br.com.gabriel.abambevchallenge.product.utils.ExceptionUtils;
import br.com.gabriel.abambevchallenge.product.validators.ProductValidator;
import br.com.gabriel.abambevchallenge.product.beans.dto.CreateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ProductDAO productDAO;

    public String create(CreateProductDTO createProductDTO) throws ProductException {
        productValidator.validate(createProductDTO);

        ProductVO productVO = new ProductVO(
                UUID.randomUUID().toString(),
                createProductDTO.getName(),
                createProductDTO.getType(),
                createProductDTO.getPrice(),
                createProductDTO.getDescription());

        productVO = productDAO.save(productVO);
        return productVO.getId();
    }

    public ProductTO findId(String id) throws ProductException {
        ProductTO productTO = null;
        Optional<ProductVO> optionalProductVO = productDAO.findById(id);
        if (optionalProductVO.isPresent()) {
            ProductVO productVO = optionalProductVO.get();
            productTO = new ProductTO() {{
                setId(productVO.getId());
                setName(productVO.getName());
                setType(productVO.getType());
                setPrice(productVO.getPrice());
                setDescription(productVO.getDescription());
            }};
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.NOT_FOUND);
        }
        return productTO;
    }

    public ProductTO findByName(String name) throws ProductException {
        ProductTO productTO = null;
        Iterable<ProductVO> productVOs = productDAO.findAll();
        Stream<ProductTO> productTOStream = StreamSupport.stream(productVOs.spliterator(), true)
                .filter(it -> it.getName().equalsIgnoreCase(name))
                .map(it -> new ProductTO() {{
                    setId(it.getId());
                    setName(it.getName());
                    setType(it.getType());
                    setPrice(it.getPrice());
                    setDescription(it.getDescription());
                }});
        Optional<ProductTO> first = productTOStream.findFirst();
        if (first.isPresent()) {
            productTO = first.get();
        } else {
            throw ExceptionUtils.createException(ExceptionEnum.NOT_FOUND);
        }

        return productTO;
    }

    public List<ProductTO> getAll(String orderBy, String order) {
        List<ProductTO> productTOs = new ArrayList<>();
        Iterable<ProductVO> productVOs = productDAO.findAll();

        Comparator<ProductTO> comparator;
        switch (orderBy) {
            case "name":
                comparator = Comparator.comparing(ProductTO::getName);
                break;
            case "description":
                comparator = Comparator.comparing(ProductTO::getDescription);
                break;
            case "type":
                comparator = Comparator.comparing(ProductTO::getType);
                break;
            case "price":
                comparator = (product, otherProduct) -> {
                    BigDecimal price1 = new BigDecimal(product.getPrice());
                    BigDecimal price2 = new BigDecimal(otherProduct.getPrice());
                    return price1.compareTo(price2);
                };
                break;
            default:
                comparator = Comparator.comparing(ProductTO::getId);
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        StreamSupport
                .stream(productVOs.spliterator(), true)
                .map(it -> new ProductTO() {{
                    setId(it.getId());
                    setName(it.getName());
                    setType(it.getType());
                    setPrice(it.getPrice());
                    setDescription(it.getDescription());
                }})
                .sorted(comparator)
                .forEachOrdered(productTOs::add);

        return productTOs;
    }

    public void update(String id, CreateProductDTO createProductDTO) throws ProductException {
        productValidator.validate(createProductDTO);

        productDAO.save(
                new ProductVO(
                        id,
                        createProductDTO.getName(),
                        createProductDTO.getType(),
                        createProductDTO.getPrice(),
                        createProductDTO.getDescription())
        );
    }

    public void delete(String id) {
        productDAO.deleteById(id);
    }
}
