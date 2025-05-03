<template>
  <MainPage>
    <div class="detail-content">
      <div class="book-info">
        <div class="cover-with-buttons">
          <div class="cover"></div>
          <div class="buttons">
            <button class="btn orange" @click="addToWishlist">Add to Wishlist</button>
            <button class="btn orange" @click="goToPayment">Buy Now</button>
          </div>
        </div>
        <div class="info">
          <p><strong>Title:</strong> {{ book.title }}</p>
          <p><strong>Author:</strong> {{ book.author }}</p>
          <p><strong>Year:</strong> {{ book.year }}</p>
          <p><strong>Category:</strong> {{ book.category }}</p>
          <hr class="info-divider" />
          <p class="description">
            {{ book.description }}
          </p>
        </div>
        <div class="reviews-summary">
          <h1>Review summary</h1>
        </div>
      </div>
      <div>
        <h1>All Reviews</h1>
      </div>
    </div>
  </MainPage>
</template>

<script>
import MainPage from "@/components/MainPage.vue";
import { getBookById } from '@/utils/bookData';
import { addToWishlist } from '@/utils/wishlist';
import { getPurchasedBooks } from "@/utils/purchase";

export default {
  name: 'BookDetailView',
  components: {
    MainPage
  },
  data() {
    return {
      book: {}
    };
  },
  mounted() {
    const id = this.$route.params.id;
    this.book = getBookById(Number(id));
  },
  methods: {
    addToWishlist() {
      addToWishlist(this.book);
      alert('Book added to wishlist!');
    },
    goToPayment() {
      const books = getPurchasedBooks();
      if (books.length >= 10){
        alert(`You have already purchased ${books.length} books. You cannot loan anymore books.`);
      } else {
        this.$router.push(`/buy/${this.book.id}`);
      }
    }
  }
};
</script>

<style scoped>
.detail-content {
  flex: 1;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  background: #fff;
  overflow-y: auto;
  gap: 40px;
}

.book-info {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 10px;
}

.cover-with-buttons,
.info {
  flex: 1; /* each takes 25% if total = 4 parts */
}

.reviews-summary {
  flex: 2; /* takes 50% of the space */
}

.cover {
  width: 240px;
  height: 330px;
  background: #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.buttons {
  margin-top: 20px;
  display: flex;
  gap: 16px;
}

.btn.orange {
  background-color: #FF6F00;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 6px;
  cursor: pointer;
  font-weight: bold;
}

.btn.orange:hover {
  background-color: #e65c00;
}

.info p {
  font-size: 16px;
  margin: 8px 0;
  font-weight: normal;
}

.info-divider {
  width: 100%;
  border: none;
  border-top: 1px solid #ccc;
  margin: 16px 0;
}

.description {
  margin-top: 10px;
  line-height: 1.6;
  color: #444;
  white-space: pre-wrap;
}
</style>