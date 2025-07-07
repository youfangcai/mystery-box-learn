<script lang="ts" setup>
import MysteryBoxProductRelTable from './components/mystery-box-product-rel-table.vue'
import MysteryBoxProductRelQuery from './components/mystery-box-product-rel-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {MysteryBoxProductRelSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: MysteryBoxProductRelSpec = {}
const tableHelper = useTableHelper(
    api.mysteryBoxProductRelForAdminController.query,
    api.mysteryBoxProductRelForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<MysteryBoxProductRelSpec>(initQuery)
provide('mysteryBoxProductRelTableHelper', tableHelper)
</script>
<template>
  <div class="mystery-box-product-rel-view">
    <mystery-box-product-rel-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></mystery-box-product-rel-query>
    <mystery-box-product-rel-table></mystery-box-product-rel-table>
  </div>
</template>

<style lang="scss" scoped>
.mystery-box-product-rel-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
