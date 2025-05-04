<template>
  <div class="average-rating">
    <div class="stars">
      <span class="star-average" v-for="i in 5">★</span>
      <span class="rating-number">{{ averageRating.toFixed(1) }}/5</span>
    </div>
    <div class="stars">
      <span class="star" v-for="i in 5">★</span>
      <span class="rating-number">{{ averageRating.toFixed(1) }}/5</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "AverageStarRating",
  props: {
    averageRating: {
      type: Number,
      default: 3
    },
  },
  data() {
    return {
      uniqueId: Math.random().toString(36).substring(2, 9),
    };
  },
  methods: {
    getFillPercent(index) {
      const fullStars = Math.floor(this.averageRating);
      if (index <= fullStars) return "100%";
      if (index === fullStars + 1) {
        const partial = (this.averageRating - fullStars) * 100;
        return `${partial}%`;
      }
      return "0%";
    },
    getStarStyle(index) {
      return {
        width: "24px",
        height: "24px",
        marginRight: "4px",
      };
    },
  },
};
</script>

<style scoped>
.star-average{
  font-size: 300%;
  color: gold;
  padding-top: 5px;
}

.star{
  font-size: 200%;
  color: gold;
  padding-top: 5px;
}
.rating-number {
  margin-left: 10px;
  margin-top: 50px;
  font-weight: bold;
  font-size: 25px;
}
</style>
