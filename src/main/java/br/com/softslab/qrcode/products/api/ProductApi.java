package br.com.softslab.qrcode.products.api;


import br.com.softslab.qrcode.products.dto.ProductDTO;
import br.com.softslab.qrcode.products.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductApi {
    @Autowired
    private ProductFacade productFacade;

    @GetMapping
    @ResponseBody
    public List<ProductDTO> index()
    {
        return productFacade.index();
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public List<ProductDTO> show(@PathVariable("productId") String productId)
    {
        return productFacade.show(productId);
    }

    @PostMapping
    @ResponseBody
    public ProductDTO store(@RequestBody ProductDTO productDTO)
    {
        return productFacade.store(productDTO);
    }

    @PutMapping("/{productId}")
    @ResponseBody
    public ProductDTO update(@PathVariable("productId") String productId, @RequestBody ProductDTO productDTO)
    {
        return productFacade.update(productId, productDTO);
    }

    @DeleteMapping("/{productId}")
    @ResponseBody
    public String delete(@PathVariable("productId") String productId)
    {
        return productFacade.destroy(productId);
    }
}
