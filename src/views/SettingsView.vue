<template>
  <MainPage>
    <div class="settings-content">
      <h2>Settings</h2>

      <section class="settings-section">
        <h3>Update Profile</h3>
        <input v-model="username" type="text" placeholder="Username" />
        <input v-model="email" type="email" placeholder="Email" />
        <input type="file" />
        <button @click="saveProfile">Save</button>
      </section>

      <section class="settings-section">
        <h3>Change Password</h3>
        <input type="password" placeholder="Old Password" />
        <input type="password" placeholder="New Password" />
        <input type="password" placeholder="Confirm Password" />
      </section>

      <section class="settings-section">
        <h3>Language</h3>
        <select>
          <option>English</option>
          <option>中文</option>
        </select>
      </section>

      <section class="settings-section">
        <h3>Theme</h3>
        <label><input type="checkbox" /> Dark Mode</label>
      </section>
    </div>
  </MainPage>
</template>

<script>
import { getUser, setUser } from '@/utils/auth';
import MainPage from "@/components/MainPage.vue";

export default {
  name: 'SettingsView',
  components: {MainPage},
  data() {
    const user = getUser() || { username: '', email: '' };
    return {
      username: user.username,
      email: user.email
    };
  },
  methods: {
    saveProfile() {
      if (!this.username || !this.email) {
        alert('Username and email are required.');
        return;
      }
      setUser({ ...getUser(), username: this.username, email: this.email });
      alert('Profile updated successfully.');
    }
  }
};
</script>

<style scoped>
.settings-content {
  flex: 1;
  padding: 40px;
  overflow-y: auto;
  background: #fff;
}

.settings-content h2 {
  font-size: 24px;
  margin-bottom: 20px;
}

.settings-section {
  margin-bottom: 30px;
}

.settings-section h3 {
  font-size: 18px;
  margin-bottom: 10px;
}

.settings-section input,
.settings-section select {
  display: block;
  width: 300px;
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

.settings-section button {
  padding: 8px 12px;
  border: none;
  background: #FF6F00;
  color: white;
  font-weight: bold;
  border-radius: 6px;
  cursor: pointer;
  margin-top: 5px;
}
</style>
