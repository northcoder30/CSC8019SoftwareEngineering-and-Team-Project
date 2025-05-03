<template>
  <div class="sidebar">
    <div class="top-icons">
      <router-link to="/home" class="icon">🏠</router-link>
      <router-link to="/category" class="icon">☰</router-link>
      <router-link to="/wishlist" class="icon">🛒</router-link>
    </div>

    <div class="bottom-icons">
      <hr class="divider" />
      <router-link to="/profile" class="icon">👤</router-link>
      <router-link to="/settings" class="icon">⚙</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "SideBar"
};
</script>

<style>
.sidebar {
  width: 60px;
  background-color: #111;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 16px 0;
}

.top-icons,
.bottom-icons {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.icon {
  font-size: 20px;
  color: white;
  margin: 16px 0;
  text-decoration: none;
  transition: transform 0.2s, color 0.2s;
}

.icon:hover {
  transform: scale(1.1);
  color: #ff9900;
}

.divider {
  width: 60%;
  border-top: 1px solid #555;
  margin: 20px 0;
}

</style>