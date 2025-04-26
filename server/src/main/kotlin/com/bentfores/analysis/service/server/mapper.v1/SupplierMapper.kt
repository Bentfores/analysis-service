package com.bentfores.analysis.service.server.mapper.v1

import com.bentfores.analysis.service.api.model.ParsedSupplier
import com.bentfores.analysis.service.api.model.SupplierStatusEnum
import com.bentfores.analysis.service.server.data.entity.Supplier
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model.ManagementSuppliersInfo
import com.bentfores.analysis.service.server.external.goodsAndSellerManagement.model.NewSupplier
import org.springframework.stereotype.Component
import com.bentfores.analysis.service.api.model.SuppliersInfo as ApiSuppliersInfo

@Component
class SupplierMapper {

  fun mapToSuppliersInformationList(
    suppliers: List<Supplier>,
    suppliersInfo: List<ManagementSuppliersInfo>
  ): List<ApiSuppliersInfo> {
    val infoById = suppliersInfo.associateBy { it.name }

    return suppliers.map { supplier ->
      val info = infoById[supplier.supplierName]
      ApiSuppliersInfo(
        supplierId = supplier.supplierId!!,
        name = supplier.supplierName,
        price = supplier.price,
        minOrder = supplier.quantity,
        rating = supplier.rating,
        years = supplier.years,
        supplierImageUrl = supplier.supplierImageUrl,
        supplierProductUrl = supplier.supplierProductUrl,
        status = mapManagementSupplierStatusToSupplierStatus(info!!.status),
      )
    }
  }

  fun mapToNewSupplierInfoList(suppliers: List<ParsedSupplier>) =
    suppliers.map { mapToNewSupplierInfo(it) }

  fun mapToNewSupplierInfo(supplier: ParsedSupplier) = NewSupplier(
    name = supplier.supplierName,
    url = supplier.supplierUrl,
  )

  fun mapParsedSupplierToSupplierList(infoList: List<ParsedSupplier>): List<Supplier> {
    return infoList.map { mapParsedSupplierToSupplier(it) }
  }

  fun mapParsedSupplierToSupplier(supplier: ParsedSupplier) = Supplier(
    article = supplier.article,
    profitPlace = supplier.profitPlace,
    supplierName = supplier.supplierName,
    productUrl = supplier.supplierProductUrl,
    price = supplier.price,
    quantity = supplier.minOrder,
    rating = supplier.rating,
    years = supplier.years,
    imageUrl = supplier.imageUrl,
    supplierImageUrl = supplier.supplierImageUrl,
    supplierProductUrl = supplier.supplierProductUrl
  )

  fun mapManagementSupplierStatusToSupplierStatus(supplierStatus: ManagementSuppliersInfo.SupplierStatus) =
    when (supplierStatus) {
      ManagementSuppliersInfo.SupplierStatus.NOT_COOPERATING -> SupplierStatusEnum.NOT_COOPERATING
      ManagementSuppliersInfo.SupplierStatus.BLACKLISTED -> SupplierStatusEnum.BLACKLISTED
      ManagementSuppliersInfo.SupplierStatus.MESSAGE_SENT -> SupplierStatusEnum.MESSAGE_SENT
      ManagementSuppliersInfo.SupplierStatus.COOPERATING -> SupplierStatusEnum.COOPERATING
      ManagementSuppliersInfo.SupplierStatus.WRONG -> SupplierStatusEnum.WRONG
    }
}