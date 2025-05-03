<template>
  <MainPage>
    <div class="content">
      <div class="search-bar">
        <input type="text" v-model="searchTerm" placeholder="Search in wishlist..." />
        <button>🔍</button>
      </div>

      <div class="scrollable-book-area">
        <div class="book-grid">
          <div
              v-for="book in filteredWishlist"
              :key="book.id"
              class="book-card"
              @mouseover="showBookInfo(book)"
              @mouseleave="clearBookInfo"
          >
            <div class="book-cover" @click="goToDetail(book.id)"></div>
            <div class="book-title">{{ book.title }}</div>
            <button class="remove-btn" @click="removeBook(book.id)">Remove</button>
          </div>
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
//import TopBar from "@/components/TopBar.vue";
//import SideBar from "@/components/SideBar.vue";
import { getWishlist, removeFromWishlist } from '@/utils/wishlist'

export default {
  name: 'WishlistView',
  components: {
    MainPage
  },
  data() {
    return {
      wishlist: getWishlist(),
      hoveredBook: null,
      searchTerm: ''
    }
  },
  computed: {
    filteredWishlist() {
      const keyword = this.searchTerm.toLowerCase()
      return this.wishlist.filter(book => book.title && book.title.toLowerCase().includes(keyword))
    }
  },
  methods: {
    showBookInfo(book) {
      this.hoveredBook = book
    },
    clearBookInfo() {
      this.hoveredBook = null
    },
    removeBook(id) {
      removeFromWishlist(id)
      this.wishlist = getWishlist() // 刷新列表
    },
    goToDetail(id) {
      this.$router.push(`/book/${id}`)
    }
  }
}
</script>

<style scoped>

.content {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.search-bar {
  display: flex;
  margin: 16px 20px 0;
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

.remove-btn {
  margin-top: 6px;
  padding: 4px 10px;
  font-size: 12px;
  border: none;
  border-radius: 6px;
  background-color: #d9534f;
  color: white;
  cursor: pointer;
}

.remove-btn:hover {
  background-color: #c9302c;
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
</style>
