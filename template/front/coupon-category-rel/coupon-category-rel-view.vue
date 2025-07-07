<script lang="ts" setup>
import CouponCategoryRelTable from './components/coupon-category-rel-table.vue'
import CouponCategoryRelQuery from './components/coupon-category-rel-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {CouponCategoryRelSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: CouponCategoryRelSpec = {}
const tableHelper = useTableHelper(
    api.couponCategoryRelForAdminController.query,
    api.couponCategoryRelForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<CouponCategoryRelSpec>(initQuery)
provide('couponCategoryRelTableHelper', tableHelper)
</script>
<template>
  <div class="coupon-category-rel-view">
    <coupon-category-rel-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></coupon-category-rel-query>
    <coupon-category-rel-table></coupon-category-rel-table>
  </div>
</template>

<style lang="scss" scoped>
.coupon-category-rel-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
