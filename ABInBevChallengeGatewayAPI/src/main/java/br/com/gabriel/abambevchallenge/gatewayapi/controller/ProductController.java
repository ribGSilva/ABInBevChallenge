package br.com.gabriel.abambevchallenge.gatewayapi.controller;

import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.CreateProductDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.ErrorDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.dto.IdDTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.ClientCredentialsTO;
import br.com.gabriel.abambevchallenge.gatewayapi.beans.to.ProductTO;
import br.com.gabriel.abambevchallenge.gatewayapi.exceptions.GatewayApiException;
import br.com.gabriel.abambevchallenge.gatewayapi.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@Api(value = "Product Controller")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @ApiOperation(value = "Request all products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request", response = ProductTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Error request", response = ErrorDTO.class)
    })
    public ResponseEntity<?> getAll(@ApiParam(value = "Field name to order") @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                    @ApiParam(value = "asc/desc") @RequestParam(value = "order", defaultValue = "desc") String order) {
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
    @ApiOperation(value = "Request one product searching by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request", response = ProductTO.class),
            @ApiResponse(code = 400, message = "Error request", response = ErrorDTO.class)
    })
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        ResponseEntity<?> responseEntity;
        try {
            ProductTO product = productService.findId(id);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (GatewayApiException e) {
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
    @ApiOperation(value = "Search one product searching by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request", response = ProductTO.class),
            @ApiResponse(code = 400, message = "Error request", response = ErrorDTO.class)
    })
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        ResponseEntity<?> responseEntity;
        try {
            ProductTO product = productService.findByName(name);
            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        } catch (GatewayApiException e) {
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

    @PostMapping
    @ApiOperation(value = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request", response = IdDTO.class),
            @ApiResponse(code = 400, message = "Error request", response = ErrorDTO.class)
    })
    public ResponseEntity<?> create(@RequestBody CreateProductDTO createProductDTO) {
        ResponseEntity<?> responseEntity;
        try {
            IdDTO idDTO = productService.create(createProductDTO);
            responseEntity = new ResponseEntity<>(idDTO, HttpStatus.OK);
        } catch (GatewayApiException e) {
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
    @ApiOperation(value = "Update a product by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request"),
            @ApiResponse(code = 400, message = "Error request", response = ErrorDTO.class)
    })
    public ResponseEntity<?> update(@PathVariable("id") String id, @Validated @RequestBody CreateProductDTO createProductDTO) {
        ResponseEntity<?> responseEntity;
        try {
            productService.update(id, createProductDTO);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (GatewayApiException e) {
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
    @ApiOperation(value = "Remove a product by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request"),
            @ApiResponse(code = 400, message = "Error request", response = ErrorDTO.class)
    })
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
