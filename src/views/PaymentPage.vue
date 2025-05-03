<template>
  <MainPage title="Confirm Your Purchase">
    <div class="content">
      <div class="payment-content">
        <div class="cover"></div>
        <div class="price">Price: ${{ book.price }}</div>
        <div class="buttons">
          <button class="btn confirm" @click="handlePayment">Confirm Payment</button>
          <button class="btn cancel" @click="cancelPayment">Cancel</button>
        </div>
        <p v-if="paymentSuccess" class="success">✅ Payment successful via HorsePay!</p>
      </div>
    </div>

    <div class="detail-panel">
      <h3>Book Info</h3>
      <p><strong>Title:</strong> {{ book.title }}</p>
      <p><strong>Author:</strong> {{ book.author }}</p>
      <p><strong>Year:</strong> {{ book.year }}</p>
      <p><strong>Category:</strong> {{ book.category }}</p>
      <hr />
      <p class="description">{{ book.description }}</p>
    </div>
  </MainPage>
</template>

<script>
import MainPage from "@/components/MainPage.vue";
import { getBookById } from '@/utils/bookData';
import { addPurchasedBook } from '@/utils/purchase';

export default {
  name: 'PaymentPage',
  components: {
    MainPage
  },
  data() {
    return {
      book: {},
      paymentSuccess: false
    };
  },
  mounted() {
    const id = this.$route.params.id;
    const data = getBookById(Number(id));
    this.book = { ...data, price: (data.id * 2 + 10).toFixed(2) };
  },
  methods: {
    handlePayment() {
      setTimeout(() => {
        addPurchasedBook(this.book);
        this.paymentSuccess = true;
        setTimeout(() => {
          this.$router.push('/home');
        }, 1500);
      }, 800);
    },
    cancelPayment() {
      this.$router.push(`/book/${this.book.id}`);
    }
  }
};
</script>

<style scoped>
.payment-page {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.top-bar {
  background-color: #FF6F00;
  color: white;
  padding: 12px 24px;
  font-size: 22px;
  font-weight: bold;
  text-align: center;
}

.main-section {
  display: flex;
  flex: 1;
  overflow: hidden;
}

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

.content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding: 40px;
  background: #fff;
}

.payment-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.cover {
  width: 220px;
  height: 300px;
  background: #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.price {
  margin-top: 16px;
  font-size: 24px;
  font-weight: bold;
  color: #FF6F00;
}

.buttons {
  margin-top: 20px;
  display: flex;
  gap: 16px;
}

.btn {
  padding: 10px 20px;
  border-radius: 6px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  border: none;
}

.btn.confirm {
  background-color: #FF6F00;
  color: white;
}

.btn.confirm:hover {
  background-color: #e65c00;
}

.btn.cancel {
  background-color: #ccc;
  color: #222;
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

.description {
  margin-top: 10px;
  color: #444;
  line-height: 1.5;
}

.success {
  margin-top: 20px;
  color: green;
  font-weight: bold;
}
</style>
