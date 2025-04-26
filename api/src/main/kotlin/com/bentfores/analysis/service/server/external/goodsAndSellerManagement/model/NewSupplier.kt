package com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model

import com.fasterxml.jackson.annotation.JsonProperty

data class NewSupplier(
  @JsonProperty("name", required = true) val name: String,
  @JsonProperty("url", required = true) val url: String
)