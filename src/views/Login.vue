<template>
  <div class="login-page">
    <div class="login-header">E-BOOK SYSTEM</div>
    <div class="login-box">
      <InputBox type="email" placeholder="Enter email" v-model="email" />
      <InputBox type="password" placeholder="Enter password" v-model="password"/>
      <button @click="handleLogin">LOGIN</button>
      <div class="register-link">
        <router-link to="/register">register here</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import InputBox from "@/components/InputBox.vue"
import { setUser } from '@/utils/auth';

export default {
  name: 'Login',
  components: {
    InputBox: InputBox
  },
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    handleLogin() {
      console.log(`email=${this.email}, password=${this.password}`);
      const users = JSON.parse(localStorage.getItem('users') || '{}');
      const user = users[this.email];
      if (!user) {
        alert('Email not registered');
        return;
      }
      if (user.password !== this.password) {
        alert('Incorrect password');
        return;
      }
      setUser({ email: this.email, username: user.username });
      this.$router.push('/home');
    }
  }
};
</script>

<style scoped>
.login-page {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, #000 50%, #fff 50%);
}

.login-header {
  color: white;
  font-size: 28px;
  font-weight: bold;
  margin: 60px 0 20px 0;
}

.login-box {
  background: white;
  width: 300px;
  padding: 30px 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.15);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.login-box button {
  width: 100%;
  background: black;
  color: white;
  border: none;
  padding: 10px;
  margin-top: 15px;
  border-radius: 20px;
  font-weight: bold;
  cursor: pointer;
}

.register-link {
  margin-top: 20px;
  font-size: 14px;
  color: #333;
}

.register-link a {
  color: #000;
  text-decoration: underline;
  cursor: pointer;
}
</style>
