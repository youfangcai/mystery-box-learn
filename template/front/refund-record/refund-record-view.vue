<script lang="ts" setup>
import RefundRecordTable from './components/refund-record-table.vue'
import RefundRecordQuery from './components/refund-record-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {RefundRecordSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: RefundRecordSpec = {}
const tableHelper = useTableHelper(
    api.refundRecordForAdminController.query,
    api.refundRecordForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<RefundRecordSpec>(initQuery)
provide('refundRecordTableHelper', tableHelper)
</script>
<template>
  <div class="refund-record-view">
    <refund-record-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></refund-record-query>
    <refund-record-table></refund-record-table>
  </div>
</template>

<style lang="scss" scoped>
.refund-record-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
