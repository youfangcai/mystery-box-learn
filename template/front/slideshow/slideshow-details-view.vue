<script lang="ts" setup>
import {onActivated, reactive, ref} from 'vue'
import {ElMessage, type FormInstance, type FormRules} from 'element-plus'
import type {SlideshowInput} from '@/apis/__generated/model/static'
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
const initForm: SlideshowInput = {}
const {formData: form, restForm} = useFormHelper<SlideshowInput>(initForm)
const rules = reactive<FormRules<SlideshowInput>>({
  "picture": [{required: true, message: '请输入公告图片', trigger: "blur"}],
  "content": [{required: true, message: '请输入公告内容', trigger: "blur"}],
  "sort": [{required: true, message: '请输入排序号', trigger: "blur"}],
  "valid": [{required: true, message: '请输入是否显示', trigger: "blur"}],
})
const handleConfirm = () => {
  formRef.value?.validate(
      assertFormValidate(() =>
          api.slideshowForAdminController.save({body: form.value}).then(async (res) => {
            form.value.id = res
            ElMessage.success('操作成功')
          })
      )
  )
}
onActivated(() => {
  if (props.id) {
    api.slideshowForAdminController.findById({id: props.id}).then((res) => {
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
      <el-form-item label="公告图片" prop="picture">
        <image-upload v-model="form.picture"></image-upload>
      </el-form-item>
      <el-form-item label="公告内容" prop="content">
        <el-input v-model.trim="form.content"></el-input>
      </el-form-item>
      <el-form-item label="跳转ID" prop="navigatorId">
        <el-input v-model.trim="form.navigatorId"></el-input>
      </el-form-item>
      <el-form-item label="跳转类型" prop="navigatorType">
        <dict-select v-model="form.navigatorType" :dict-id="DictConstants.NAVIGATOR_TYPE"></dict-select>
      </el-form-item>
      <el-form-item label="排序号" prop="sort">
        <el-input-number v-model="form.sort"></el-input-number>
      </el-form-item>
      <el-form-item label="是否显示" prop="valid">
        <el-input-number v-model="form.valid"></el-input-number>
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
