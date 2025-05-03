<template>
  <div class="main-page">
    <TopBar :title="title"/>
    <div class="main-section">
      <SideBar />
      <slot />
    </div>
  </div>

</template>

<script>
import TopBar from "@/components/TopBar.vue";
import SideBar from "@/components/SideBar.vue";

export default {
  name: "MainPage",
  props: {
    title: {
      type: String
    }
  },
  // Top bar and side bar components present on main page
  components: {
    TopBar,
    SideBar
  }
}
</script>

<style>
.main-page {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.main-section {
  display: flex;
  flex: 1;
  overflow: hidden;
}
</style>