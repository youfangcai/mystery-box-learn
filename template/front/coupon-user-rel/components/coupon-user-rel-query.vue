<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {CouponUserRelSpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: CouponUserRelSpec]; reset: [] }>()
const query = defineModel<CouponUserRelSpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="获取途径类型">
        <dict-select v-model="query.receiveType" :dict-id="DictConstants.COUPON_RECEIVE_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="优惠劵">
        <remote-select v-model="query.couponId" :query-options="couponQueryOptions" label-prop="name"></remote-select>
      </el-form-item>
      <el-form-item label="优惠券状态">
        <dict-select v-model="query.status" :dict-id="DictConstants.COUPON_STATUS"></dict-select>
      </el-form-item>
      <el-form-item label="用户信息">
        <remote-select v-model="query.userId" :query-options="userQueryOptions" label-prop="name"></remote-select>
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

