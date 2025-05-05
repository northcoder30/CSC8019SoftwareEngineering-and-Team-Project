<template>
  <MainPage>
    <div class="content">
      <div class="content-header">
        <div class="search-bar">
          <input type="text" v-model="searchQuery" placeholder="Search books..." />
          <button>🔍</button>
        </div>
        <div class="login-status">
          <span v-if="user && user.username">Hi, {{ user.username }}，Welcome back</span>
          <span v-else>
              Please <router-link to="/login">login</router-link>
            </span>
        </div>
      </div>

      <div class="scrollable-book-area">
        <div class="book-grid">
          <div
              v-for="book in paginatedBooks"
              :key="book.id"
              class="book-card"
              @click="goToDetail(book.id)"
              @mouseover="showBookInfo(book)"
              @mouseleave="clearBookInfo"
          >
            <div class="book-cover"></div>
            <div class="book-title">{{ book.title }}</div>
          </div>
        </div>
        <div class="pagination">
          <button @click="prevPage" :disabled="currentPage === 1">Prev</button>
          <span>Page {{ currentPage }} of {{ totalPages }}</span>
          <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
        </div>
      </div>
    </div>

    <div class="detail-panel">
      <h3>Book Info</h3>
      <p v-if="hoveredBook">
        <strong>Title:</strong> {{ hoveredBook.title }}<br />
        <strong>Author:</strong> {{ hoveredBook.author }}<br />
        <strong>Year:</strong> {{ hoveredBook.year }}<br />
        <strong>Description:</strong> {{ hoveredBook.description }}
      </p>
      <p v-else style="color: #888">Hover over a book to see details.</p>
    </div>
  </MainPage>
</template>

<script>
import MainPage from "@/components/MainPage.vue";
import { getUser } from '@/utils/auth';

export default {
  name: 'HomeView',
  components: {
    MainPage
  },
  data() {
    return {
      hoveredBook: null,
      user: getUser(),
      currentPage: 1,
      pageSize: 20,
      searchQuery: '',
      books: Array.from({ length: 60 }, (_, i) => ({
        id: i + 1,
        /* Finds and displays the information of each book in the page
         here it is currently using numbers incremented using a loop, but it needs to be fetched from the data
         in the backend by using the necessary logic instead of just i */
        title: `Book Title ${i + 1}`,
        author: `Author ${i + 1}`,
        year: 2000 + (i % 20),
        description: `This is the description of book ${i + 1}.`,
        price: `$ (i)`,  //
      }))
    };
  },
  computed: {
    filteredBooks() {
      const q = this.searchQuery.toLowerCase();
      return this.books.filter(book =>
          book.title.toLowerCase().includes(q) ||
          book.author.toLowerCase().includes(q)
      );
    },
    totalPages() {
      return Math.ceil(this.filteredBooks.length / this.pageSize);
    },
    paginatedBooks() {
      const start = (this.currentPage - 1) * this.pageSize;
      return this.filteredBooks.slice(start, start + this.pageSize);
    }
  },
  methods: {

    showBookInfo(book) {
      this.hoveredBook = book;
    },
    clearBookInfo() {
      this.hoveredBook = null;
    },
    goToDetail(id) {
      if (!this.user) {
        alert("Please login before using this feature.");
        return;
      }
      this.$router.push(`/book/${id}`);
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    }
  }
};
</script>

<style scoped>

.content {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 40px 10px 80px;
  margin-top: 10px;
}

.search-bar {
  display: flex;
  background: white;
  width: 300px;
}

.search-bar input {
  flex: 1;
  padding: 8px 12px;
  border-radius: 20px 0 0 20px;
  border: none;
  background: #eee;
}

.search-bar button {
  padding: 8px 12px;
  border-radius: 0 20px 20px 0;
  border: none;
  background: #222;
  color: white;
  cursor: pointer;
}

.login-status {
  margin-left: auto;
  align-self: center;
  padding: 0 20px;
  font-weight: bold;
  color: #444;
}

.scrollable-book-area {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
}

.book-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  column-gap: 12px;
  row-gap: 32px;
  padding: 20px;
}

.book-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}

.book-cover {
  width: 160px;
  height: 220px;
  background: #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  transition: transform 0.2s;
}

.book-cover:hover {
  transform: scale(1.03);
}

.book-title {
  margin-top: 10px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  color: #333;
  max-width: 160px;
  word-break: break-word;
}

.detail-panel {
  width: 250px;
  flex-shrink: 0;
  padding: 20px;
  background-color: #f9f9f9;
  border-left: 1px solid #ddd;
}

.detail-panel h3 {
  margin-top: 0;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  margin-top: 20px;
}

.pagination button {
  padding: 6px 12px;
  border: none;
  border-radius: 4px;
  background-color: #FF6F00;
  color: white;
  cursor: pointer;
  font-weight: bold;
}

.pagination button[disabled] {
  background-color: #ccc;
  cursor: not-allowed;
  color: #666;
}
</style>
