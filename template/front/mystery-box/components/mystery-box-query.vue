<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {MysteryBoxSpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: MysteryBoxSpec]; reset: [] }>()
const query = defineModel<MysteryBoxSpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="盲盒名字">
        <dict-select v-model="query.name" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="盲盒详情">
        <dict-select v-model="query.details" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="购买提示">
        <dict-select v-model="query.tips" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="价格">
        <dict-select v-model="query.price" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="封面">
        <dict-select v-model="query.cover" :dict-id="DictConstants."></dict-select>
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

