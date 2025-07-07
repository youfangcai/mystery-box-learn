package org.example.mysteryboxlearn.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mysteryboxlearn.common.domain.vo.response.ApiResult;
import org.example.mysteryboxlearn.common.domain.vo.response.PageResult;
import org.example.mysteryboxlearn.common.exception.ResultCode;
import org.example.mysteryboxlearn.product.domain.entity.Product;
import org.example.mysteryboxlearn.product.domain.vo.ProductInfoReq;
import org.example.mysteryboxlearn.product.domain.vo.ProductInfoResp;
import org.example.mysteryboxlearn.product.service.ProductService;
import org.example.mysteryboxlearn.product.service.adapter.ProductAdapter;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productInfo")
@Slf4j
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/find_by_id")
    public ApiResult<ProductInfoResp> getProductById(@RequestParam String id) {
        Product product = productService.getById(id);
        ProductInfoResp resp = ProductAdapter.INSTANCE.convertToResp(product);
        if (resp == null) {
            return ApiResult.success(resp);
        }
        return ApiResult.fail(ResultCode.NotFindError);
    }

    @GetMapping("/find_all")
    public ApiResult<PageResult<ProductInfoResp>> getProducts(@RequestParam int current, @RequestParam int size) {
        // 方法一(简单查找直接通过service层来完成查找和转换vo)
        IPage<Product> page = new Page<>(current, size);
        IPage<Product> productIPage = productService.lambdaQuery().page(page); // 直接通过Service查找
        IPage<ProductInfoResp> respIPage = productIPage.convert(ProductAdapter.INSTANCE::convertToResp); // 转换为vo
        // 方法二（进行分层，dao来查找， service层来实现转换为vo)
        return ApiResult.success(new PageResult<ProductInfoResp>().setContent(respIPage.getRecords()));
    }
    @PostMapping("/save")
    public ApiResult<Boolean> saveProduct(@RequestBody ProductInfoReq req) {
        Product product = ProductAdapter.INSTANCE.convertToProduct(req);
        if (productService.save(product) ){
            return ApiResult.success();
        }
        return ApiResult.fail(ResultCode.SaveError);
    }
    @PutMapping("/update")
    public ApiResult<Boolean> updateProduct(@RequestParam String id, @RequestBody ProductInfoReq req) {
        Product product = ProductAdapter.INSTANCE.convertToProduct(req);
        product.setId(id);
        if (productService.updateById(product) ){
            return ApiResult.success();
        }
        return ApiResult.fail(ResultCode.UpdateError);
    }
}
