<template>
  <MainPage>
    <div class="book-details-container">
      <div class="book-info-reviews">
        <div class="book-info">
          <BookInfo :book="book"/>
        </div>
        <div class="self-review">
          <h2>Submit review</h2>
          <ClickableStar
              :stars="ownRating"
              starSize="40px"
              @click="handleRatingChange"
          />
          <InputBox type="email" placeholder="Subject" v-model="subject" />
          <TextareaBox placeholder="Leave a review" v-model="review">
            Hello
          </TextareaBox>
          <button class="btn orange" @click="postReview">Post review</button>
        </div>
        <div class="book-reviews-summary">
          <h2>Review summary</h2>
          <div class="stars-wrapper">
            <Stars v-for="i in 5"
             :stars=6-i
             starSize="40px"
             fontSize="25px"
             :leftText=(6-i).toFixed(1)
             :rightText=ratings[i-1].toString()
            />
          </div>
        </div>
      </div>

      <div class="book-reviews">
        <h1>All Reviews</h1>
        <div class="all-reviews">
          <div v-for="review in reviews" class="book-review">
            <p>{{review.user_id}}</p>
            <Stars
                   :stars=review.star_rating
                   starSize="36px"
            />
            <p>{{review.review}}</p>
          </div>
        </div>
      </div>

    </div>
  </MainPage>
</template>

<script>
import MainPage from "@/components/MainPage.vue";
import AverageStarRating from "@/components/AverageStarRating.vue";
import Stars from "@/components/Stars.vue";
import ClickableStar from "@/components/ClickableStar.vue";
import BookInfo from "@/components/BookInfo.vue";
import TextareaBox from "@/components/TextareaBox.vue";
import InputBox from "@/components/InputBox.vue";
import {getBookById, getBooksRatings, getBooksReviews, postBookReview,updatedBookRatingOccurence} from '@/utils/bookData';
import {getUser} from "@/utils/auth";

// import {getAllBookReviewRatings} from "@/utils/bookData";

export default {
  name: 'BookDetailView',
  components: {
    InputBox,
    MainPage,
    AverageStarRating,
    Stars,
    ClickableStar,
    BookInfo,
    TextareaBox
  },
  data() {
    return {
      book: {},
      username: {},
      ratings: [updatedBookRatingOccurence(5),updatedBookRatingOccurence(4),updatedBookRatingOccurence(3),updatedBookRatingOccurence(2),updatedBookRatingOccurence(1)],
      // ratings: [getAllBookReviewRatings(5),getAllBookReviewRatings(4),getAllBookReviewRatings(3),getAllBookReviewRatings(2),getAllBookReviewRatings(1)],   // function to calculate the number of ratings put for each star
      subject: "",
      review: "",
      reviews: [],
      ownRating: 3, // Default rating
    };
  },
  methods:{
    postReview(){
      const newRating = {
        book_id: this.book.id,
        user_id: this.username.username, // This displays the username?
        star_rating: this.ownRating,
        review: this.review
      };
      postBookReview(newRating);    // Implement check condition ti ensure
      if (newRating.review === "")  {
        alert("Please fill in the review before posting")

      }
      else {
        alert('Book review posted successfully.')
        switch (newRating.star_rating)  {
          case 1:
            getBooksRatings()
        }
        this.reviews = getBooksReviews(this.book);
      }
      //console.log(newRating);
    },
    handleRatingChange(newRating) {
      if (Number.isInteger(newRating)){
        //console.log("New rating selected:", newRating);
        this.ownRating = newRating;
      }
    },
  },
  mounted() {
    const id = this.$route.params.id;
    this.book = getBookById(Number(id));
    this.reviews = getBooksReviews(this.book);
    this.username = getUser();  // Gets the logged in username from the auth.js file
    // this.ratings = getBooksRatings(this.ratings);



    // const count = getAllBookReviewRatings(ownR)
  },
};
</script>

<style scoped>
.book-details-container{
  display: flex;
  flex-direction: column;
  padding: 24px;
  width: 100%;
  gap: 50px;
}

.book-info-reviews{
  display: flex;
  flex-direction: row;
  gap: 50px;
}
.book-info{
  flex: 1
}
.book-reviews-summary{
  flex: 1;
}
.book-reviews-summary h2{
  text-align: start;
}
.self-review{
  flex: 1
}
.stars-wrapper{
  align-items: end;
}
.book-review{
  border: 1px solid black;
  margin: 3px;
  text-align: start;
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