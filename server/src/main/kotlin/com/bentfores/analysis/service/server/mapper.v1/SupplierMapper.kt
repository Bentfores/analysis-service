package com.bentfores.analysis.service.server.mapper.v1

import com.bentfores.analysis.service.api.model.SuppliersInformation
import com.bentfores.analysis.service.server.data.entity.Supplier
import com.bentfores.analysis.service.server.external.goodsAggregator.model.SuppliersInfo
import org.springframework.stereotype.Component

@Component
class SupplierMapper {

  fun mapToSupplier(info: SuppliersInfo): Supplier {
    return Supplier(
      article = info.article,
      profitPlace = info.profitPlace,
      supplierName = info.name,
      productUrl = info.supplierProductUrl,
      price = info.price,
      quantity = info.quantity,
      rating = info.rating,
      years = info.years,
      imageUrl = info.supplierImageUrl,
      supplierImageUrl =  info.supplierImageUrl,
      supplierProductUrl = info.supplierProductUrl
    )
  }

  fun mapToSupplierList(infoList: List<SuppliersInfo>): List<Supplier> {
    return infoList.map { mapToSupplier(it) }
  }

  fun toSuppliersInformation(supplier: Supplier): SuppliersInformation {
    return SuppliersInformation(
      article = supplier.article,
      name = supplier.supplierName,
      imageUrl = supplier.imageUrl,
      price = supplier.price,
      quantity = supplier.quantity,
      rating = supplier.rating,
      years = supplier.years,
      supplierUrl = supplier.productUrl,
      supplierImageUrl = supplier.supplierImageUrl,
      supplierProductUrl = supplier.supplierProductUrl
    )
  }

  fun mapToSuppliersInformationList(suppliers: List<Supplier>): List<SuppliersInformation> {
    return suppliers.map { toSuppliersInformation(it) }
  }
}