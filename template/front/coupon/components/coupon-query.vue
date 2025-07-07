<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {CouponSpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: CouponSpec]; reset: [] }>()
const query = defineModel<CouponSpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="优惠券名称">
        <el-input v-model.trim="query.name"></el-input>
      </el-form-item>
      <el-form-item label="使用门槛">
        <el-input-number v-model="query.thresholdAmount" controls-position="right"></el-input-number>
      </el-form-item>
      <el-form-item label="发放数量">
        <el-input-number v-model="query.releasedQuantity" controls-position="right"></el-input-number>
      </el-form-item>
      <el-form-item label="生效日期">
        <datetime-picker
            v-model:max-date-time="query.maxEffectiveDate"
            v-model:min-date-time="query.minEffectiveDate"
        >
        </datetime-picker>
      </el-form-item>
      <el-form-item label="过期时间">
        <datetime-picker
            v-model:max-date-time="query.maxExpirationDate"
            v-model:min-date-time="query.minExpirationDate"
        >
        </datetime-picker>
      </el-form-item>
      <el-form-item label="优惠类型">
        <dict-select v-model="query.couponType" :dict-id="DictConstants.COUPON_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="使用范围">
        <dict-select v-model="query.scopeType" :dict-id="DictConstants.COUPON_SCOPE_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="优惠金额">
        <el-input-number v-model="query.amount" controls-position="right"></el-input-number>
      </el-form-item>
      <el-form-item label="折扣">
        <el-input-number v-model="query.discount" controls-position="right"></el-input-number>
      </el-form-item>
      <el-form-item label="是否启用">
        <el-switch v-model="query.status"></el-switch>
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

