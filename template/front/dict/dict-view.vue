<script lang="ts" setup>
import DictTable from './components/dict-table.vue'
import DictQuery from './components/dict-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {DictSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: DictSpec = {}
const tableHelper = useTableHelper(
    api.dictForAdminController.query,
    api.dictForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<DictSpec>(initQuery)
provide('dictTableHelper', tableHelper)
</script>
<template>
  <div class="dict-view">
    <dict-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></dict-query>
    <dict-table></dict-table>
  </div>
</template>

<style lang="scss" scoped>
.dict-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
