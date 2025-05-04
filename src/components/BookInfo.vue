<template>
  <div class="book-container">
    <div class="book-cover-with-buttons">
      <div class="book-cover">
      </div>
      <div class="book-buttons">
        <button class="btn orange" @click="addToWishlist">Add to Wishlist</button>
        <button class="btn orange" @click="goToPayment">Buy Now</button>
      </div>
    </div>
    <div class="book-info-container">
      <div class="book-info">
        <p><strong>Title:</strong> {{ book.title }}</p>
        <p><strong>Author:</strong> {{ book.author }}</p>
        <p><strong>Year:</strong> {{ book.year }}</p>
        <p><strong>Category:</strong> {{ book.category }}</p>
      </div>
      <hr class="info-divider" />
      <p class="book-description">
        {{ book.description }}
      </p>
    </div>
  </div>
</template>

<script>
import {addToWishlist} from "@/utils/wishlist";
import {getPurchasedBooks} from "@/utils/purchase";

export default {
  name: "BookInfo",
  props: {
    book: Object
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
}
</script>

<style scoped>
.book-container{
  display: flex;
  flex-direction: row;
  gap: 26px;
}
.book-cover-with-buttons{
  display: flex;
  flex-direction: column;
}
.book-cover{
  width: 240px;
  height: 330px;
  background: #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}
.book-buttons{
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  gap: 16px;
  width: 240px
}
.book-info-container{
  display: flex;
  flex-direction: column;
}
.book-info{
  font-size: 16px;
  margin: 8px 0;
  font-weight: normal;
}
.info-divider{
  width: 100%;
  border: none;
  border-top: 1px solid #ccc;
  margin: 16px 0;
}
.book-description{
  margin-top: 10px;
  line-height: 1.6;
  color: #444;
  white-space: pre-wrap;
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

</style>