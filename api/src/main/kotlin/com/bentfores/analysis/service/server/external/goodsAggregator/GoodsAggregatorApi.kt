package com.bentfores.analysis.service.server.external.goodsAggregator

import com.bentfores.analysis.service.server.external.goodsAggregator.model.Products
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface GoodsAggregatorApi {
  @PostMapping("/aggregator/suppliers")
  fun getSuppliersInfo(
    @RequestBody products: List<Products>
  )

  @PostMapping("/aggregator/message")
  fun postSendMessage(
    @RequestParam(name = "productUrl", required = true) productUrl: String
  )
}