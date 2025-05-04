<template>
  <div class="custom-textarea-wrapper">
    <textarea
        class="custom-textarea"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :placeholder="placeholder"
        :rows="rows"
    ></textarea>
  </div>
</template>

<script>
export default {
  name: "TextareaBox",
  props: {
    modelValue: {
      type: String,
      default: '',
      required: false
    },
    placeholder: String,
    rows: {
      type: Number,
      default: 4
    }
  }
};
</script>

<style>
.custom-textarea-wrapper {
  width: 100%;
  display: inherit;
}

.custom-textarea {
  margin: 10px 0;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  background: #eee;
  resize: vertical;
  width: 100%;
}
</style>
