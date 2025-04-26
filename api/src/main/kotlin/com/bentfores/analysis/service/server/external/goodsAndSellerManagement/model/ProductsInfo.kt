package com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductsInfo(
  @JsonProperty("article", required = true) val article: String,
  @JsonProperty("name", required = true) val name: String,
  @JsonProperty("imageUrl", required = true) val imageUrl: String,
  @JsonProperty("profitPlace") val profitPlace: String,
)
