package br.com.gabriel.abambevchallenge.product.controller;

import br.com.gabriel.abambevchallenge.product.beans.dto.CreateProductDTO;
import br.com.gabriel.abambevchallenge.product.beans.dto.ErrorDTO;
import br.com.gabriel.abambevchallenge.product.beans.dto.IdDTO;
import br.com.gabriel.abambevchallenge.product.beans.to.ProductTO;
import br.com.gabriel.abambevchallenge.product.exceptions.ProductException;
import br.com.gabriel.abambevchallenge.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                    @RequestParam(value = "order", defaultValue = "desc") String order) {
        ResponseEntity<?> responseEntity;
        try {
            List<ProductTO> products = productService.getAll(orderBy, order);
            responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        ResponseEntity<?> responseEntity;
        try {
            ProductTO product = productService.findId(id);
            responseEntity = new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
        } catch (ProductException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("byName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        ResponseEntity<?> responseEntity;
        try {
            ProductTO product = productService.findByName(name);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<?> create(@Validated @RequestBody CreateProductDTO createProductDTO) {
        ResponseEntity<?> responseEntity;
        try {
            String id = productService.create(createProductDTO);
            IdDTO idDTO = new IdDTO() {{
                setId(id);
            }};
            responseEntity = new ResponseEntity<>(idDTO, HttpStatus.CREATED);
        } catch (ProductException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Validated @RequestBody CreateProductDTO createProductDTO) {
        ResponseEntity<?> responseEntity;
        try {
            productService.update(id, createProductDTO);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductException e) {
            ErrorDTO errorDTO = new ErrorDTO() {{
                setCode(e.getCode());
                setMessage(e.getMessage());
            }};
            responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        ResponseEntity<?> responseEntity;
        try {
            productService.delete(id);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
