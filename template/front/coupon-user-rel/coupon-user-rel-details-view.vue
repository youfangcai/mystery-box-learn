<script lang="ts" setup>
import {onActivated, reactive, ref} from 'vue'
import {ElMessage, type FormInstance, type FormRules} from 'element-plus'
import type {CouponUserRelInput} from '@/apis/__generated/model/static'
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
const initForm: CouponUserRelInput = {}
const {formData: form, restForm} = useFormHelper<CouponUserRelInput>(initForm)
const rules = reactive<FormRules<CouponUserRelInput>>({
  "receiveType": [{required: true, message: '请输入获取途径类型', trigger: "change"}],
  "couponId": [{required: true, message: '请输入优惠劵', trigger: "blur"}],
  "status": [{required: true, message: '请输入优惠券状态', trigger: "change"}],
  "userId": [{required: true, message: '请输入用户信息', trigger: "blur"}],
})
const handleConfirm = () => {
  formRef.value?.validate(
      assertFormValidate(() =>
          api.couponUserRelForAdminController.save({body: form.value}).then(async (res) => {
            form.value.id = res
            ElMessage.success('操作成功')
          })
      )
  )
}
onActivated(() => {
  if (props.id) {
    api.couponUserRelForAdminController.findById({id: props.id}).then((res) => {
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
      <el-form-item label="获取途径类型" prop="receiveType">
        <dict-select v-model="form.receiveType" :dict-id="DictConstants.COUPON_RECEIVE_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="优惠劵" prop="couponId">
        <remote-select v-model="form.couponId" :query-options="couponQueryOptions" label-prop="name"></remote-select>
      </el-form-item>
      <el-form-item label="优惠券状态" prop="status">
        <dict-select v-model="form.status" :dict-id="DictConstants.COUPON_STATUS"></dict-select>
      </el-form-item>
      <el-form-item label="用户信息" prop="userId">
        <remote-select v-model="form.userId" :query-options="userQueryOptions" label-prop="name"></remote-select>
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
