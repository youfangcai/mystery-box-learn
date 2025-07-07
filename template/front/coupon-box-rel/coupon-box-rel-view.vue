<script lang="ts" setup>
import CouponBoxRelTable from './components/coupon-box-rel-table.vue'
import CouponBoxRelQuery from './components/coupon-box-rel-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {CouponBoxRelSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: CouponBoxRelSpec = {}
const tableHelper = useTableHelper(
    api.couponBoxRelForAdminController.query,
    api.couponBoxRelForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<CouponBoxRelSpec>(initQuery)
provide('couponBoxRelTableHelper', tableHelper)
</script>
<template>
  <div class="coupon-box-rel-view">
    <coupon-box-rel-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></coupon-box-rel-query>
    <coupon-box-rel-table></coupon-box-rel-table>
  </div>
</template>

<style lang="scss" scoped>
.coupon-box-rel-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
