package com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class ManagementSuppliersInfo(
  @JsonProperty("supplierId", required = true) val supplierId: UUID,
  @JsonProperty("status", required = true) val status: SupplierStatus,
  @JsonProperty("name") val name: String?,
  @JsonProperty("comment") val comment: String?
) {
  enum class SupplierStatus {
    NOT_COOPERATING,
    BLACKLISTED,
    MESSAGE_SENT,
    COOPERATING,
    WRONG
  }
}