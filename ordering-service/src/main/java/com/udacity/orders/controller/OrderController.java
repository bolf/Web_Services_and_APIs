package com.udacity.orders.controller;

import com.udacity.orders.entity.Order;
import com.udacity.orders.service.OrderResourceAssembler;
import com.udacity.orders.service.OrderService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderResourceAssembler assembler;

    public OrderController(OrderService orderService, OrderResourceAssembler assembler) {
        this.orderService = orderService;
        this.assembler = assembler;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> post(@Valid @RequestBody Order order) throws URISyntaxException {
        Order savedOrder = orderService.createOrder(order);
        Resource<Order> resource = assembler.toResource(savedOrder);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Resource<Order> get(@PathVariable Long id){
        return assembler.toResource(orderService.getByID(id));
    }

    @GetMapping(produces = "application/json")
    public Resources<Resource<Order>> list() {
        List<Resource<Order>> resources = orderService.getAll().stream().map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(OrderController.class).list()).withSelfRel());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
