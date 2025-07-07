<script lang="ts" setup>
import {onActivated, reactive, ref} from 'vue'
import {ElMessage, type FormInstance, type FormRules} from 'element-plus'
import type {CouponInput} from '@/apis/__generated/model/static'
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
const initForm: CouponInput = {}
const {formData: form, restForm} = useFormHelper<CouponInput>(initForm)
const rules = reactive<FormRules<CouponInput>>({
  "name": [{required: true, message: '请输入优惠券名称', trigger: "blur"}],
  "thresholdAmount": [{required: true, message: '请输入使用门槛', trigger: "blur"}],
  "releasedQuantity": [{required: true, message: '请输入发放数量', trigger: "blur"}],
  "effectiveDate": [{required: true, message: '请输入生效日期', trigger: "blur"}],
  "expirationDate": [{required: true, message: '请输入过期时间', trigger: "blur"}],
  "couponType": [{required: true, message: '请输入优惠类型', trigger: "change"}],
  "scopeType": [{required: true, message: '请输入使用范围', trigger: "change"}],
  "amount": [{required: true, message: '请输入优惠金额', trigger: "blur"}],
  "discount": [{required: true, message: '请输入折扣', trigger: "blur"}],
  "status": [{required: true, message: '请输入是否启用', trigger: "blur"}],
})
const handleConfirm = () => {
  formRef.value?.validate(
      assertFormValidate(() =>
          api.couponForAdminController.save({body: form.value}).then(async (res) => {
            form.value.id = res
            ElMessage.success('操作成功')
          })
      )
  )
}
onActivated(() => {
  if (props.id) {
    api.couponForAdminController.findById({id: props.id}).then((res) => {
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
      <el-form-item label="优惠券名称" prop="name">
        <el-input v-model.trim="form.name"></el-input>
      </el-form-item>
      <el-form-item label="使用门槛" prop="thresholdAmount">
        <el-input-number v-model="form.thresholdAmount"></el-input-number>
      </el-form-item>
      <el-form-item label="发放数量" prop="releasedQuantity">
        <el-input-number v-model="form.releasedQuantity"></el-input-number>
      </el-form-item>
      <el-form-item label="生效日期" prop="effectiveDate">
        <el-date-picker
            v-model="form.effectiveDate"
            placeholder="请选择日期"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="过期时间" prop="expirationDate">
        <el-date-picker
            v-model="form.expirationDate"
            placeholder="请选择日期"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="优惠类型" prop="couponType">
        <dict-select v-model="form.couponType" :dict-id="DictConstants.COUPON_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="使用范围" prop="scopeType">
        <dict-select v-model="form.scopeType" :dict-id="DictConstants.COUPON_SCOPE_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="优惠金额" prop="amount">
        <el-input-number v-model="form.amount"></el-input-number>
      </el-form-item>
      <el-form-item label="折扣" prop="discount">
        <el-input-number v-model="form.discount"></el-input-number>
      </el-form-item>
      <el-form-item label="是否启用" prop="status">
        <el-switch v-model="form.status"></el-switch>
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
