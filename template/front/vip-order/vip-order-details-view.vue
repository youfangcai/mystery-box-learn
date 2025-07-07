<script lang="ts" setup>
import {onActivated, reactive, ref} from 'vue'
import {ElMessage, type FormInstance, type FormRules} from 'element-plus'
import type {VipOrderInput} from '@/apis/__generated/model/static'
import RemoteSelect from '@/components/base/form/remote-select.vue'
import ImageUpload from '@/components/image/image-upload.vue'
import KeyValueInput from '@/components/key-value/key-value-input.vue'
import ValueInput from '@/components/key-value/value-input.vue'
import DictColumn from '@/components/dict/dict-column.vue'
import {DictConstants} from '@/apis/__generated/model/enums/DictConstants'
import {assertFormValidate} from '@/utils/common'
import {api} from '@/utils/api-instance'
import {useFormHelper} from '@/components/base/form/form-helper'

const props = defineProps<{ id?: string }>()
const formRef = ref<FormInstance>()
const initForm: VipOrderInput = {}
const {formData: form, restForm} = useFormHelper<VipOrderInput>(initForm)
const rules = reactive<FormRules<VipOrderInput>>({
  "user": [{required: true, message: '请输入用户', trigger: "blur"}],
  "vipPackage": [{required: true, message: '请输入vip套餐', trigger: "blur"}],
})
const handleConfirm = () => {
  formRef.value?.validate(
      assertFormValidate(() =>
          api.vipOrderForAdminController.save({body: form.value}).then(async (res) => {
            form.value.id = res
            ElMessage.success('操作成功')
          })
      )
  )
}
onActivated(() => {
  if (props.id) {
    api.vipOrderForAdminController.findById({id: props.id}).then((res) => {
      form.value = res
    })
  } else {
    restForm()
  }
})
</script>

<template>
  <div class="form">
    <el-form ref="formRef" :model="form" :rules="rules" class="form" labelWidth="120">
      <el-form-item label="用户" prop="user">
        <remote-select v-model="form.user" :query-options="userQueryOptions" label-prop="name"></remote-select>
      </el-form-item>
      <el-form-item label="vip套餐" prop="vipPackage">
        <remote-select v-model="form.vipPackage" :query-options="vipPackageQueryOptions"
                       label-prop="name"></remote-select>
      </el-form-item>
    </el-form>
    <el-row justify="center">
      <el-button type="primary" @click="handleConfirm">提交</el-button>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.form {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
