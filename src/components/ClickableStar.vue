<template>
  <div class="stars">
    <!-- Render gold stars -->
    <span
        v-for="i in 5"
        :key="i"
        class="star"
        :class="{ 'star-grey': i > currentRating }"
        @click="setRating(i)"
    >★</span
    >
  </div>
</template>

<script>
export default {
  name: "ClickableStars",
  props: {
    stars: {
      type: Number,
      default: 3,
    },
    starSize: {
      type: String,
      default: "200%",
    },
    fontSize: {
      type: [Number, String],
      default: "25px",
    },
  },
  data() {
    return {
      currentRating: this.stars,
    };
  },
  methods: {
    setRating(newRating) {
      this.currentRating = newRating;
      this.$emit("click", newRating); // Emit to parent
    },
  },
};
</script>

<style scoped>
.stars {
  display: flex;
  align-items: center;
  cursor: pointer;
}
.star {
  font-size: v-bind(starSize);
  color: gold;
  padding-top: 5px;
  transition: color 0.2s ease;
}
.star-grey {
  color: lightgray;
}
</style>
