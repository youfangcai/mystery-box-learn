<script lang="ts" setup>
import MenuTable from './components/menu-table.vue'
import MenuQuery from './components/menu-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {MenuSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: MenuSpec = {}
const tableHelper = useTableHelper(
    api.menuForAdminController.query,
    api.menuForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<MenuSpec>(initQuery)
provide('menuTableHelper', tableHelper)
</script>
<template>
  <div class="menu-view">
    <menu-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></menu-query>
    <menu-table></menu-table>
  </div>
</template>

<style lang="scss" scoped>
.menu-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
