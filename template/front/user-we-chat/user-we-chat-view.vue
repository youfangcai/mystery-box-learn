<script lang="ts" setup>
import UserWeChatTable from './components/user-we-chat-table.vue'
import UserWeChatQuery from './components/user-we-chat-query.vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {api} from '@/utils/api-instance'
import {provide} from 'vue'
import type {UserWeChatSpec} from '@/apis/__generated/model/static'
import {useQueryHelper} from '@/components/base/query/query-helper'

const initQuery: UserWeChatSpec = {}
const tableHelper = useTableHelper(
    api.userWeChatForAdminController.query,
    api.userWeChatForAdminController,
    initQuery
)
const {query, restQuery} = useQueryHelper<UserWeChatSpec>(initQuery)
provide('userWeChatTableHelper', tableHelper)
</script>
<template>
  <div class="user-we-chat-view">
    <user-we-chat-query
        v-model:query="query"
        @reset="restQuery"
        @search="tableHelper.reloadTableData({ query })"
    ></user-we-chat-query>
    <user-we-chat-table></user-we-chat-table>
  </div>
</template>

<style lang="scss" scoped>
.user-we-chat-view {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
