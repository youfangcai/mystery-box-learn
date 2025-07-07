<script lang="ts" setup>
import RemoteSelect from '@/components/base/form/remote-select.vue'
import type {MysteryBoxCategorySpec} from '@/apis/__generated/model/static'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const emit = defineEmits<{ search: [value: MysteryBoxCategorySpec]; reset: [] }>()
const query = defineModel<MysteryBoxCategorySpec>('query', {required: true})
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
      <el-form-item label="类别名称">
        <el-input v-model.trim="query.name"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model.trim="query.description"></el-input>
      </el-form-item>
      <el-form-item label="排序号">
        <el-input-number v-model="query.sortOrder" controls-position="right"></el-input-number>
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

