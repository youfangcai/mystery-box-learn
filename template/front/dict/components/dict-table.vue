<script lang="ts" setup>
import {inject, onMounted} from 'vue'
import {assertSuccess} from '@/utils/common'
import {api} from '@/utils/api-instance'
import {ElMessageBox} from 'element-plus'
import type {Scope} from '@/typings'
import type {DictDto} from '@/apis/__generated/model/dto'
import {Delete, Edit, Plus} from '@element-plus/icons-vue'
import {useTableHelper} from '@/components/base/table/table-helper'
import {useTagStore} from '@/layout/store/tag-store'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'

const tagStore = useTagStore()
type DictScope = Scope<DictDto['DictRepository/COMPLEX_FETCHER_FOR_ADMIN']>
const dictTableHelper = inject(
    'dictTableHelper',
    useTableHelper(api.dictForAdminController.query, api.dictForAdminController, {})
)
const {
  loadTableData,
  handleSortChange,
  handleSelectChange,
  getTableSelectedRows,
  reloadTableData,
  pageData,
  loading,
  queryRequest,
  table
} = dictTableHelper
onMounted(() => {
  reloadTableData()
})
const handleEdit = (row: { id: string }) => {
  tagStore.openTag({
    path: '/dict-details',
    query: {
      id: row.id
    }
  })
}
const handleCreate = () => {
  tagStore.openTag({
    path: '/dict-details'
  })
}
const handleSingleDelete = (row: { id: string }) => {
  handleDelete([row.id])
}
const handleBatchDelete = () => {
  handleDelete(
      getTableSelectedRows().map((row) => {
        return row.id || ''
      })
  )
}
const handleDelete = (ids: string[]) => {
  ElMessageBox.confirm('此操作将删除数据且无法恢复, 是否继续?', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    api.dictForAdminController.delete({body: ids}).then((res) => {
      assertSuccess(res).then(() => reloadTableData())
    })
  })
}
</script>
<template>
  <div>
    <div class="button-section">
      <el-button size="small" type="success" @click="handleCreate">
        <el-icon>
          <plus/>
        </el-icon>
        新增
      </el-button>
      <el-button size="small" type="danger" @click="handleBatchDelete">
        <el-icon>
          <delete/>
        </el-icon>
        删除
      </el-button>
    </div>
    <el-table
        ref="table"
        v-loading="loading"
        :border="true"
        :data="pageData.content"
        @selection-change="handleSelectChange"
        @sort-change="handleSortChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="创建时间" prop="createdTime" show-overflow-tooltip sortable="custom" width="150">
        <template v-slot:default="{ row }: DictScope">
          {{ row.createdTime }}
        </template>
      </el-table-column>
      <el-table-column label="更新时间" prop="editedTime" show-overflow-tooltip sortable="custom" width="150">
        <template v-slot:default="{ row }: DictScope">
          {{ row.editedTime }}
        </template>
      </el-table-column>
      <el-table-column label="创建人" prop="creator.phone" show-overflow-tooltip sortable="custom" width="150">
        <template v-slot:default="{ row }: DictScope">
          {{ row.creator.nickname }}({{ row.creator.phone }})
        </template>
      </el-table-column>
      <el-table-column label="更新人" prop="editor.phone" show-overflow-tooltip sortable="custom" width="150">
        <template v-slot:default="{ row }: DictScope">
          {{ row.editor.nickname }}({{ row.editor.phone }})
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template v-slot:default="{ row }">
          <div>
            <el-button class="edit-btn" link size="small" type="primary" @click="handleEdit(row)">
              <el-icon>
                <edit/>
              </el-icon>
            </el-button>
            <el-button
                class="delete-btn"
                link
                size="small"
                type="primary"
                @click="handleSingleDelete(row)"
            >
              <el-icon>
                <delete/>
              </el-icon>
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div class="page">
      <el-pagination
          :current-page="queryRequest.pageNum"
          :page-size="queryRequest.pageSize"
          :page-sizes="[10, 20, 30, 40, 50]"
          :total="pageData.totalElements"
          background
          layout="prev, pager, next, jumper, total, sizes"
          small
          style="margin-top: 30px"
          @current-change="(pageNum) => loadTableData({ pageNum })"
          @size-change="(pageSize) => loadTableData({ pageSize })"
      />
    </div>
  </div>
</template>

<style lang="scss" scoped>
.button-section {
  margin: 20px 0;
}

.page {
  display: flex;
  justify-content: flex-end;
}
</style>

