<script lang="ts" setup>
import UserTable from './components/user-table.vue'
import UserQuery from './components/user-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {UserSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: UserSpec = {}
const tableHelper = useTableHelper(
    api.userForAdminController.query,
    api.userForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<UserSpec>(initQuery)
provide('userTableHelper', tableHelper)
</script>
<template>
  <div class="user-view">
    <user-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></user-query>
    <user-table></user-table>
  </div>
</template>

<style lang="scss" scoped>
.user-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
