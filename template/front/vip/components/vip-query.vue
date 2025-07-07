<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {VipSpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: VipSpec]; reset: [] }>()
const query = defineModel<VipSpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="过期时间">
        <datetime-picker
            v-model:max-date-time="query.maxEndTime"
            v-model:min-date-time="query.minEndTime"
        >
        </datetime-picker>
      </el-form-item>
      <el-form-item label="用户信息">
        <remote-select v-model="query.user" :query-options="userQueryOptions" label-prop="name"></remote-select>
      </el-form-item>
      <el-form-item label=" ">
        <div class="btn-wrapper">
          <el-button size="small" type="primary" @click="() => emit('search', query)">
            查询
          </el-button>
          <el-button size="small" type="warning" @click="() => emit('reset')"> 重置</el-button>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
:deep(.el-form-item) {
  margin-bottom: 5px;
}

.search {
  display: flex;
  flex-flow: column nowrap;
  width: 100%;

  .btn-wrapper {
    margin-left: 20px;
  }
}
</style>

