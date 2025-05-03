<template>
  <div class="register-page">
    <div class="register-header">E-BOOK SYSTEM</div>
    <div class="register-box">
      <InputBox v-model="username" type="text" placeholder="Enter username" />
      <InputBox v-model="email" type="email" placeholder="Enter email address" />
      <InputBox v-model="password" type="password" placeholder="Enter password" />
      <InputBox v-model="confirmPassword" type="password" placeholder="Confirm password" />
      <button @click="register">CONTINUE</button>
    </div>
  </div>
</template>

<script>
import InputBox from "@/components/InputBox.vue";

export default {
  name: 'Register',
  components: {
    InputBox
  },
  data() {
    return {
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    }
  },
  methods: {
    register() {
      if (this.password !== this.confirmPassword) {
        alert('Passwords do not match!')
        return
      }
      if (!this.username || !this.email || !this.password) {
        alert('Please fill all fields')
        return
      }

      const users = JSON.parse(localStorage.getItem('users') || '{}');
      if (users[this.email]) {
        alert('This email is already registered')
        return
      }
      users[this.email] = { username: this.username, password: this.password };
      localStorage.setItem('users', JSON.stringify(users));
      alert('Registration successful! Please login')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.register-page {
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, #000 50%, #fff 50%);
}

.register-header {
  color: white;
  font-size: 28px;
  font-weight: bold;
  margin: 60px 0 20px 0;
}

.register-box {
  background: white;
  width: 300px;
  padding: 30px 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.15);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.register-box button {
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

.login-link {
  margin-top: 20px;
  font-size: 14px;
  color: #333;
}

.login-link a {
  color: #000;
  text-decoration: underline;
  cursor: pointer;
}
</style>
