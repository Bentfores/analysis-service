package com.bentfores.analysis.service.server.external.goodsAggregator

import com.bentfores.analysis.service.server.external.goodsAggregator.model.Products
import com.bentfores.analysis.service.server.external.goodsAggregator.model.SuppliersInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

interface GoodsAggregatorApi {
  @GetMapping("/goods-aggregator/suppliersInfo")
  fun getSuppliersInfo(
    @RequestParam(name = "products", required = true) products: List<Products>
  ): List<SuppliersInfo>
}