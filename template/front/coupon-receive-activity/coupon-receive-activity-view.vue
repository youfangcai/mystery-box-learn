<script lang="ts" setup>
import CouponReceiveActivityTable from './components/coupon-receive-activity-table.vue'
import CouponReceiveActivityQuery from './components/coupon-receive-activity-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {CouponReceiveActivitySpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: CouponReceiveActivitySpec = {}
const tableHelper = useTableHelper(
    api.couponReceiveActivityForAdminController.query,
    api.couponReceiveActivityForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<CouponReceiveActivitySpec>(initQuery)
provide('couponReceiveActivityTableHelper', tableHelper)
</script>
<template>
  <div class="coupon-receive-activity-view">
    <coupon-receive-activity-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></coupon-receive-activity-query>
    <coupon-receive-activity-table></coupon-receive-activity-table>
  </div>
</template>

<style lang="scss" scoped>
.coupon-receive-activity-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
