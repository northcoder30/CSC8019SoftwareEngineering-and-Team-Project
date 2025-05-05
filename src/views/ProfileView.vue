<template>
  <MainPage title="User Profile">
    <div class="profile-content">
      <div class="user-info">
        <img class="avatar" :src="user?.avatar || require('@/assets/default-avatar.png')" alt="avatar" />
        <div class="user-details">
          <p><strong>Username:</strong> {{ user.username }}</p>
          <p><strong>Email:</strong> {{ user.email }}</p>
        </div>
      </div>

      <hr class="section-divider" />


      <h2 class="section-title">Purchased Books</h2>
      <div class="book-grid">
        <div v-for="book in purchasedBooks" :key="book.id" class="book-card">
          <div class="book-cover"></div>
          <div class="book-title">{{ book.title }}</div>
          <div class="book-actions">
            <button class="btn preview">Preview</button>
            <button class="btn download">Download PDF</button>
            <button class="btn cancel" @click="cancelBook(book)">Cancel</button>
          </div>
        </div>
      </div>
    </div>
  </MainPage>
</template>

<script>
import MainPage from "@/components/MainPage.vue";
import {cancelPurchasedBook, getPurchasedBooks} from '@/utils/purchase';
import { getUser } from '@/utils/auth';

export default {
  name: 'ProfilePage',
  components: {
    MainPage
  },
  data() {
    return {
      user: getUser(),
      purchasedBooks: []
    };
  },
  methods: {
    cancelBook(book){
      cancelPurchasedBook(book);

      // Reset the purchase books by calling the below function in order to get webpage to be rendered with the updated list of purchased books
      this.purchasedBooks = getPurchasedBooks();
      alert(`You have successfully cancelled "${book.title}"`)
    }
  },
  mounted() {
    this.purchasedBooks = getPurchasedBooks();
  }
};
</script>

<style scoped>
.profile-content {
  flex: 1;
  padding: 40px;
  background-color: #fdfdfd;
  overflow-y: auto;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 24px;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 3px solid #FF6F00;
}

.user-details p {
  margin: 6px 0;
  font-size: 16px;
  text-align: left;
}

.section-divider {
  margin: 24px 0;
  border-top: 1px solid #ddd;
}

.section-title {
  margin-bottom: 16px;
  font-size: 20px;
  font-weight: bold;
  color: #333;
}

.book-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 24px;
}

.book-card {
  background: white;
  padding: 16px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  text-align: center;
}

.book-cover {
  width: 100%;
  height: 200px;
  background: #ccc;
  border-radius: 8px;
  margin-bottom: 12px;
}

.book-title {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
}

.book-actions {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.btn {
  padding: 8px 12px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: bold;
}

.btn.preview {
  background-color: #FF6F00;
  color: white;
}

.btn.download {
  background-color: #444;
  color: white;
}

.btn.cancel {
  background-color: red;
  color: white;
}

.btn:hover {
  opacity: 0.9;
}
</style>
