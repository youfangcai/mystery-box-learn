<script lang="ts" setup>
import {onActivated, reactive, ref} from 'vue'
import {ElMessage, type FormInstance, type FormRules} from 'element-plus'
import type {CouponCategoryRelInput} from '@/apis/__generated/model/static'
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
const initForm: CouponCategoryRelInput = {}
const {formData: form, restForm} = useFormHelper<CouponCategoryRelInput>(initForm)
const rules = reactive<FormRules<CouponCategoryRelInput>>({
  "categoryId": [{required: true, message: '请输入商品分类', trigger: "blur"}],
  "couponId": [{required: true, message: '请输入优惠券', trigger: "blur"}],
})
const handleConfirm = () => {
  formRef.value?.validate(
      assertFormValidate(() =>
          api.couponCategoryRelForAdminController.save({body: form.value}).then(async (res) => {
            form.value.id = res
            ElMessage.success('操作成功')
          })
      )
  )
}
onActivated(() => {
  if (props.id) {
    api.couponCategoryRelForAdminController.findById({id: props.id}).then((res) => {
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
      <el-form-item label="商品分类" prop="categoryId">
        <remote-select v-model="form.categoryId" :query-options="mysteryBoxCategoryQueryOptions"
                       label-prop="name"></remote-select>
      </el-form-item>
      <el-form-item label="优惠券" prop="couponId">
        <remote-select v-model="form.couponId" :query-options="couponQueryOptions" label-prop="name"></remote-select>
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
