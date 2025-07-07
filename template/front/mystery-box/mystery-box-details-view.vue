<script lang="ts" setup>
import {onActivated, reactive, ref} from 'vue'
import {ElMessage, type FormInstance, type FormRules} from 'element-plus'
import type {MysteryBoxInput} from '@/apis/__generated/model/static'
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
const initForm: MysteryBoxInput = {}
const {formData: form, restForm} = useFormHelper<MysteryBoxInput>(initForm)
const rules = reactive<FormRules<MysteryBoxInput>>({
  "name": [{required: true, message: '请输入盲盒名字', trigger: "change"}],
  "details": [{required: true, message: '请输入盲盒详情', trigger: "change"}],
  "tips": [{required: true, message: '请输入购买提示', trigger: "change"}],
  "price": [{required: true, message: '请输入价格', trigger: "change"}],
  "cover": [{required: true, message: '请输入封面', trigger: "change"}],
})
const handleConfirm = () => {
  formRef.value?.validate(
      assertFormValidate(() =>
          api.mysteryBoxForAdminController.save({body: form.value}).then(async (res) => {
            form.value.id = res
            ElMessage.success('操作成功')
          })
      )
  )
}
onActivated(() => {
  if (props.id) {
    api.mysteryBoxForAdminController.findById({id: props.id}).then((res) => {
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
      <el-form-item label="盲盒名字" prop="name">
        <dict-select v-model="form.name" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="盲盒详情" prop="details">
        <dict-select v-model="form.details" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="购买提示" prop="tips">
        <dict-select v-model="form.tips" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <dict-select v-model="form.price" :dict-id="DictConstants."></dict-select>
      </el-form-item>
      <el-form-item label="封面" prop="cover">
        <dict-select v-model="form.cover" :dict-id="DictConstants."></dict-select>
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
