<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {CouponCategoryRelSpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: CouponCategoryRelSpec]; reset: [] }>()
const query = defineModel<CouponCategoryRelSpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="商品分类">
        <remote-select v-model="query.categoryId" :query-options="mysteryBoxCategoryQueryOptions"
                       label-prop="name"></remote-select>
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

