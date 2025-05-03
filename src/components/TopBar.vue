<template>
  <div class="top-bar">
    <h1 class="system-title">
      <span class="logo-icon">📘</span> {{title.toUpperCase()}}
    </h1>
  </div>
</template>

<script>
export default {
  name: "TopBar",
  props: {
    title: {
      type: String,
      default: 'E-BOOK SYSTEM'
    }
  }
};
</script>

<style>
.top-bar {
  background: linear-gradient(to right, #FF6F00, #FF9000);
  color: white;
  padding: 8px 24px;
  height: 48px;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 6px rgba(0,0,0,0.2);
}

.system-title {
  font-family: 'Poppins', sans-serif;
  font-size: 20px;
  font-weight: bold;
  letter-spacing: 1px;
  display: flex;
  align-items: center;
  gap: 10px;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.2);
}

.logo-icon {
  font-size: 22px;
}
</style>