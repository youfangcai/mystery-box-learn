<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {CouponBoxRelSpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: CouponBoxRelSpec]; reset: [] }>()
const query = defineModel<CouponBoxRelSpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="盲盒">
        <remote-select v-model="query.boxId" :query-options="mysteryBoxQueryOptions" label-prop="name"></remote-select>
      </el-form-item>
      <el-form-item label="优惠券">
        <remote-select v-model="query.couponId" :query-options="couponQueryOptions" label-prop="name"></remote-select>
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

