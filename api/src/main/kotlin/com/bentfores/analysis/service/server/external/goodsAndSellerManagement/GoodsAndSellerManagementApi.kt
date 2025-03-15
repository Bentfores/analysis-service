package com.bentfores.analysis.service.server.external.goodsAndSellerManagement

import com.bentfores.analysis.service.server.external.goodsAggregator.model.Products
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

interface GoodsAndSellerManagementApi {
  @GetMapping("/management/products/{status}")
  fun getProductsInfo(
    @PathVariable("status") status: String = "IGNORED"
  ): List<String>

  @GetMapping("/management/suppliers")
  fun getSuppliersInfo(
    @RequestParam(name = "statuses", required = true) statuses: List<String>,
  ): List<String>
}