<template>
  <div class="custom-input-wrapper">
    <input
        class="custom-input"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        v-bind:placeholder="placeholder"
        v-bind:type="type">
  </div>
</template>

<script>
export default {
  name: "InputBox",
  props: {
    modelValue: {
      type: String,
      default: '',
      required: true
    },
    placeholder: String,
    type: String
  }
};
</script>

<style>
.custom-input-wrapper{
  width: 100%;
  display: inherit;
}
.custom-input {
  width: 100%;
  margin: 10px 0;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  background: #eee;
}
</style>
