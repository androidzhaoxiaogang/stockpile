package com.capitalone.nsb.marketing.stockpile.controller;

import com.capitalone.nsb.marketing.stockpile.api.Response;
import com.capitalone.nsb.marketing.stockpile.domain.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Stockpile", tags = "Endpoint", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryController {

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "Finds this item by Id.", notes = "simple lookup")
    public Response<Item> getItem(@PathVariable Integer id) {
        Item item = new Item();
        item.setId(id);
        item.setSKU(123);
        item.setDescription("test product");
        return new Response<>(item);
    }
}
